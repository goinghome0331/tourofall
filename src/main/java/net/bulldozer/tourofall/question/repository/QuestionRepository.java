package net.bulldozer.tourofall.question.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bulldozer.tourofall.question.dto.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	public List<Question> findByItemId(int itemId);
	public long countByUserId(long userId);
	public List<Question> findByUserId(long userId);
}
