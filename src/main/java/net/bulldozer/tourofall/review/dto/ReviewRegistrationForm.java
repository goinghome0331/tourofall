package net.bulldozer.tourofall.review.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class ReviewRegistrationForm {
	@NotEmpty(message="제목을 입력해주세요.")
	@Size(max=45, message = "최대 45까지 입력 가능합니다.")
	private String title;
	
	@NotEmpty(message="내용을 입력해주세요")
	@Size(max=255, message = "최대 255까지 입력 가능합니다.")
	private String content;
	
	@Range(min=0,max=5,message="최대 0~5점까지 입력 가능합니다.")
	private double score;
	
	private int itemId;

	public ReviewRegistrationForm() {
	}

	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
}
