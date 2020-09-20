package com.study.test.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class CommonUtil {

	public static Map<String, Object> requestToMap(HttpServletRequest request) {
		Map<String, Object> map = new HashMap();
		
	
		Enumeration<String> pramEnumeration =request.getParameterNames();
		String pramName;
		String pramValue;
		
		
		while (pramEnumeration.hasMoreElements()) {
			
			pramName=pramEnumeration.nextElement();
			pramValue=request.getParameter(pramName);
			
			map.put(pramName, pramValue);
			
			
		}
		
		
		return map;
	}
	
	public static void setCurrentPage(Map<String, Object> map) {
		int currentPage = 0;
		int pageSet =0;
		
		
	
		if(map.get("currentPage") == null) {
			currentPage=1;
		}else {
			currentPage=Integer.parseInt(map.get("currentPage").toString());
		}
		
		if(map.get("pageSet") == null) {
			pageSet=15;
		}else {
			pageSet=Integer.parseInt(map.get("pageSet").toString());
		}
		
		
		
		if ( currentPage <= 0 || currentPage < Integer.MIN_VALUE || currentPage > Integer.MAX_VALUE ) {
			currentPage = 1;
		}
		
		if ( pageSet <= 0 || pageSet < Integer.MIN_VALUE || pageSet > Integer.MAX_VALUE ) {
			pageSet = 15;
		}
		
		int offSet = (currentPage -1) *pageSet;
		
		
		map.put("offSet", offSet);
		map.put("pageSet", pageSet);
		map.put("currentPage", currentPage);
		
	
		
	}
	
	public static void setBoardNum(Map<String, Object> map) {
		ArrayList<Integer> boardNumList = new ArrayList<Integer>();
		
		
		int offSet =(Integer) map.get("offSet");
		int pageSet =(Integer) map.get("pageSet");
		int totalCount =(Integer) map.get("totalCount");
		int currentPage =(Integer) map.get("currentPage");
		int minPage=0;
		int maxPage=(totalCount/pageSet)+1;
		minPage=(((currentPage-1)/10)*10)+1;
		
		System.out.println("minPage ="+minPage);
		System.out.println("maxPage ="+maxPage);
		System.out.println("pageSet ="+pageSet);
		System.out.println("offSet ="+offSet);
		
		
		List<Map<String, Object>> selectList = (List<Map<String, Object>>) map.get("selectList");
		
		for (int i = 0; i < selectList.size(); i++) {
			Map<String, Object> selectMap = selectList.get(i);
			selectMap.put("listNum", offSet+i+1);
		}
		
		
		
		for (int i = minPage; i < minPage+10; i++) {
			boardNumList.add(i);
			System.out.println("Paging ="+i);
			
			if(i>=maxPage) {
				break;
			}
			
		}	
		
		map.put("paging", boardNumList);
		
		
	}
	
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	public static String byteArrayToHex(byte[] a) {
	    StringBuilder sb = new StringBuilder();
	    for(final byte b: a)
	        sb.append(String.format("%02x ", b&0xff));
	    return sb.toString();
	}
	
}
