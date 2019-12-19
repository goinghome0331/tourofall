package net.bulldozer.tourofall.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.bulldozer.tourofall.answer.dto.AnswerRenderingModelsSet;
import net.bulldozer.tourofall.answer.service.AnswerService;
import net.bulldozer.tourofall.common.dto.Response;
import net.bulldozer.tourofall.dest.service.TourApiService;
import net.bulldozer.tourofall.evaluation.dto.Evaluation;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRenderingModel;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRenderingModelsSet;
import net.bulldozer.tourofall.evaluation.service.EvaluationService;
import net.bulldozer.tourofall.question.dto.UserQuestionRenderingModelsSet;
import net.bulldozer.tourofall.question.service.QuestionService;
import net.bulldozer.tourofall.review.dto.UserReviewRenderingModel;
import net.bulldozer.tourofall.review.dto.UserReviewRenderingModelsSet;
import net.bulldozer.tourofall.review.service.ReviewService;
import net.bulldozer.tourofall.user.dto.User;
import net.bulldozer.tourofall.user.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private UserService userService;

	@Autowired
	private EvaluationService evaluationService;
	
	@Autowired
	private TourApiService tourApiService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private QuestionService questionService;
	
	private void setCountToHeader(long userId, Model model){
		model.addAttribute("questionCount", userService.getQuestionsSizeByUserId(userId));
		model.addAttribute("answerCount", userService.getAnswersSizeByUserId(userId));
		model.addAttribute("reviewCount", userService.getReviewsSizeByUserId(userId));
		model.addAttribute("evaluationCount", userService.getEvaluationsSizeByUserId(userId));
	}
	private void setImageUrlToHeader(long userId, Model model){
		List<Evaluation> evaluations = evaluationService.findByUserId(userId);
		int selected = (int)(Math.random()*evaluations.size());
		String imageUrl = null;
		
		if(selected != 0){
			Evaluation evaluation = evaluations.get(selected);
		
			try {
				imageUrl = tourApiService.getItemImage(evaluation.getItemId());
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		model.addAttribute("imageUrl", imageUrl);
	}
	private void setUserInfoToHeader(long userId, Model model){
		User user = userService.getUserByUserId(userId);
		model.addAttribute("username", user.getLastName()+user.getFirstName());
	}
	private void setEvaluationRenderingModelsSet(long userId, int pageNo, Model model) throws Exception{
		EvaluationRenderingModelsSet evaluationRenderingModelsSet = evaluationService.getEvaluationRenderingModelsSet(userId, pageNo);
		
		model.addAttribute("evaluationRenderingModelsSet", evaluationRenderingModelsSet);
		int count = 0;
		for(EvaluationRenderingModel evaluationRenderingModel : evaluationRenderingModelsSet.getEvaluationRenderingModels()){
			model.addAttribute("evaluationRenderingModel"+count, evaluationRenderingModel);
			count++;
		}
		System.out.println("EvaluationRenderingModels"+count);
	}
	
	
	
	private void setUserReviewRenderingModelsSet(long userId, int index, Model model) throws Exception{
		UserReviewRenderingModelsSet userReviewRenderingModelsSet = reviewService.getUserReviewRenderingModelsSet(userId, index);
		
		model.addAttribute("userReviewRenderingModelsSet", userReviewRenderingModelsSet);
		int count = 0;
		for(UserReviewRenderingModel userReviewRenderingModel : userReviewRenderingModelsSet.getUserReviewRenderingModels()){
			model.addAttribute("userReviewRenderingModel"+count, userReviewRenderingModel);
			count++;
		}
		System.out.println("userReviewRenderingModels : " + count);
	}
	
	private void setAnswerRenderingModelsSet(long userId, int index, Model model) throws Exception{
		AnswerRenderingModelsSet answerRenderingModelsSet = answerService.getAnswerRenderingModelsSet(userId, index);
		model.addAttribute("answerRenderingModelsSet", answerRenderingModelsSet);
	}
	
	private void setUserQuestionRenderingModelsSet(long userId, int index, Model model) throws Exception {
		UserQuestionRenderingModelsSet userQuestionRenderingModelsSet = questionService.getUserQuestionRenderingModelsSet(userId, index);
		model.addAttribute("userQuestionRenderingModelsSet", userQuestionRenderingModelsSet);
	}
	@RequestMapping(value="/{userId}", method=RequestMethod.GET)
	public String showUserInfo(@PathVariable long userId, Model model) {
		try{
			setEvaluationRenderingModelsSet(userId,1,model);
			setUserReviewRenderingModelsSet(userId,0,model);
			setAnswerRenderingModelsSet(userId,0,model);
			setUserQuestionRenderingModelsSet(userId, 0, model);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		model.addAttribute("userId", userId);
		setUserInfoToHeader(userId, model);
		setCountToHeader(userId, model);
		setImageUrlToHeader(userId,model);
		
		return "userspage";
	}
	@RequestMapping(value="/{userId}/reviewmore", produces="application/json",method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getReviewRenderingModelsSet(@PathVariable int userId, @RequestParam(value="index") int index) throws Exception{
		UserReviewRenderingModelsSet userReviewRenderingModelsSet = reviewService.getUserReviewRenderingModelsSet(userId, index); 
		return new ResponseEntity<UserReviewRenderingModelsSet>(userReviewRenderingModelsSet, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{userId}/usereval", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getEvaluationRenderingModelsSet(@RequestParam(value="userId") int itemId, @RequestParam(value="pageNo") int pageNo) throws Exception{
		EvaluationRenderingModelsSet evaluationRenderingModelsSet = evaluationService.getEvaluationRenderingModelsSet(itemId, pageNo);
		return new ResponseEntity<EvaluationRenderingModelsSet>(evaluationRenderingModelsSet,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{userId}/answermore", produces="application/json",method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getAnswerRenderingModelsSet(@PathVariable long userId, @RequestParam(value="index") int index) throws Exception{
		AnswerRenderingModelsSet answerRenderingModelsSet = answerService.getAnswerRenderingModelsSet(userId, index); 
		return new ResponseEntity<AnswerRenderingModelsSet>(answerRenderingModelsSet, HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteanswer/{answerId}")
	public @ResponseBody ResponseEntity<?> deleteAnswer(@PathVariable long answerId){
		try{
			answerService.deleteAnswer(answerId);
		}catch(Exception e){
			return new ResponseEntity<Response>(new Response(false,"Answer","�亯�� ���� �����Ͽ����ϴ�."),HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Response>(new Response(true,"Answer","�亯�� ���� �Ϸ� �Ǿ����ϴ�."),HttpStatus.OK);
	}
}
