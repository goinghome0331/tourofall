package net.bulldozer.tourofall.answer.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class AnswerRegistrationForm {
	@NotEmpty(message = "내용을 입력해주세요.")
	@Size(max = 255, message = "최대 255까지 입력 가능합니다.")
	private String content;

	private long questionId;
	
	
	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
