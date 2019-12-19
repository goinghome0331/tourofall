package net.bulldozer.tourofall.answer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.answer.dto.Answer;
import net.bulldozer.tourofall.answer.dto.AnswerRegistrationForm;
import net.bulldozer.tourofall.answer.dto.AnswerRenderingModel;
import net.bulldozer.tourofall.answer.dto.AnswerRenderingModelsSet;
import net.bulldozer.tourofall.answer.repository.AnswerRepository;
import net.bulldozer.tourofall.question.dto.Question;
import net.bulldozer.tourofall.question.dto.QuestionRenderingModel;
import net.bulldozer.tourofall.question.repository.QuestionRepository;
import net.bulldozer.tourofall.security.dto.UserAuthenticationDetails;
import net.bulldozer.tourofall.user.dto.User;
import net.bulldozer.tourofall.user.repository.UserRepository;

@Service
public class AnswerRepositoryService implements AnswerService {
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Transactional
	@Override
	public AnswerRenderingModel registerNewAnswer(AnswerRegistrationForm answerRegistrationForm) {
		Question question = questionRepository.findOne(answerRegistrationForm.getQuestionId());
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findOne(userAuthenticationDetails.getId()); 
		
		Answer answer = Answer.getBuilder()
				.content(answerRegistrationForm.getContent())
				.build();
		
		
		user.addAnswer(answer);
		question.addAnswer(answer);
		
		if(answerRepository.save(answer) == null){
			return null;
		}
		AnswerRenderingModel answerRenderingModel = AnswerRenderingModel.getBuilder()
				.userId(user.getId())
				.lastName(answer.getUser().getLastName())
				.firstName(answer.getUser().getFirstName())
				.createdDate(answer.getCreatedDate())
				.questionTitle(answer.getQuestion().getTitle())
				.content(answer.getContent())
				.build();
		
		return answerRenderingModel;
	}
	@Transactional
	@Override
	public void deleteAnswer(long answerId){
		Answer answer = answerRepository.findOne(answerId);
		if(answer == null){
			System.out.println("answer is null");
		}
		answerRepository.delete(answer);
	}
	@Transactional(readOnly=true)
	@Override
	public AnswerRenderingModelsSet getAnswerRenderingModelsSet(long userId, int index){
		List<Answer> answers = answerRepository.findByUserId(userId);
		List<AnswerRenderingModel> answerRenderingModels = new ArrayList<AnswerRenderingModel>(); 
		AnswerRenderingModelsSet answerRenderingModelsSet = new AnswerRenderingModelsSet();
		
		
		for(int i= 5*index; i < 5*index+5; i++){
			Answer answer = null;
			
			try{
				answer = answers.get(i);
			}catch(IndexOutOfBoundsException iobe){
				break;
			}
			AnswerRenderingModel answerRenderingModel = AnswerRenderingModel.getBuilder()
													.answerId(answer.getId())
													.userId(userId)
													.lastName(answer.getUser().getLastName())
													.firstName(answer.getUser().getFirstName())
													.createdDate(answer.getCreatedDate())
													.content(answer.getContent())
													.questionId(answer.getQuestion().getId())
													.questionTitle(answer.getQuestion().getTitle())
													.questionCreatedDate(answer.getQuestion().getCreatedDate())
													.build();
			
			answerRenderingModels.add(answerRenderingModel);
		}
		System.out.println(answers.size());
		boolean nextIndex = answers.size() > (index+1)*5;
		answerRenderingModelsSet.setAnswerRenderingModels(answerRenderingModels);
		answerRenderingModelsSet.setNextIndex(nextIndex);
		
		return answerRenderingModelsSet;
	}
}
