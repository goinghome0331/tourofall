package net.bulldozer.tourofall.review.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserReviewRenderingModel extends  ReviewRenderingModel{
	private int itemId;
	private String address;
	private String itemTitle;
	private String imageUrl;
	private double myScore;
	
	public UserReviewRenderingModel(long userId, String title,String lastName,String firstName, String content, Date createdDate, double score, String itemTitle, int itemId){
		super(userId,title,lastName,firstName,content,createdDate,score,itemTitle);
		this.itemId = itemId;
	}
	public static ExtendedBuilder getExtendedBuilder(){
		return new ExtendedBuilder();
	}
	public void setItemTitle(String itemTitle){
		this.itemTitle = itemTitle;
	}
	public void setAddress(String address){
		this.address = address;
	}
	
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	public void setMyScore(double myScore){
		this.myScore = myScore;
	}
	
	
	public static class ExtendedBuilder{
		private long userId;
		private String title;
		private String lastName;
		private String firstName;
		private String content;
		private Date createdDate;
		private double score;
		private String itemTitle;
		private int itemId;
		public ExtendedBuilder(){
		}
		public ExtendedBuilder userId(long userId){
			this.userId = userId;
			return this;
		}
		public ExtendedBuilder title(String title){
			this.title = title;
			return this;
		}
		public ExtendedBuilder content(String content){
			this.content = content;
			return this;
		}
		public ExtendedBuilder lastName(String lastName){
			this.lastName = lastName;
			return this;
		}
		public ExtendedBuilder firstName(String firstName){
			this.firstName = firstName;
			return this;
		}
		public ExtendedBuilder createdDate(Date createdDate){
			this.createdDate = createdDate;
			return this;
		}
		public ExtendedBuilder score(double score){
			this.score = score;
			return this;
		}
		public ExtendedBuilder itemTitle(String itemTitle){
			this.itemTitle = itemTitle;
			return this;
		}
		public ExtendedBuilder itemId(int itemId){
			this.itemId = itemId;
			return this;
		}
		public UserReviewRenderingModel build(){
			return new UserReviewRenderingModel(userId,title,lastName,firstName,content,createdDate,score,itemTitle,itemId);
		}
		
	}
}
