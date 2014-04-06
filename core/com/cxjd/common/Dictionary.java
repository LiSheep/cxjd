package com.cxjd.common;

public final class Dictionary {

	//权限
	public static int role_admin = 1;
	public static int role_stu = 2;
	public static int role_tea = 3;
	public static int role_collAdmin = 4;	//承办单位管理员
	public static String role = "role";
	
	public static int none = -1;
	
	public static String name = "name";
	
	public static String roleName = "roleName";
	
	public static String access = "access";
	public static String noaccess = "no.access";
	
	//地点
	public static int schLvShun = 1;
	public static int schBenBu = 2;
	
	//设备申请，审核状态
	public static int unCheck = 0;
	public static int check = 1;
	public static int isReturn = 2;
	public static int refuse = 3;

	//组长/组员
	public static int claimerType_main = 1;
	public static int claimerType_other = 0;

	//项目申请状态
	public static int applyStatus_uncheck = 0;
	public static int applyStatus_pass = 1;
	public static int applyStatus_nopass = 2;
	public static int applyStatus_mark = 3;
	public static int applyStatus_uploaded = 4;
	
	//性别
	public static boolean male = true;
	public static boolean female = false;
}
