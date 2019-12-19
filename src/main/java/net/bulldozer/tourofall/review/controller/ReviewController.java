package net.bulldozer.tourofall.review.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.bulldozer.tourofall.common.dto.Response;
import net.bulldozer.tourofall.review.dto.Review;
import net.bulldozer.tourofall.review.dto.ReviewRegistrationForm;
import net.bulldozer.tourofall.review.service.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> processRegisterReview(@RequestBody @Valid ReviewRegistrationForm reviewRegistrationForm, BindingResult result) throws Exception {
		System.out.println("Entered processRegisterReview");
		if (result.hasErrors()) {
			List<ObjectError> errors =  result.getAllErrors();
		
			return new ResponseEntity<List<ObjectError>>(errors,HttpStatus.CONFLICT);
		}
		Review review = reviewService.registerNewReview(reviewRegistrationForm);
		if(review == null){
			return new ResponseEntity<Response>(new Response(false,"Review","등록이 실패되었습니다."),HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Response>(new Response(true,"Review","등록을 성공하였습니다."),HttpStatus.OK); 
	}
	
	@ResponseStatus(value=HttpStatus.CONFLICT,reason="이미 있는 리뷰 내용입니다. ")
	@ExceptionHandler(DataIntegrityViolationException.class)
	public String processingDuplicatedReviews(Model model,DataIntegrityViolationException ex){
		
		model.addAttribute("errorMsg", ex.getMessage());
		
		return "duplicated_review";
	}
}
