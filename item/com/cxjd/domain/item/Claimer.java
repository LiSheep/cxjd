package com.cxjd.domain.item;

import com.cxjd.entity.EntityObject;

/**
 * borrowTool action层
 * 
 *@author 李腾超
 *@since 2013-08-29
 *
 */
public class Claimer extends EntityObject {
	private String claimerName;
	private boolean claimerSex;
	private String claimerCollege;	//学院
	private String claimerPro;		//专业
	private String claimerClass;	//班级
	private String claimerNo;		//学号
	private int claimerType;
	
	

	public boolean isClaimerSex() {
		return claimerSex;
	}

	public void setClaimerSex(boolean claimerSex) {
		this.claimerSex = claimerSex;
	}

	public String getClaimerCollege() {
		return claimerCollege;
	}

	public void setClaimerCollege(String claimerCollege) {
		this.claimerCollege = claimerCollege;
	}

	public String getClaimerPro() {
		return claimerPro;
	}

	public void setClaimerPro(String claimerPro) {
		this.claimerPro = claimerPro;
	}

	public String getClaimerClass() {
		return claimerClass;
	}

	public void setClaimerClass(String claimerClass) {
		this.claimerClass = claimerClass;
	}

	public String getClaimerNo() {
		return claimerNo;
	}

	public void setClaimerNo(String claimerNo) {
		this.claimerNo = claimerNo;
	}

	public int getClaimerType() {
		return claimerType;
	}

	public void setClaimerType(int claimerType) {
		this.claimerType = claimerType;
	}

	public String getClaimerName() {
		return claimerName;
	}

	public void setClaimerName(String claimerName) {
		this.claimerName = claimerName;
	}
	
	
}
