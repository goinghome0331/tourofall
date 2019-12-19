package net.bulldozer.tourofall.evaluation.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.dest.service.TourApiService;
import net.bulldozer.tourofall.evaluation.dto.Evaluation;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRegistration;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRenderingModel;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRenderingModelsSet;
import net.bulldozer.tourofall.evaluation.repository.EvaluationRepository;
import net.bulldozer.tourofall.security.dto.UserAuthenticationDetails;
import net.bulldozer.tourofall.user.dto.User;
import net.bulldozer.tourofall.user.repository.UserRepository;

@Service
public class EvaluationRepositoryService implements EvaluationService{
	private static final int PAGE_COUNT=5;
	
	@Autowired
	private TourApiService tourApiService;
	
	@Autowired
	private EvaluationRepository evaluationRepository;

	@Autowired
	private UserRepository userRepository;
	
	
	@Transactional
	@Override
	public Evaluation registerNewEvaluation(EvaluationRegistration evaluationRegistration) {
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findOne(userAuthenticationDetails.getId());
		
		Evaluation getEvaluation = evaluationRepository.findByUserIdAndItemId(user.getId(), evaluationRegistration.getItemId());
		if(getEvaluation == null){
			getEvaluation = Evaluation.getBuilder()
										.itemId(evaluationRegistration.getItemId())
										.build();
		}
		getEvaluation.setScore(evaluationRegistration.getScore());
		
		user.addEvaluation(getEvaluation);
		return evaluationRepository.save(getEvaluation);
	}

	@Transactional(readOnly=true)
	@Override
	public Evaluation findByUserIdAndItemId(long id,int itemId) {
		return evaluationRepository.findByUserIdAndItemId(id,itemId);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Evaluation> findByUserId(long userId){
		return evaluationRepository.findByUserId(userId);
	}
	
	@Transactional(readOnly=true)
	@Override
	public int getEvaluationCountByItemId(int itemId){
		List<Evaluation> evaluations = evaluationRepository.findByItemId(itemId);
		return evaluations.size();
	}
	
	@Transactional(readOnly=true)
	@Override
	public double getEvaluationMeanByItemId(int itemId){
		double mean = 0;
		List<Evaluation> evaluations = evaluationRepository.findByItemId(itemId);
		Iterator<Evaluation> evaluationIter = evaluations.iterator();
		while(evaluationIter.hasNext()){
			Evaluation evaluation = evaluationIter.next();
			mean += evaluation.getScore();
		}
		mean = mean / evaluations.size();
		return mean;
	}
	@Transactional(readOnly=true)
	@Override
	public EvaluationRenderingModelsSet getEvaluationRenderingModelsSet(long userId, int pageNo) throws Exception{
		List<EvaluationRenderingModel> evaluationRenderingModels = new ArrayList<EvaluationRenderingModel>();
		List<Evaluation> evaluations = evaluationRepository.findByUserId(userId);
		EvaluationRenderingModelsSet evaluationRenderingModelsSet = new EvaluationRenderingModelsSet();  
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Double> userScoreList = new ArrayList<Double>();;
		
		
		int index = pageNo-1;
		int totalCount = evaluations.size();
		int listIndex = index/PAGE_COUNT; // 5 = PAGE�� ������ �ε��� ��
		int totalPage = totalCount/EvaluationRenderingModelsSet.numOfRows;
		if(totalCount%EvaluationRenderingModelsSet.numOfRows != 0)
			totalPage++;
		
		
		int listTotal = totalPage / PAGE_COUNT;
		
		List<Integer> indexList = new ArrayList<Integer>();
		
		if(listIndex == listTotal && totalPage%PAGE_COUNT != 0){
			for(int i = 1 + listIndex*5; i <= listIndex*5 + totalPage%PAGE_COUNT; i++){
				indexList.add(i);
			}
		}else{
			if(totalCount != 0){
				for(int i = 1 + listIndex*5; i <= 5 + listIndex*5; i++){
					indexList.add(i);
				}
			}
		}
		
		
		for(int i = index*EvaluationRenderingModelsSet.numOfRows; i < index*EvaluationRenderingModelsSet.numOfRows+EvaluationRenderingModelsSet.numOfRows; i++){
			Evaluation evaluation = null;
			try{
				evaluation = evaluations.get(i);
			}catch(IndexOutOfBoundsException iobe){
				break;
			}
			JSONObject result = (JSONObject)tourApiService.getBasicInfo(evaluation.getItemId());
			if(result != null){
				JSONObject items = (JSONObject) result.get("items");
				JSONObject item = (JSONObject) items.get("item");
				
			
			EvaluationRenderingModel evaluationRenderingModel = new EvaluationRenderingModel(evaluation.getItemId(),(String)item.get("firstimage"),(String)item.get("title"),evaluation.getScore());
			
			if(userId != userAuthenticationDetails.getId()){
				Evaluation myEvaluation = evaluationRepository.findByUserIdAndItemId(userAuthenticationDetails.getId(), evaluation.getItemId());
				if(myEvaluation == null){
					evaluationRenderingModel.setScore(0);
				}else{
					evaluationRenderingModel.setScore(myEvaluation.getScore());
				}
				userScoreList.add(evaluation.getScore());
			}else{
				userScoreList.add(0.0);
			}
			
			evaluationRenderingModels.add(evaluationRenderingModel);
			}
		}  
		evaluationRenderingModelsSet.setEvaluationRenderingModels(evaluationRenderingModels);
		evaluationRenderingModelsSet.setPageNo(pageNo);
		evaluationRenderingModelsSet.setTotalPage(totalPage);
		evaluationRenderingModelsSet.setIndexList(indexList);
		
		
		
		
		evaluationRenderingModelsSet.setUserScoreList(userScoreList);
		
		return evaluationRenderingModelsSet;
	}
}
