package com.cxjd.util;

import java.util.HashMap;
import java.util.Map;

import com.cxjd.common.Dictionary;

public class RoleType {

	private static Map<Integer, String> roleMap = new HashMap<Integer, String>();
	
	static{
		roleMap.put(Dictionary.role_admin, "����Ա");
		roleMap.put(Dictionary.role_stu, "ѧ��");
		roleMap.put(Dictionary.role_tea, "��ʦ");
		roleMap.put(Dictionary.role_collAdmin, "�а쵥λ����Ա");
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
