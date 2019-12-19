package net.bulldozer.tourofall.evaluation.dto;

public class EvaluationRenderingModel {
	private int itemId;
	private String imageUrl;
	private String title;
	private double score;
	
	public EvaluationRenderingModel(){}
	public EvaluationRenderingModel(int itemId,String imageUrl, String title, double score) {
		this.itemId = itemId;
		this.imageUrl = imageUrl;
		this.title = title;
		this.score = score;
	}
	public EvaluationRenderingModel(int itemId,String imageUrl, String title) {
		this.itemId = itemId;
		this.imageUrl = imageUrl;
		this.title = title;
	}
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "DestinationEval [itemId=" + itemId + ", imageUrl=" + imageUrl + ", title=" + title + ", score=" + score
				+ "]";
	}
}
