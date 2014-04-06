package com.cxjd.common;

import java.util.Map;

public class SessionHelper {

	public static boolean authorizating(Map<String, Object> sessionMap){
		
		if(sessionMap != null && !sessionMap.isEmpty()){
			String name = (String) sessionMap.get(Dictionary.name);
		    int role = (Integer) sessionMap.get(Dictionary.role);
		    if(name != null && role != Dictionary.none )
		    	return true;
		}
		return false;
	}
	
	public static String getRoleName(int type){
		if(Dictionary.role_admin == type){
			return "管理员";
		}
		if(Dictionary.role_stu == type){
			return "同学";
		}
		return "";
	}
	
}
