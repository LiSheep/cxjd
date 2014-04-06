package com.cxjd.util;

import java.util.HashMap;
import java.util.Map;

import com.cxjd.common.Dictionary;

public class RoleType {

	private static Map<Integer, String> roleMap = new HashMap<Integer, String>();
	
	static{
		roleMap.put(Dictionary.role_admin, "管理员");
		roleMap.put(Dictionary.role_stu, "学生");
		roleMap.put(Dictionary.role_tea, "教师");
		roleMap.put(Dictionary.role_collAdmin, "承办单位管理员");
	}
	
	public static   Map<Integer, String> getRoleType()
	{
		return roleMap;
	}
	
	public static String getTypeByKey(int key)
	{
		return roleMap.get(key);
	}
	
}
