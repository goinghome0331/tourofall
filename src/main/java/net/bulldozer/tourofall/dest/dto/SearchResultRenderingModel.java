package net.bulldozer.tourofall.dest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchResultRenderingModel {
	private int itemId;
	private String imageUrl;
	private String title;
	private String address;
	private double score;
	
	public void setScore(double score){
		this.score = score;
	}
	public static Builder getBuilder(){
		return new Builder();
	}
	public static class Builder{
		SearchResultRenderingModel searchResultRenderingModel;
		public Builder(){
			searchResultRenderingModel = new SearchResultRenderingModel(); 
		}
		
		public Builder itemId(int itemId){
			searchResultRenderingModel.itemId=itemId;
			return this;
		}
		public Builder imageUrl(String imageUrl){
			searchResultRenderingModel.imageUrl = imageUrl;
			return this;
		}
		public Builder title(String title){
			searchResultRenderingModel.title = title;
			return this;
		}
		public Builder address(String address){
			searchResultRenderingModel.address = address;
			return this;
		}
		public Builder score(double score){
			searchResultRenderingModel.score = score;
			return this;
		}
		public SearchResultRenderingModel build(){
			return searchResultRenderingModel;
		}
		
	}
}
