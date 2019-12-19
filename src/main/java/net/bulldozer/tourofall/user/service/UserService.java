package net.bulldozer.tourofall.user.service;

import java.util.Collection;

import net.bulldozer.tourofall.answer.dto.AnswerRenderingModel;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRenderingModel;
import net.bulldozer.tourofall.question.dto.QuestionRenderingModel;
import net.bulldozer.tourofall.review.dto.ReviewRenderingModel;
import net.bulldozer.tourofall.user.dto.User;
import net.bulldozer.tourofall.user.dto.UserModificationForm;
import net.bulldozer.tourofall.user.dto.UserRegistrationForm;

public interface UserService {
	public User registerNewUser(UserRegistrationForm registrationUserForm)throws DuplicateUsernameException;
	public User getUserByUsername(String username);
	public User getUserByUserId(long id);
	public Collection<QuestionRenderingModel> getQuestionsByUserId(long id);
	public Collection<AnswerRenderingModel> getAnswersByUserId(long id);
	public Collection<ReviewRenderingModel> getReviewsByUserId(long id);
	public Collection<EvaluationRenderingModel> getEvaluationsByUserId(long id);
	public long getQuestionsSizeByUserId(long id);
	public long getAnswersSizeByUserId(long id);
	public long getReviewsSizeByUserId(long id);
	public long getEvaluationsSizeByUserId(long id);
	public UserModificationForm getUserModificationFormByUserId(long id);
}
