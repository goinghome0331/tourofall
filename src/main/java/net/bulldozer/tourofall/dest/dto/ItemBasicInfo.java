package net.bulldozer.tourofall.dest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemBasicInfo {
	private String title;
	private String address;
	private String imageUrl;
	
	public static Builder getBuilder(){
		return new Builder();
	}
	
	public static class Builder{
		private ItemBasicInfo itemBasicInfo;
		public Builder(){
			itemBasicInfo = new ItemBasicInfo();
		}
		public Builder title(String title){
			itemBasicInfo.title = title;
			return this;
		}
		public Builder address(String address){
			itemBasicInfo.address = address;
			return this;
		}
		public Builder imageUrl(String imageUrl){
			itemBasicInfo.imageUrl = imageUrl;
			return this;
		}
		public ItemBasicInfo build(){
			return itemBasicInfo;
		}
		
		
	}
}
