package net.bulldozer.tourofall.dest.dto;

public class ItemType {
	private String itemTypeName;
	private int itemTypeNum;
	
	public ItemType(){}
	public ItemType(String itemTypeName,int itemTypeNum){
		this.itemTypeName = itemTypeName;
		this.itemTypeNum = itemTypeNum;
	}
	public String getItemTypeName() {
		return itemTypeName;
	}
	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}
	public int getItemTypeNum() {
		return itemTypeNum;
	}
	public void setItemTypeNum(int itemTypeNum) {
		this.itemTypeNum = itemTypeNum;
	}
}
