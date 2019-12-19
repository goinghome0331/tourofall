package net.bulldozer.tourofall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
public class ViewConfig {
	
	@Bean
	public ViewResolver viewResolver() {
		return new TilesViewResolver();
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] {
				"/tiles-def/tiles-def.xml",
				"/tiles-def/base-common-tiles.xml",
				"/tiles-def/base-destinfo-tiles.xml",
				"/tiles-def/base-detailinfo-tiles.xml",
				"/tiles-def/base-introinfo-tiles.xml",
				"/tiles-def/base-users-tiles.xml",
				"/tiles-def/destinfo-tiles.xml",
				"/tiles-def/users-tiles.xml"
		});
		return configurer;
	}

}
