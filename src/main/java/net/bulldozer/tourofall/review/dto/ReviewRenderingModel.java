package net.bulldozer.tourofall.review.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRenderingModel {
	private long userId;
	private String title;
	private String lastName;
	private String firstName;
	private String content;
	private Date createdDate;
	private double score;
	private String itemTitle;
	
	public static Builder getBuilder(){
		return new Builder();
	}
	public static class Builder{
		private ReviewRenderingModel reviewRenderingModel;
		
		public Builder(){
			reviewRenderingModel = new ReviewRenderingModel();
		}
		public Builder userId(long userId){
			reviewRenderingModel.userId = userId;
			return this;
		}
		public Builder title(String title){
			reviewRenderingModel.title = title;
			return this;
		}
		public Builder content(String content){
			reviewRenderingModel.content = content;
			return this;
		}
		public Builder lastName(String lastName){
			reviewRenderingModel.lastName = lastName;
			return this;
		}
		public Builder firstName(String firstName){
			reviewRenderingModel.firstName = firstName;
			return this;
		}
		public Builder createdDate(Date createdDate){
			reviewRenderingModel.createdDate = createdDate;
			return this;
		}
		public Builder score(double score){
			reviewRenderingModel.score = score;
			return this;
		}
		public Builder itemTitle(String itemTitle){
			reviewRenderingModel.itemTitle = itemTitle;
			return this;
		}
		public ReviewRenderingModel build(){
			return reviewRenderingModel;
		}
	}
}
