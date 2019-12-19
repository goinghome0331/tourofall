package net.bulldozer.tourofall.evaluation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bulldozer.tourofall.evaluation.dto.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long>{
	public Evaluation findByUserIdAndItemId(long id,int itemId);
	public List<Evaluation> findByItemId(int itemId);
	public List<Evaluation> findByUserId(long userId);
	public long countByUserId(long userId);
}
