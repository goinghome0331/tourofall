package net.bulldozer.tourofall.dest.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchResultRenderingModelsSet {
	private List<SearchResultRenderingModel> searchResultRenderingModels; 
	private int totalCount;
	private int pageNo;
	private int totalPage;
	private List<Integer> numList;
}
