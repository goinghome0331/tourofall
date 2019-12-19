package net.bulldozer.tourofall.dest.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RecommendJSONUtilites {
	public static JSONArray getRecommendItems(String jsonResult) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(jsonResult);
		Object items = jsonObject.get("Items");
		if(items instanceof String){
			return null;
		}
		
		return (JSONArray)items;
	}
}
