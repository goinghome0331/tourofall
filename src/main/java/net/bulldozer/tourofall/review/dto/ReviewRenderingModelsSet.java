package net.bulldozer.tourofall.review.dto;

import java.util.List;

public class ReviewRenderingModelsSet {
	private List<ReviewRenderingModel> reviewRenderingModels;
	private boolean nextIndex;
	
	public ReviewRenderingModelsSet(){}

	public ReviewRenderingModelsSet(List<ReviewRenderingModel> reviewRenderingModels, boolean nextIndex) {
		this.reviewRenderingModels = reviewRenderingModels;
		this.nextIndex = nextIndex;
	}

	public List<ReviewRenderingModel> getReviewRenderingModels() {
		return reviewRenderingModels;
	}

	public void setReviewRenderingModels(List<ReviewRenderingModel> reviewRenderingModels) {
		this.reviewRenderingModels = reviewRenderingModels;
	}

	public boolean getNextIndex() {
		return nextIndex;
	}

	public void setNextIndex(boolean nextIndex) {
		this.nextIndex = nextIndex;
	}
}
