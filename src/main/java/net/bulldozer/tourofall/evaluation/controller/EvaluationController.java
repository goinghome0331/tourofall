package net.bulldozer.tourofall.evaluation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.bulldozer.tourofall.common.dto.Response;
import net.bulldozer.tourofall.dest.service.TourApiService;
import net.bulldozer.tourofall.evaluation.dto.Evaluation;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRegistration;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRenderingModel;
import net.bulldozer.tourofall.evaluation.service.EvaluationService;

@Controller
@RequestMapping("/eval")
public class EvaluationController {
	
	@Autowired
	private EvaluationService evaluationService;
	
	
	@Autowired
	private TourApiService tourApiService;
	
	@RequestMapping(value="/evalmore", method=RequestMethod.GET)
	public String showEvaluationRegistrationsFormPage(@RequestParam(value="itemCat1", defaultValue="A01") String  itemCat1, @RequestParam(value="itemCat2", defaultValue="A0101") String itemCat2, @RequestParam(value="pageNo", defaultValue="1") int pageNo,  Model model) throws Exception{
		model.addAttribute("currentItemCat1", itemCat1);
		model.addAttribute("currentItemCat2", itemCat2);
		model.addAttribute("currentPageNo",pageNo);
		model.addAttribute("evaluationRenderingModels", tourApiService.getEvaluationRenderingModels(itemCat1, itemCat2, pageNo));
		return "evalmore";
	}
	
	@RequestMapping(value="/evalmore", produces="application/json", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> processEvaluationRegistration(@RequestBody EvaluationRegistration evaluationRegistration){
		System.out.println("Entered processing Evalatuion");
		
		Evaluation evaluation = evaluationService.registerNewEvaluation(evaluationRegistration);
		if(evaluation == null){
			return new ResponseEntity<Response>(new Response(false,"Evaluation","�� ������ �����Ͽ����ϴ�."), HttpStatus.CONFLICT);
		}		
		return new ResponseEntity<Response>(new Response(true,"Evaluation","�򰡰� ����Ǿ����ϴ�."), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/moreeval", produces="application/json", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getEvaluationModels(@RequestParam(value="page") int pageNum, @RequestParam(value="cat1") String cat1, @RequestParam(value="cat2") String cat2) throws Exception{
		System.out.println("Entered getEvaluationModels");
		
		List<EvaluationRenderingModel> evaluationRenderingModels =  tourApiService.getEvaluationRenderingModels(cat1, cat2, pageNum);
		
		
		if(evaluationRenderingModels.size() == 0){
			return new ResponseEntity<Response>(new Response(false,"Evaluation","�� ��� ���� ���� ���� �����Ͽ����ϴ�."), HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<EvaluationRenderingModel>>(evaluationRenderingModels,HttpStatus.OK);
	}
}
