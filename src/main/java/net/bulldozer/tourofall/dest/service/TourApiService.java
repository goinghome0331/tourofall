package net.bulldozer.tourofall.dest.service;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import net.bulldozer.tourofall.dest.dto.ItemBasicInfo;
import net.bulldozer.tourofall.dest.dto.SearchResultRenderingModel;
import net.bulldozer.tourofall.dest.dto.SearchResultRenderingModelsSet;
import net.bulldozer.tourofall.dest.util.TourJSONUtilities;
import net.bulldozer.tourofall.dest.util.TourUriUtilities;
import net.bulldozer.tourofall.evaluation.dto.Evaluation;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRenderingModel;
import net.bulldozer.tourofall.evaluation.repository.EvaluationRepository;
import net.bulldozer.tourofall.security.dto.UserAuthenticationDetails;

@Service
public class TourApiService {
	private static final int PAGE_COUNT = 5;
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EvaluationRepository evaluationRepository; 
	
	
	private JSONObject sendAndReceiveDataFromApiServer(String serviceName, Map<String,String> parameter) throws Exception{
		UriComponentsBuilder urIbuilder = TourUriUtilities.getBaseUriComponentsBuilder(serviceName);
		Set<String> parameterName = parameter.keySet();
		Iterator<String> it = parameterName.iterator();
		System.out.println(parameterName.size());
		while(it.hasNext()){
			String key = it.next();
			System.out.println("name : "+ key +", value : " + parameter.get(key));
			urIbuilder.queryParam(key, parameter.get(key));
		}
		URI uri = urIbuilder.build().encode().toUri();
		String jsonResult = restTemplate.getForObject(uri, String.class);
		return TourJSONUtilities.getTourItems(jsonResult);
	}
	
	
	private boolean addEvaluationRegistrationToList(List<EvaluationRenderingModel> evalList,Object obj){
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject item = (JSONObject) obj;
		long itemId = (Long)item.get("contentid");
		long userId = userAuthenticationDetails.getId();
	
		Evaluation evaluation = evaluationRepository.findByUserIdAndItemId(userId, (int)itemId);
		
		if(evaluation == null){
			EvaluationRenderingModel evaluationRegistration = new EvaluationRenderingModel((int)itemId, (String)item.get("firstimage"), (String)item.get("title"), 0);
			evalList.add(evaluationRegistration);
			return true;
		}
		return false;
	}
	
	
	
	private int addEvaluationsToList(JSONObject result, List<EvaluationRenderingModel> evalList){
		int count = 0;
		
		if(result == null){
			return count;
		}
		JSONObject items = (JSONObject)result.get("items");
		// 0�� �� ó���ؾ��Ѵ�.
		long totalCount = (Long)result.get("totalCount");
		if (totalCount > 1) {
			System.out.println(result.get("totalCount"));
			JSONArray item = (JSONArray) items.get("item");
			System.out.println(item.size());
			for(Object temp : item){
				if(addEvaluationRegistrationToList(evalList, temp)) count++;
			}
		}else if(totalCount == 1) {
			Object item = items.get("item");
			System.out.println("totocalCount = 1");

			addEvaluationRegistrationToList(evalList, item);
			count++;
		}
		return count;
	}


	public List<EvaluationRenderingModel> getEvaluationRenderingModels(String itemCat1, String itemCat2, int pageNo) throws Exception {
		List<EvaluationRenderingModel> evalList = new ArrayList<EvaluationRenderingModel>();
		Map<String,String> parameter = new HashMap<String,String>();
		
		
		parameter.put("cat1", itemCat1);
		parameter.put("cat2", itemCat2);
		parameter.put("listYN", "Y");
		parameter.put("arrange", "B");
		parameter.put("numOfRows"  , "30");
		parameter.put("pageNo", Integer.toString(pageNo));
		
		JSONObject result = sendAndReceiveDataFromApiServer("areaBasedList",parameter);
			
			
		addEvaluationsToList(result,evalList);
				
		return evalList;
	}
	
	@Transactional(readOnly=true)
	public SearchResultRenderingModelsSet getSearchResult(String query, String pageNum) throws Exception {
		Map<String,String> parameter = new HashMap<String,String>();
		parameter.put("keyword", URLDecoder.decode(URLEncoder.encode(query, "UTF-8"), "UTF-8"));
		parameter.put("pageNo", pageNum);
		parameter.put("arrange", "A");
		parameter.put("numOfRows"  , "8");
		parameter.put("listYN", "Y");
		
		List<SearchResultRenderingModel> searchResultRenderingModels = new ArrayList<SearchResultRenderingModel>();
		SearchResultRenderingModelsSet searchResultRenderingModelsSet =  new SearchResultRenderingModelsSet();
		List<Integer> numList = new ArrayList<Integer>();
		
		
		JSONObject body = sendAndReceiveDataFromApiServer("searchKeyword",parameter);
		
		
		if(body == null){
			searchResultRenderingModelsSet.setSearchResultRenderingModels(searchResultRenderingModels);
			searchResultRenderingModelsSet.setPageNo(1);
			searchResultRenderingModelsSet.setTotalCount(0);
			numList.add(1);
			searchResultRenderingModelsSet.setNumList(numList);
			searchResultRenderingModelsSet.setTotalPage(0);
			return searchResultRenderingModelsSet;
		}
		
		
		long numOfRows = (Long)body.get("numOfRows");
		long pageNo = (Long)body.get("pageNo");
		long totalCount = (Long)body.get("totalCount");
		
		System.out.println("numOfRows: "+numOfRows +", pageNo: " + pageNo +", totalCount: " + totalCount);
		long listIndex = (pageNo-1)/PAGE_COUNT;
		long totalPage = totalCount/numOfRows;
		if(totalCount%numOfRows != 0){
			totalPage++;
		}
		
		
		long listTotal = totalPage/PAGE_COUNT;
		

		if(listIndex == listTotal && totalPage%PAGE_COUNT != 0){
			for(long i = 1 + listIndex*5; i <= listIndex*5 + totalPage%PAGE_COUNT; i++){
				numList.add((int)i);
			}
		}else{
			for(long i = 1 + listIndex*5; i <= 5 + listIndex*5; i++){
				numList.add((int)i);
			}
		}
		
		System.out.println(numList);
			
		System.out.println("listIndex: "+listIndex +", totalPage: " + totalPage +", listTotal: " + listTotal);
		
		
		
		JSONObject itemSet = (JSONObject) body.get("items");
		
		
		Object principal  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserAuthenticationDetails userAuthenticationDetails;
		if(principal instanceof String){
			userAuthenticationDetails = null;
		}else{
			userAuthenticationDetails = (UserAuthenticationDetails)principal; 
		}
		
		if (((Long)body.get("totalCount")) != 1) {
			JSONArray items = (JSONArray) itemSet.get("item");
			for(Object obj : items){
				JSONObject item = (JSONObject)obj;
				long contentId = (Long)item.get("contentid");
				int itemId = (int)contentId;
				
				String address = (String)item.get("addr1");
				if(item.get("addr2") != null)
					address += item.get("addr2");
				 
				SearchResultRenderingModel searchResultRenderingModel = SearchResultRenderingModel.getBuilder()
																		.itemId(itemId)
																		.imageUrl((String)item.get("firstimage"))
																		.title((String)item.get("title"))
																		.address(address)
																		.build();
																		
				if(userAuthenticationDetails != null){
					Evaluation evaluation = evaluationRepository.findByUserIdAndItemId(userAuthenticationDetails.getId(), itemId);
					if(evaluation == null){
						searchResultRenderingModel.setScore(0);
					}else{
						searchResultRenderingModel.setScore(evaluation.getScore());
					}
					
				}
				searchResultRenderingModels.add(searchResultRenderingModel);
			}
		} else {
			JSONObject item = (JSONObject) itemSet.get("item");
			long contentId = (Long)item.get("contentid");
			int itemId = (int)contentId;
			SearchResultRenderingModel searchResultRenderingModel = SearchResultRenderingModel.getBuilder()
																		.itemId(itemId)
																		.imageUrl((String)item.get("firstimage"))
																		.title((String)item.get("title"))
																		.address((String)item.get("addr1")+(String)item.get("addr2"))
																		.build();
					
			if(userAuthenticationDetails != null){
				Evaluation evaluation = evaluationRepository.findByUserIdAndItemId(userAuthenticationDetails.getId(), itemId);
				if(evaluation == null){
					searchResultRenderingModel.setScore(0);
				}else{
					searchResultRenderingModel.setScore(evaluation.getScore());
				}
			}
			searchResultRenderingModels.add(searchResultRenderingModel);
		}
		
		
		searchResultRenderingModelsSet.setSearchResultRenderingModels(searchResultRenderingModels);
		searchResultRenderingModelsSet.setPageNo((int)pageNo);
		searchResultRenderingModelsSet.setTotalCount((int)totalCount);
		searchResultRenderingModelsSet.setNumList(numList);
		searchResultRenderingModelsSet.setTotalPage((int)totalPage);
		
		return searchResultRenderingModelsSet; 
		
	}
	public String getItemImage(int contentId) throws Exception{
		Map<String,String> parameter = new HashMap<String,String>();
		parameter.put("contentId", Integer.toString(contentId));
		parameter.put("imageYN","Y");

		String imageUrl = null;
		
		JSONObject body = (JSONObject)(sendAndReceiveDataFromApiServer("detailImage",parameter));
		if(body == null){
			return imageUrl;
		}
		
		JSONObject items = (JSONObject)(body.get("items"));
		
		if((Long)body.get("totalCount") == 1){
			JSONObject item = (JSONObject)items.get("item");
			imageUrl = (String)item.get("originimgurl");
		}else{
			JSONArray item = (JSONArray)items.get("item");
			for(Object obj : item){
				JSONObject jObj = (JSONObject)obj;
				if(jObj.get("originimgurl") != null){
					imageUrl = (String)jObj.get("originimgurl");
					break;
				}
				
			}
		}
		return imageUrl;
	}
	public ItemBasicInfo getItemBasicInfo(int contentId) throws Exception{
		Map<String,String> parameter = new HashMap<String,String>();
		parameter.put("contentId", Integer.toString(contentId));
		parameter.put("defaultYN", "Y");
		parameter.put("firstImageYN", "Y");
		parameter.put("areaCodeYN","Y");
		parameter.put("addrinfoYN","Y");
		parameter.put("mapinfoYN","Y");
		parameter.put("overviewYN","Y");
		
		
		JSONObject body = sendAndReceiveDataFromApiServer("detailCommon",parameter);
		if(body != null){
			JSONObject items = (JSONObject) body.get("items");
			JSONObject item = (JSONObject) items.get("item");
			
			ItemBasicInfo itemBasicInfo = ItemBasicInfo.getBuilder()
											.title((String)item.get("title"))
											.address((String)item.get("addr1")+(String)item.get("addr2"))
											.imageUrl((String)item.get("firstimage"))
											.build();
			return itemBasicInfo;			
		}
		return null; 
	}
	
	public JSONObject getBasicInfo(int contentId) throws Exception{
		Map<String,String> parameter = new HashMap<String,String>();
		parameter.put("contentId", Integer.toString(contentId));
		parameter.put("defaultYN", "Y");
		parameter.put("firstImageYN", "Y");
		parameter.put("areaCodeYN","Y");
		parameter.put("addrinfoYN","Y");
		parameter.put("mapinfoYN","Y");
		parameter.put("overviewYN","Y");
		return sendAndReceiveDataFromApiServer("detailCommon",parameter);
	}
	public JSONObject getIntroInfo(int contentId,int contentTypeId) throws Exception{
		Map<String,String> parameter = new HashMap<String,String>();
		parameter.put("contentId", Integer.toString(contentId));
		parameter.put("contentTypeId", Integer.toString(contentTypeId));
		parameter.put("introYN","Y");
		return sendAndReceiveDataFromApiServer("detailIntro",parameter);
	}
	public JSONObject getDetailInfo(int contentId,int contentTypeId) throws Exception{
		Map<String,String> parameter = new HashMap<String,String>();
		parameter.put("contentId", Integer.toString(contentId));
		parameter.put("contentTypeId", Integer.toString(contentTypeId));
		parameter.put("introYN","Y");
		return sendAndReceiveDataFromApiServer("detailInfo",parameter);
	}
	
	public JSONObject getImageInfo(int contentId,int contentTypeId) throws Exception{
		Map<String,String> parameter = new HashMap<String,String>();
		parameter.put("contentId", Integer.toString(contentId));
		parameter.put("contentTypeId", Integer.toString(contentTypeId));
		parameter.put("imageYN","Y");
		return sendAndReceiveDataFromApiServer("detailImage",parameter);
	}
}
