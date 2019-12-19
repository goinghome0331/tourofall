package net.bulldozer.tourofall.dest.dto;

public class Area {
	private String areaName;
	private int areaNum;
	
	public Area(){}
	public Area(String areaName, int areaNum) {
		this.areaName = areaName;
		this.areaNum = areaNum;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public int getAreaNum() {
		return areaNum;
	}
	public void setAreaNum(int areaNum) {
		this.areaNum = areaNum;
	}
	
	
}
