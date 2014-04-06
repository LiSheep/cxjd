package com.cxjd.domain.item;

import java.util.Date;

import com.cxjd.entity.EntityObject;

/**
 * borrowTool action²ã
 * 
 *@author ÀîÌÚ³¬
 *@since 2013-08-29
 *
 */
public class Item extends EntityObject {

	private String itemName;
	private Date itemTime;
	private String itemCollege;
	private String orgId;
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Date getItemTime() {
		return itemTime;
	}
	public void setItemTime(Date itemTime) {
		this.itemTime = itemTime;
	}
	public String getItemCollege() {
		return itemCollege;
	}
	public void setItemCollege(String itemCollege) {
		this.itemCollege = itemCollege;
	}

	
}
