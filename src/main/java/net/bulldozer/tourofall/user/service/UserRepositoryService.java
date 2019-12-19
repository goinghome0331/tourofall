package net.bulldozer.tourofall.user.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.answer.dto.Answer;
import net.bulldozer.tourofall.answer.dto.AnswerRenderingModel;
import net.bulldozer.tourofall.answer.repository.AnswerRepository;
import net.bulldozer.tourofall.evaluation.dto.Evaluation;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRenderingModel;
import net.bulldozer.tourofall.evaluation.repository.EvaluationRepository;
import net.bulldozer.tourofall.question.dto.Question;
import net.bulldozer.tourofall.question.dto.QuestionRenderingModel;
import net.bulldozer.tourofall.question.repository.QuestionRepository;
import net.bulldozer.tourofall.review.dto.Review;
import net.bulldozer.tourofall.review.dto.ReviewRenderingModel;
import net.bulldozer.tourofall.review.repository.ReviewRepository;
import net.bulldozer.tourofall.user.dto.User;
import net.bulldozer.tourofall.user.dto.UserModificationForm;
import net.bulldozer.tourofall.user.dto.UserPreference;
import net.bulldozer.tourofall.user.dto.UserRegistrationForm;
import net.bulldozer.tourofall.user.repository.UserPreferenceRepository;
import net.bulldozer.tourofall.user.repository.UserRepository;


@Service
public class UserRepositoryService implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private UserPreferenceRepository userPreferenceRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private EvaluationRepository evaluationRepository;
	
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Transactional
	@Override
	public User registerNewUser(UserRegistrationForm registrationUserForm) throws DuplicateUsernameException{
		if(checkUsernameDuplicate(registrationUserForm.getUsername())){
			 throw new DuplicateUsernameException("The Username: " + registrationUserForm.getUsername() + " is already in use.");
		}
		
		String[] preferences = registrationUserForm.getUserPreferences();
		
		List<UserPreference> userPreferences = new ArrayList<UserPreference>();
		for(String preference : preferences){
			UserPreference userPreference = UserPreference.getBuilder()
											.itemCategoryCode(preference)
											.build();
			userPreferences.add(userPreference);	
		}
		User newUser = User.getBuilder()
				.username(registrationUserForm.getUsername())
				.firstName(registrationUserForm.getFirstName())
				.lastName(registrationUserForm.getLastName())
				.password(encoder.encode(registrationUserForm.getPassword()))
				.birth(registrationUserForm.getBirth())
				.gender(registrationUserForm.getGender())
				.signInProvider(registrationUserForm.getSignInProvider())
				.userPreferences(userPreferences)
				.build();
		
		System.out.println(newUser);
		// user의 role은 따로 건네주지 않음 : 기본적으로 ROLE_USER 권한을 부여받음
		return userRepository.save(newUser);
	}
	
	
	
	
	@Transactional(readOnly=true)
	public User getUserByUserId(long id){
		return userRepository.findOne(id);
	}
	@Transactional(readOnly=true)
	public User getUserByUsername(String username){
		return userRepository.findByUsername(username);
	}
	
	
	private boolean checkUsernameDuplicate(String username) {
		User user = userRepository.findByUsername(username);
		
		if(user != null)
			return true;
		
		return false;
	}
	
	@Transactional(readOnly=true)
	@Override
	public long getQuestionsSizeByUserId(long id){
		return questionRepository.countByUserId(id);
	}
	@Transactional(readOnly=true)
	@Override
	public long getAnswersSizeByUserId(long id){
		return answerRepository.countByUserId(id);
	}
	@Transactional(readOnly=true)
	@Override
	public long getReviewsSizeByUserId(long id){
		return reviewRepository.countByUserId(id);
	}
	@Transactional(readOnly=true)
	@Override
	public long getEvaluationsSizeByUserId(long id){
		return evaluationRepository.countByUserId(id);
	}
	
	
	
	@Transactional(readOnly=true)
	@Override
	public Collection<QuestionRenderingModel> getQuestionsByUserId(long id) {
		User user = userRepository.findOne(id);
		Collection<Question> questions = user.getQuestions();
		Collection<QuestionRenderingModel> questionRenderingModels = new ArrayList<QuestionRenderingModel>(); 
		for(Question question : questions){
			QuestionRenderingModel questionRenderingModel = QuestionRenderingModel.getBuilder()
															.userId(question.getUser().getId())
															.questionId(question.getId())
															.title(question.getTitle())
															.content(question.getContent())
															.createdDate(question.getCreatedDate())
															.lastName(question.getUser().getLastName())
															.firstName(question.getUser().getFirstName())
															.visitor(question.getVisitor())
															.build();
			questionRenderingModels.add(questionRenderingModel);
		}
		return questionRenderingModels;
	}
	
	@Transactional(readOnly=true)
	@Override
	public Collection<AnswerRenderingModel> getAnswersByUserId(long id){
		User user = userRepository.findOne(id);
		Collection<Answer> answers = user.getAnswers();
		Collection<AnswerRenderingModel> answerRenderingModels = new ArrayList<AnswerRenderingModel>();
		for(Answer answer : answers){
			AnswerRenderingModel answerRenderingModel = AnswerRenderingModel.getBuilder()
														.userId(user.getId())
														.lastName(answer.getUser().getLastName())
														.firstName(answer.getUser().getFirstName())
														.createdDate(answer.getCreatedDate())
														.questionTitle(answer.getQuestion().getTitle())
														.content(answer.getContent())
														.build();
			
			answerRenderingModels.add(answerRenderingModel);
		}
		
		return answerRenderingModels;
	}

	@Transactional(readOnly=true)
	@Override
	public Collection<ReviewRenderingModel> getReviewsByUserId(long id) {
		User user = userRepository.findOne(id);
		Collection<Review> reviews = user.getReviews();
		Collection<ReviewRenderingModel> reviewRenderingModels = new ArrayList<ReviewRenderingModel>();
		
		for(Review review : reviews){
			ReviewRenderingModel reviewRenderingModel = ReviewRenderingModel.getBuilder()
														.userId(user.getId())
														.title(review.getTitle())
														.content(review.getContent())
														.createdDate(review.getCreatedDate())
														.itemTitle(review.getItemTitle())
														.score(review.getEvaluation().getScore())
														.build();
			reviewRenderingModels.add(reviewRenderingModel);
		}
		return reviewRenderingModels;
	}

	@Transactional(readOnly=true)
	@Override
	public Collection<EvaluationRenderingModel> getEvaluationsByUserId(long id) {
		User user = userRepository.findOne(id);
		Collection<Evaluation> evaluations = user.getEvaluations();
		Collection<EvaluationRenderingModel> evaluationRenderingModels = new ArrayList<EvaluationRenderingModel>();
		
		for(Evaluation evaluation : evaluations){
			EvaluationRenderingModel evaluationRenderingModel = new EvaluationRenderingModel();
			evaluationRenderingModel.setItemId(evaluation.getItemId());
			evaluationRenderingModel.setScore(evaluation.getScore());
			evaluationRenderingModels.add(evaluationRenderingModel);
		}
		return evaluationRenderingModels;
	}
	
	@Transactional(readOnly=true)
	@Override
	public UserModificationForm getUserModificationFormByUserId(long id){
		User user = userRepository.findOne(id);
		
		List<UserPreference> userPreferences = userPreferenceRepository.findByUserId(id);
		String[] preferences = new String[userPreferences.size()];
		
		for(int i =0; i < preferences.length; i++){
			preferences[i] = userPreferences.get(i).getItemCategoryCode();
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(user.getBirth());
		
		UserModificationForm userModificationForm = UserModificationForm.getBuilder()
													.id(user.getId())
													.username(user.getUsername())
													.gender(user.getGender())
													.lastName(user.getLastName())
													.firstName(user.getFirstName())
													.signInProvider(user.getSignInProvider())
													.userPreferences(preferences)
													.year(Integer.toString(cal.get(Calendar.YEAR)))
													.month(Integer.toString(cal.get(Calendar.MONTH)+1))
													.date(Integer.toString(cal.get(Calendar.DAY_OF_MONTH)))
													.build();
		
		return userModificationForm;
	}
}
