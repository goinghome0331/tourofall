package net.bulldozer.tourofall.question.dto;

import java.util.List;

public class QuestionRenderingModelsSet {
	private List<QuestionRenderingModel> questionRenderingModels;
	private final int numOfRows = 5;
	private int pageNo;
	private int totalPage;
	private List<Integer> indexList;
	
	public QuestionRenderingModelsSet(){}



	public QuestionRenderingModelsSet(List<QuestionRenderingModel> questionRenderingModels, int pageNo, int totalPage,
			List<Integer> indexList) {
		this.questionRenderingModels = questionRenderingModels;
		this.pageNo = pageNo;
		this.totalPage = totalPage;
		this.indexList = indexList;
	}



	public int getPageNo() {
		return pageNo;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}


	

	public int getTotalPage() {
		return totalPage;
	}



	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}



	public List<Integer> getIndexList() {
		return indexList;
	}


	public void setIndexList(List<Integer> indexList) {
		this.indexList = indexList;
	}


	public int getNumOfRows() {
		return numOfRows;
	}


	public List<QuestionRenderingModel> getQuestionRenderingModels() {
		return questionRenderingModels;
	}

	public void setQuestionRenderingModels(List<QuestionRenderingModel> questionRenderingModels) {
		this.questionRenderingModels = questionRenderingModels;
	}
}
