package net.bulldozer.tourofall.review.service;

import net.bulldozer.tourofall.review.dto.Review;
import net.bulldozer.tourofall.review.dto.ReviewRegistrationForm;
import net.bulldozer.tourofall.review.dto.ReviewRenderingModel;
import net.bulldozer.tourofall.review.dto.ReviewRenderingModelsSet;
import net.bulldozer.tourofall.review.dto.UserReviewRenderingModelsSet;

public interface ReviewService {
	public Review registerNewReview(ReviewRegistrationForm reviewRegistrationForm) ;
	public Review findByUserIdAndItemId(long id,int itemId);
	public int getReviewCountByItemId(int itemId);
	public ReviewRenderingModelsSet getReviewRenderingModelsSet(int itemId, int index);
	public UserReviewRenderingModelsSet getUserReviewRenderingModelsSet(long userId, int index) throws Exception;
}
