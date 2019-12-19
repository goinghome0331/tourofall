package net.bulldozer.tourofall.evaluation.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationRenderingModelsSet {
	private List<EvaluationRenderingModel> evaluationRenderingModels;
	public static final int numOfRows = 20;
	private int pageNo;
	private int totalPage;
	private List<Integer> indexList;
	private List<Double> userScoreList;
}
