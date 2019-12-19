package net.bulldozer.tourofall.review.dto;

import java.util.List;

public class UserReviewRenderingModelsSet {
	private List<UserReviewRenderingModel> userReviewRenderingModels;
	private boolean nextIndex;
	
	public UserReviewRenderingModelsSet(){}

	public UserReviewRenderingModelsSet(List<UserReviewRenderingModel> userReviewRenderingModels, boolean nextIndex) {
		this.userReviewRenderingModels = userReviewRenderingModels;
		this.nextIndex = nextIndex;
	}

	public List<UserReviewRenderingModel> getUserReviewRenderingModels() {
		return userReviewRenderingModels;
	}

	public void setUserReviewRenderingModels(List<UserReviewRenderingModel> userReviewRenderingModels) {
		this.userReviewRenderingModels = userReviewRenderingModels;
	}

	public boolean getNextIndex() {
		return nextIndex;
	}

	public void setNextIndex(boolean nextIndex) {
		this.nextIndex = nextIndex;
	}
}
