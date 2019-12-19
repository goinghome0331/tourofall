package net.bulldozer.tourofall.dest.dto;

public class ServiceCategory {
	private String categoryName;
	private String categoryCode;
	
	public ServiceCategory(){}
	public ServiceCategory(String categoryName, String categoryCode) {
		this.categoryName = categoryName;
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
}
