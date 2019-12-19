package net.bulldozer.tourofall.dest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.bulldozer.tourofall.dest.dto.SearchResultRenderingModel;
import net.bulldozer.tourofall.dest.dto.SearchResultRenderingModelsSet;
import net.bulldozer.tourofall.dest.service.TourApiService;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	private TourApiService tourApiService;
	

	@RequestMapping(method = RequestMethod.GET)
	public String simpleSearch(@RequestParam(value = "s", required = false) String query, @RequestParam(value="p",defaultValue="1") String pageNum, Model model) throws Exception {
		if (query != null) {
			SearchResultRenderingModelsSet searchResultRenderingModelsSet = tourApiService. getSearchResult(query,pageNum);
			
			model.addAttribute("query", query);
			model.addAttribute("searchResultRenderingModelsSet", searchResultRenderingModelsSet);
			
			int count=0;
			for(SearchResultRenderingModel searchResultRenderingModel : searchResultRenderingModelsSet.getSearchResultRenderingModels()){
				model.addAttribute("searchResultRenderingModel"+count, searchResultRenderingModel);
				count++;
			}
			
		}
		return "search";
	}
}
