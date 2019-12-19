package net.bulldozer.tourofall.dest.util;

import java.io.IOException;
import java.net.URLDecoder;

import org.springframework.web.util.UriComponentsBuilder;

public class TourUriUtilities {
	private final static String baseUrl = "api.visitkorea.or.kr";
	private final static String path="/openapi/service/rest/KorService/";
	private final static String KEY = "PKxRVEKq4aylIvyIJESCgJiM1SrJl7UXAiiBhuLddUMNQ0aC7U8Dn2%2F8XrrxfcTVLvHE9WBzTWD4LNLDWYyMQw%3D%3D"; 
	public static UriComponentsBuilder getBaseUriComponentsBuilder(String pathRoot) throws IOException{
		return UriComponentsBuilder.newInstance()
				.scheme("http")
				.path(path+pathRoot)
				.host(baseUrl)
				.queryParam("ServiceKey"   , URLDecoder.decode(KEY, "UTF-8"))
				.queryParam("MobileOS" , (String)"ETC")
                .queryParam("MobileApp"  , (String)"AppTesting")
                .queryParam("_type"  , (String)"json");
	}
	
}
