package net.bulldozer.tourofall.answer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bulldozer.tourofall.answer.dto.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
	long countByUserId(long userId);
	List<Answer> findByUserId(long userId);
}
