package net.bulldozer.tourofall.recommendation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRenderingModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationRenderingModel extends EvaluationRenderingModel{
	private double recommendScore;
	
	
	public RecommendationRenderingModel(int itemId,String imageUrl, String title, double score, double recommendScore){
		super(itemId,imageUrl,title,score);
		this.recommendScore = recommendScore;
	}
}
