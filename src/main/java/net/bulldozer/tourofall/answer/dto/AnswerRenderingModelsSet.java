package net.bulldozer.tourofall.answer.dto;

import java.util.List;

public class AnswerRenderingModelsSet {
	private List<AnswerRenderingModel> answerRenderingModels;
	private boolean nextIndex;
	
	
	public AnswerRenderingModelsSet(){}


	public AnswerRenderingModelsSet(List<AnswerRenderingModel> answerRenderingModels, boolean nextIndex) {
		this.answerRenderingModels = answerRenderingModels;
		this.nextIndex = nextIndex;
	}


	public List<AnswerRenderingModel> getAnswerRenderingModels() {
		return answerRenderingModels;
	}


	public void setAnswerRenderingModels(List<AnswerRenderingModel> answerRenderingModels) {
		this.answerRenderingModels = answerRenderingModels;
	}


	public boolean getNextIndex() {
		return nextIndex;
	}


	public void setNextIndex(boolean nextIndex) {
		this.nextIndex = nextIndex;
	}
	
	
}
