package com.cxjd.domain.sfile;

import java.util.Date;

import com.cxjd.entity.EntityObject;

/**
 * share file model²ã
 * 
 *@author ÀîÌÚ³¬
 *@since 2013-10-05
 *
 */
public class Sfile extends EntityObject{

	String SfileTitle;
	String SfileComment;
	String SfileOldName;
	String SfileNewName;
	String SfileType;
	Date SfileDate;
	public String getSfileTitle() {
		return SfileTitle;
	}
	public void setSfileTitle(String sfileTitle) {
		SfileTitle = sfileTitle;
	}
	public String getSfileComment() {
		return SfileComment;
	}
	public void setSfileComment(String sfileComment) {
		SfileComment = sfileComment;
	}
	public String getSfileOldName() {
		return SfileOldName;
	}
	public void setSfileOldName(String sfileOldName) {
		SfileOldName = sfileOldName;
	}
	public String getSfileNewName() {
		return SfileNewName;
	}
	public void setSfileNewName(String sfileNewName) {
		SfileNewName = sfileNewName;
	}
	public Date getSfileDate() {
		return SfileDate;
	}
	public void setSfileDate(Date sfileDate) {
		SfileDate = sfileDate;
	}
	public String getSfileType() {
		return SfileType;
	}
	public void setSfileType(String sfileType) {
		SfileType = sfileType;
	}
	
	
}
