package net.bulldozer.tourofall.evaluation.service;

import java.util.List;

import net.bulldozer.tourofall.evaluation.dto.Evaluation;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRegistration;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRenderingModelsSet;
import net.bulldozer.tourofall.user.dto.User;

public interface EvaluationService {
	public Evaluation registerNewEvaluation(EvaluationRegistration evaluationRegistration);
	public Evaluation findByUserIdAndItemId(long id,int itemId);
	public List<Evaluation> findByUserId(long userId);
	public int getEvaluationCountByItemId(int itemId);
	public double getEvaluationMeanByItemId(int itemId);
	public EvaluationRenderingModelsSet getEvaluationRenderingModelsSet(long userId, int pageNo) throws Exception;
}
