package net.bulldozer.tourofall.question.service;

import java.util.List;

import net.bulldozer.tourofall.answer.dto.AnswerRenderingModel;
import net.bulldozer.tourofall.question.dto.QnARenderingModelsSet;
import net.bulldozer.tourofall.question.dto.Question;
import net.bulldozer.tourofall.question.dto.QuestionRegistrationForm;
import net.bulldozer.tourofall.question.dto.QuestionRenderingModel;
import net.bulldozer.tourofall.question.dto.QuestionRenderingModelsSet;
import net.bulldozer.tourofall.question.dto.UserQuestionRenderingModelsSet;

public interface QuestionService {
	public List<QuestionRenderingModel> getQuestionRenderingModelsByItemId(int itemId) ;
	public List<AnswerRenderingModel> getAnswerRenderingModelsByQuestionId(long questionId);
	public QuestionRenderingModel getQuestionRenderingModelById(long questionId);
	public Question registerNewQuestion(QuestionRegistrationForm registrationQuestionForm);
	public void incrementVisitor(long questionId);
	public Question getQuestionById(long questionId);
	public int getQuestionCountByItemId(int itemId);
	public QuestionRenderingModelsSet getQuestionRenderingModelsSet(int itemId, int index);
	public QnARenderingModelsSet getQnARenderingModelsSet(long questionId);
	public UserQuestionRenderingModelsSet getUserQuestionRenderingModelsSet(long userId, int index);
}
