package net.bulldozer.tourofall.user.util;

import java.util.ArrayList;
import java.util.List;

public class DateList {
	private static List<Integer> yearList = new ArrayList<Integer>();
	private static List<Integer> monthList = new ArrayList<Integer>(); 
	private static List<Integer> dateList = new ArrayList<Integer>();
	static{
		for(int i = 1975; i <= 2005; i++){
			yearList.add(i);
		}
		
		for(int i = 1; i <= 12; i++){
			monthList.add(i);
		}
		
		for(int i = 1; i <= 31; i++){
			dateList.add(i);
		}
	}
	public static List<Integer> getYearList(){
		return yearList;
	}
	public static List<Integer> getMonthList(){
		return monthList;
	}
	public static List<Integer> getDateList(){
		return dateList;
	}
}
