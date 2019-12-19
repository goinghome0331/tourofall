package net.bulldozer.tourofall.question.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.answer.dto.Answer;
import net.bulldozer.tourofall.answer.dto.AnswerRenderingModel;
import net.bulldozer.tourofall.question.dto.QnARenderingModelsSet;
import net.bulldozer.tourofall.question.dto.Question;
import net.bulldozer.tourofall.question.dto.QuestionRegistrationForm;
import net.bulldozer.tourofall.question.dto.QuestionRenderingModel;
import net.bulldozer.tourofall.question.dto.QuestionRenderingModelsSet;
import net.bulldozer.tourofall.question.dto.UserQuestionRenderingModelsSet;
import net.bulldozer.tourofall.question.repository.QuestionRepository;
import net.bulldozer.tourofall.security.dto.UserAuthenticationDetails;
import net.bulldozer.tourofall.user.dto.User;
import net.bulldozer.tourofall.user.repository.UserRepository;

@Service
public class QuestionRepositoryService implements QuestionService {
	private static final int PAGE_COUNT = 5;
	
	@Autowired
	private QuestionRepository questionRepository;

	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Question getQuestionById(long questionId){
		return questionRepository.findOne(questionId);
	}
	@Transactional(readOnly=true)
	@Override
	public List<AnswerRenderingModel> getAnswerRenderingModelsByQuestionId(long questionId){
		List<AnswerRenderingModel> answerRenderingModels = new ArrayList<AnswerRenderingModel>();
		Question question = questionRepository.findOne(questionId);
		Collection<Answer> answers = question.getAnswers();
		
		Iterator<Answer> answerIter = answers.iterator();
		while(answerIter.hasNext()){
			Answer answer = answerIter.next();
			AnswerRenderingModel answerRenderingModel = AnswerRenderingModel.getBuilder()
					.userId(answer.getUser().getId())
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
	public List<QuestionRenderingModel> getQuestionRenderingModelsByItemId(int itemId) {
		List<QuestionRenderingModel> questionRenderingModels = new ArrayList<QuestionRenderingModel>();
		
		List<Question> questions = questionRepository.findByItemId(itemId);
		
		Iterator<Question> questionIter = questions.iterator();
		while(questionIter.hasNext()){
			Question question = questionIter.next();
			
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
	public QuestionRenderingModel getQuestionRenderingModelById(long questionId) {
		Question question = questionRepository.findOne(questionId);
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
		
		return questionRenderingModel;
	}
	@Transactional
	@Override
	public Question registerNewQuestion(QuestionRegistrationForm registrationQuestionForm) {
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User getUser = userRepository.findOne(userAuthenticationDetails.getId());
		
		Question question = Question.getBuilder()
				.title(registrationQuestionForm.getTitle())
				.content(registrationQuestionForm.getContent())
				.itemId(registrationQuestionForm.getItemId())
				.build();
		
		getUser.addQuestion(question);
		
		return questionRepository.save(question);
	}
	
	@Transactional
	@Override
	public void incrementVisitor(long questionId) {
		Question question = questionRepository.findOne(questionId);
		question.incrementVisitor();
	}
	
	@Transactional(readOnly=true)
	@Override
	public int getQuestionCountByItemId(int itemId){
		List<Question> questions = questionRepository.findByItemId(itemId);
		return questions.size();
	}
	
	
	@Transactional(readOnly=true)
	@Override
	public QnARenderingModelsSet getQnARenderingModelsSet(long questionId){
		
		Question question = questionRepository.findOne(questionId);
		if(question == null){
			return null;
		}
		Collection<Answer> answers = question.getAnswers();
		
		
		List<AnswerRenderingModel> answerRenderingModels = new ArrayList<AnswerRenderingModel>();
		QnARenderingModelsSet qnARenderingModelsSet = new QnARenderingModelsSet();  
		
		
		QuestionRenderingModel questionRenderingModel = QuestionRenderingModel.getBuilder()
				.userId(question.getUser().getId())
				.questionId(question.getId())
				.answerCount(answers.size())
				.title(question.getTitle())
				.content(question.getContent())
				.createdDate(question.getCreatedDate())
				.lastName(question.getUser().getLastName())
				.firstName(question.getUser().getFirstName())
				.visitor(question.getVisitor())
				.build();
		
		
		Iterator<Answer> answerIter = answers.iterator();
		while(answerIter.hasNext()){
			Answer answer = answerIter.next();
			
			AnswerRenderingModel answerRenderingModel = AnswerRenderingModel.getBuilder()
					.userId(answer.getUser().getId())
					.lastName(answer.getUser().getLastName())
					.firstName(answer.getUser().getFirstName())
					.createdDate(answer.getCreatedDate())
					.content(answer.getContent())
					.build();
			
			answerRenderingModels.add(answerRenderingModel);
		}
		qnARenderingModelsSet.setQuestionRenderingModel(questionRenderingModel);
		qnARenderingModelsSet.setAnswerRenderingModels(answerRenderingModels);
		
		return qnARenderingModelsSet;
	}
	
	@Transactional(readOnly=true)
	@Override
	public UserQuestionRenderingModelsSet getUserQuestionRenderingModelsSet(long userId, int index){
		List<Question> questions = questionRepository.findByUserId(userId);
		List<QuestionRenderingModel> questionRenderingModels = new ArrayList<QuestionRenderingModel>();
		UserQuestionRenderingModelsSet userQuestionRenderingModelsSet = new UserQuestionRenderingModelsSet();
		
		for(int i=0; i < questions.size();i++){
			Question question = null;
			try{
				question = questions.get(i);
			}catch(IndexOutOfBoundsException iobe){
				break;
			}
			QuestionRenderingModel questionRenderingModel = QuestionRenderingModel.getBuilder()
					.userId(question.getUser().getId())
					.questionId(question.getId())
					.answerCount(question.getAnswers().size())
					.title(question.getTitle())
					.content(question.getContent())
					.createdDate(question.getCreatedDate())
					.lastName(question.getUser().getLastName())
					.firstName(question.getUser().getFirstName())
					.visitor(question.getVisitor())
					.build();
			questionRenderingModels.add(questionRenderingModel);
		}
		userQuestionRenderingModelsSet.setNextIndext(false);
		userQuestionRenderingModelsSet.setQuestionRenderingModels(questionRenderingModels);
		
		return userQuestionRenderingModelsSet;
	}
	@Transactional(readOnly=true)
	@Override
	public QuestionRenderingModelsSet getQuestionRenderingModelsSet(int itemId, int pageNo) {
		List<QuestionRenderingModel> questionRenderingModels = new ArrayList<QuestionRenderingModel>();
		List<Question> questions = questionRepository.findByItemId(itemId);
		QuestionRenderingModelsSet questionRenderingModelsSet = new QuestionRenderingModelsSet();  
		
		int index = pageNo-1;
		int totalCount = questions.size();
		int listIndex = index/PAGE_COUNT; // 5 = PAGE에 나오는 인덱스 수
		int totalPage = totalCount/questionRenderingModelsSet.getNumOfRows();
		if(totalCount%questionRenderingModelsSet.getNumOfRows() != 0)
			totalPage++;
		
		
		int listTotal = totalPage / PAGE_COUNT;
		
		List<Integer> indexList = new ArrayList<Integer>();
		
		if(listIndex == listTotal && totalPage%PAGE_COUNT != 0){
			for(int i = 1 + listIndex*5; i <= listIndex*5 + totalPage%PAGE_COUNT; i++){
				indexList.add(i);
			}
		}else{
			if(totalCount != 0){
				for(int i = 1 + listIndex*5; i <= 5 + listIndex*5; i++){
					indexList.add(i);
				}
			}
		}
		
		
		for(int i = index*5; i < index*5+5; i++){
			Question question = null;
			try{
				question = questions.get(i);
			}catch(IndexOutOfBoundsException iobe){
				break;
			}
			QuestionRenderingModel questionRenderingModel = QuestionRenderingModel.getBuilder()
					.userId(question.getUser().getId())
					.questionId(question.getId())
					.answerCount(question.getAnswers().size())
					.title(question.getTitle())
					.content(question.getContent())
					.createdDate(question.getCreatedDate())
					.lastName(question.getUser().getLastName())
					.firstName(question.getUser().getFirstName())
					.visitor(question.getVisitor())
					.build();
			
			questionRenderingModels.add(questionRenderingModel);
		}  
		questionRenderingModelsSet.setQuestionRenderingModels(questionRenderingModels);
		questionRenderingModelsSet.setPageNo(pageNo);
		questionRenderingModelsSet.setTotalPage(totalPage);
		questionRenderingModelsSet.setIndexList(indexList);
		return questionRenderingModelsSet;
	}

}
