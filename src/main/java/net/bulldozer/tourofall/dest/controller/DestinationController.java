package net.bulldozer.tourofall.dest.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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

import net.bulldozer.tourofall.dest.service.TourApiService;
import net.bulldozer.tourofall.evaluation.service.EvaluationService;
import net.bulldozer.tourofall.question.dto.QuestionRenderingModelsSet;
import net.bulldozer.tourofall.question.service.QuestionService;
import net.bulldozer.tourofall.review.dto.ReviewRenderingModelsSet;
import net.bulldozer.tourofall.review.service.ReviewService;

@Controller
@RequestMapping("/dest")
public class DestinationController {
	@Autowired
	private TourApiService tourApiService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private EvaluationService evaluationService;
	
	
	
	@RequestMapping(value="/info/reviewmore", produces="application/json",method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getReviewRenderingModels(@RequestParam(value="itemId") int itemId, @RequestParam(value="index") int index){
		ReviewRenderingModelsSet reviewRenderingModelsSet = reviewService.getReviewRenderingModelsSet(itemId, index); 
		return new ResponseEntity<ReviewRenderingModelsSet>(reviewRenderingModelsSet, HttpStatus.OK);
	}
	@RequestMapping(value="/info/questions", produces="application/json",method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getQuestionRenderingModels(@RequestParam(value="itemId") int itemId, @RequestParam(value="pageNo") int pageNo){
		QuestionRenderingModelsSet questionRenderingModelsSet = questionService.getQuestionRenderingModelsSet(itemId, pageNo);
		return new ResponseEntity<QuestionRenderingModelsSet>(questionRenderingModelsSet,HttpStatus.OK);
	}
	@RequestMapping("/info/{itemId}")
	public String showDestinationInfo(@PathVariable int itemId, Model model) throws Exception{
		JSONObject body1 = tourApiService.getBasicInfo(itemId);
		int itemTypeId = 0;
		if (body1 != null) {
			JSONObject items = (JSONObject) body1.get("items");
			JSONObject item = (JSONObject) items.get("item");
			long contentTypeId = (Long)item.get("contenttypeid");
			itemTypeId = (int)contentTypeId;
			model.addAttribute("basicInfo", item);
		}
		
		JSONObject body2 = tourApiService.getIntroInfo(itemId, itemTypeId);
		if (body2 != null) {
			JSONObject items = (JSONObject) body2.get("items");
			JSONObject item = (JSONObject) items.get("item");
			model.addAttribute("introInfo", item);
		}
		
		JSONObject body3 = tourApiService.getDetailInfo(itemId, itemTypeId);
		if (body3 != null) {
			JSONObject items = (JSONObject) body3.get("items");

			if (((Long) body3.get("totalCount")) != 1) {
				JSONArray item = (JSONArray) items.get("item");
				model.addAttribute("detailInfoes", item);
				System.out.println("detailInfoes selected");
			} else {
				JSONObject item = (JSONObject) items.get("item");
				model.addAttribute("detailInfo", item);
				System.out.println("detailInfo selected");
			}
		}
		
		JSONObject body4 = tourApiService.getImageInfo(itemId, itemTypeId);
		if (body4 != null) {
			JSONObject items = (JSONObject) body4.get("items");

			if (((Long) body4.get("totalCount")) != 1) {
				JSONArray item = (JSONArray) items.get("item");
				model.addAttribute("imageInfoes", item);
				System.out.println("imageInfoes selected");
			} else {
				JSONObject item = (JSONObject) items.get("item");
				model.addAttribute("imageInfo", item);
				System.out.println("imageInfo selected");
			}
		}
		model.addAttribute("itemId", itemId);
		model.addAttribute("evaluationSize", evaluationService.getEvaluationCountByItemId(itemId));
		model.addAttribute("reviewSize", reviewService.getReviewCountByItemId(itemId));
		model.addAttribute("questionSize", questionService.getQuestionCountByItemId(itemId));
		model.addAttribute("evaluationMean", evaluationService.getEvaluationMeanByItemId(itemId));
		model.addAttribute("reviewRenderingModelsSet", reviewService.getReviewRenderingModelsSet(itemId, 0));
		model.addAttribute("questionRenderingModelsSet", questionService.getQuestionRenderingModelsSet(itemId, 1));
		return "dest-info";
	}
}
