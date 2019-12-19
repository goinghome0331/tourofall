package net.bulldozer.tourofall.dest.util;

import java.util.ArrayList;

import net.bulldozer.tourofall.dest.dto.Area;
import net.bulldozer.tourofall.dest.dto.ItemType;
import net.bulldozer.tourofall.dest.dto.ServiceCategory;

public class DetailInfoUtil {
	private static final ArrayList<ItemType> tourType= new ArrayList<ItemType>();
	private static final ArrayList<ServiceCategory> serviceCategory = new ArrayList<ServiceCategory>();
	private static final ArrayList<Area> area = new ArrayList<Area>();
	static{
		tourType.add(new ItemType("������",12));
		tourType.add(new ItemType("��ȭ�ü�",14));
		tourType.add(new ItemType("���/����/����",15));
		tourType.add(new ItemType("�����ڽ�",25));
		tourType.add(new ItemType("������",28));
		tourType.add(new ItemType("����",32));
		tourType.add(new ItemType("����",38));
		tourType.add(new ItemType("������",39));
		
		serviceCategory.add(new ServiceCategory("�ڿ�","A01"));
		serviceCategory.add(new ServiceCategory("�ι�(��ȭ/����/����)","A02"));
		serviceCategory.add(new ServiceCategory("������","A03"));
		serviceCategory.add(new ServiceCategory("����","A04"));
		serviceCategory.add(new ServiceCategory("����","A05"));
		serviceCategory.add(new ServiceCategory("����","B02"));
		serviceCategory.add(new ServiceCategory("��õ�ڽ�","C01"));
		
		
		area.add(new Area("����",1));
		area.add(new Area("��õ",2));
		area.add(new Area("����",3));
		area.add(new Area("�뱸",4));
		area.add(new Area("����",5));
		area.add(new Area("�λ�",6));
		area.add(new Area("���",7));
		area.add(new Area("����Ư����ġ��",8));
		area.add(new Area("��⵵",31));
		area.add(new Area("������",32));
		area.add(new Area("��û�ϵ�",33));
		area.add(new Area("��û����",34));
		area.add(new Area("���ϵ�",35));
		area.add(new Area("��󳲵�",36));
		area.add(new Area("����ϵ�",37));
		area.add(new Area("���󳲵�",38));
		area.add(new Area("���ֵ�",39));
	}
	
	public static ArrayList<ItemType> getTourType(){
		return new ArrayList<ItemType>(tourType);
	}
	public static ArrayList<ServiceCategory> getServiceCategory(){
		return new ArrayList<ServiceCategory>(serviceCategory);
	}
	public static ArrayList<Area> getProvince(){
		return new ArrayList<Area>(area);
	}
}
