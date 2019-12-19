package net.bulldozer.tourofall.recommendation.dto;

import java.util.List;

public class RecommendationRenderingModelForm {
	private List<RecommendationRenderingModel> recommendationRenderingModels;
	
	public RecommendationRenderingModelForm(){
		
	}
	public RecommendationRenderingModelForm(List<RecommendationRenderingModel> recommendationRenderingModels){
		this.recommendationRenderingModels = recommendationRenderingModels;
	}
	public List<RecommendationRenderingModel> getRecommendationRenderingModels() {
		return recommendationRenderingModels;
	}
	public void setRecommendationRenderingModels(List<RecommendationRenderingModel> recommendationRenderingModels) {
		this.recommendationRenderingModels = recommendationRenderingModels;
	}
	
	@Override
	public String toString() {
		return "RecommendationRenderingModelForm [recommendationRenderingModels=" + recommendationRenderingModels + "]";
	}
	
}
