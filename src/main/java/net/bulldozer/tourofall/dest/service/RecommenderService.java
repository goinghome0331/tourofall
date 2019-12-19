package net.bulldozer.tourofall.dest.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.bulldozer.tourofall.dest.util.RecommendJSONUtilites;
import net.bulldozer.tourofall.recommendation.dto.RecommendationRenderingModel;
import net.bulldozer.tourofall.recommendation.dto.RecommendationRenderingModelForm;

@Service
public class RecommenderService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public RecommendationRenderingModelForm getRecommendations(long id) throws Exception{
		RecommendationRenderingModelForm recommendationRenderingModelForm = new RecommendationRenderingModelForm();
		List<RecommendationRenderingModel> recommendationRenderingModels = new ArrayList<RecommendationRenderingModel>();
		
		String result = restTemplate.getForObject("http://223.194.155.69:8080/recommender/"+id, String.class);
		JSONArray items = RecommendJSONUtilites.getRecommendItems(result);
		for(Object tmp : items){
			JSONObject item = (JSONObject)tmp;
			System.out.println((String)item.get("URL"));
			RecommendationRenderingModel recommendationRenderingModel = new RecommendationRenderingModel();
			recommendationRenderingModel.setItemId(Integer.parseInt((String)item.get("ID")));
			recommendationRenderingModel.setRecommendScore(Double.parseDouble((String)item.get("Value")));
			recommendationRenderingModel.setImageUrl((String)item.get("URL"));
			recommendationRenderingModel.setScore(0);
			recommendationRenderingModel.setTitle((String)item.get("Title"));
			recommendationRenderingModels.add(recommendationRenderingModel);
		}
		recommendationRenderingModelForm.setRecommendationRenderingModels(recommendationRenderingModels);
		return recommendationRenderingModelForm;
		
	}
}
