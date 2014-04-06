package com.cxjd.domain.tool;

import com.cxjd.entity.EntityObject;


/**
 * tool control²ã
 * 
 *@author ÀîÌÚ³¬
 *@since 2013-07-23
 *
 */
public class Tool extends EntityObject {

	private String toolName;
	private int toolNum;
	private String toolNo;
	private int toolPlace;
	private int toolOut;
	private String userKey;
	
	public String getToolName() {
		return toolName;
	}
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	public int getToolNum() {
		return toolNum;
	}
	public void setToolNum(int toolNum) {
		this.toolNum = toolNum;
	}
	public String getToolNo() {
		return toolNo;
	}
	public void setToolNo(String toolNo) {
		this.toolNo = toolNo;
	}
	public int getToolPlace() {
		return toolPlace;
	}
	public void setToolPlace(int toolPlace) {
		this.toolPlace = toolPlace;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public int getToolOut() {
		return toolOut;
	}
	public void setToolOut(int toolOut) {
		this.toolOut = toolOut;
	}
	
	
}
