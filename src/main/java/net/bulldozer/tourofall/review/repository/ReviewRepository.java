package net.bulldozer.tourofall.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bulldozer.tourofall.evaluation.dto.Evaluation;
import net.bulldozer.tourofall.review.dto.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	public List<Review> findByItemId(int itemId);
	public Review findByUserIdAndItemId(long id,int itemId);
	public List<Review> findByUserId(long userId);
	public long countByUserId(long userId);
}
