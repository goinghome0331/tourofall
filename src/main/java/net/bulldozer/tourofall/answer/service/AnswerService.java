package net.bulldozer.tourofall.answer.service;

import net.bulldozer.tourofall.answer.dto.AnswerRegistrationForm;
import net.bulldozer.tourofall.answer.dto.AnswerRenderingModel;
import net.bulldozer.tourofall.answer.dto.AnswerRenderingModelsSet;

public interface AnswerService {
	public AnswerRenderingModel registerNewAnswer(AnswerRegistrationForm answerRegistrationForm);
	public AnswerRenderingModelsSet getAnswerRenderingModelsSet(long userId, int index);
	public void deleteAnswer(long answerId);
}
