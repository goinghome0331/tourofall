package net.bulldozer.tourofall.dest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BestDestinationRenderingModel {
	private String title;
	private int itemId;
	private String imageUrl;
	private String description;
}
