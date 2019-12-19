package net.bulldozer.tourofall.dest.dto;

public class TodayDestinationRederingModel {
	private String title;
	private int itemId;
	private String address;
	private String imageUrl;
	private String destinationType;
	private double meanScore;
	
	
	public static Builder getBuilder(){
		return new Builder();
	}

	public String getTitle() {
		return title;
	}
	
	public String getAddress() {
		return address;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getDestinationType() {
		return destinationType;
	}

	public double getMeanScore() {
		return meanScore;
	}


	public int getItemId() {
		return itemId;
	}


	public static class Builder{
		private TodayDestinationRederingModel bestDestinationRederingModel;
		
		public Builder(){
			bestDestinationRederingModel = new TodayDestinationRederingModel();
		}
		public Builder title(String title){
			bestDestinationRederingModel.title = title;
			return this;
		}
		public Builder address(String address){
			bestDestinationRederingModel.address = address;
			return this;
		}
		public Builder itemId(int itemId){
			bestDestinationRederingModel.itemId = itemId;
			return this;
		}
		public Builder imageUrl(String imageUrl){
			bestDestinationRederingModel.imageUrl = imageUrl;
			return this;
		}
		public Builder destinationType(String destinationType){
			bestDestinationRederingModel.destinationType = destinationType;
			return this;
		}
		public Builder meanScore(double meanScore){
			bestDestinationRederingModel.meanScore = meanScore;
			return this;
		}
		public TodayDestinationRederingModel build(){
			return bestDestinationRederingModel;
		}
	}
}
