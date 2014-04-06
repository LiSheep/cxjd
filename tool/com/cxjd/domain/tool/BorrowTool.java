package com.cxjd.domain.tool;

import java.util.Date;

import com.cxjd.entity.EntityObject;

/**
 * borrowTool model²ã
 * 
 *@author ÀîÌÚ³¬
 *@since 2013-07-29
 *
 */
public class BorrowTool extends EntityObject {

	private String toolKey;
	private String bro_userKey;
	private Date broTime;
	private Date returnTime;
	private int broDay;
	private String broNote;//ÏÞÖÆ100×Ö
	private int broNum;
	private int broStatus;
	
	private String toolName;
	private int toolNum;
	private String toolNo;
	private int toolPlace;
	private int toolOut;
	private String userKey;
	
	public Date getBroTime() {
		return broTime;
	}
	public void setBroTime(Date broTime) {
		this.broTime = broTime;
	}
	public int getBroDay() {
		return broDay;
	}
	public void setBroDay(int broDay) {
		this.broDay = broDay;
	}
	public String getBroNote() {
		return broNote;
	}
	public void setBroNote(String broNote) {
		this.broNote = broNote;
	}
	public int getBroNum() {
		return broNum;
	}
	public void setBroNum(int broNum) {
		this.broNum = broNum;
	}
	public String getToolKey() {
		return toolKey;
	}
	public void setToolKey(String toolKey) {
		this.toolKey = toolKey;
	}
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
	public int getToolOut() {
		return toolOut;
	}
	public void setToolOut(int toolOut) {
		this.toolOut = toolOut;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public Date getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	public int getBroStatus() {
		return broStatus;
	}
	public void setBroStatus(int broStatus) {
		this.broStatus = broStatus;
	}
	public String getBro_userKey() {
		return bro_userKey;
	}
	public void setBro_userKey(String bro_userKey) {
		this.bro_userKey = bro_userKey;
	}	
	
}
