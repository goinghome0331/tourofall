package net.bulldozer.tourofall.recommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.bulldozer.tourofall.dest.service.RecommenderService;
import net.bulldozer.tourofall.security.dto.UserAuthenticationDetails;

@Controller
@RequestMapping("/recommend")
public class RecommendationController {

	@Autowired
	private RecommenderService recommenderService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String showDestinationRecommendPage(@RequestParam(value="fin", required=false) String fin,Model model) throws Exception{
		UserAuthenticationDetails userAuthenticationDetails = (UserAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		model.addAttribute("recommendationRenderingModelForm", recommenderService.getRecommendations(userAuthenticationDetails.getId()));
		return "recommend";
	}
}