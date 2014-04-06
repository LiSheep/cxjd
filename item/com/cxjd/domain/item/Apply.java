package com.cxjd.domain.item;

import java.util.ArrayList;
import java.util.List;

import com.cxjd.domain.user.User;
import com.cxjd.entity.EntityObject;

/**
 * borrowTool action��
 * 
 *@author ���ڳ�
 *@since 2013-08-29
 *
 */
public class Apply extends EntityObject {

	//id ΪApplyKey
	
	private Item item = new Item();
	
	private String applyName;	//������Ʒ��Ŀ
	private int applyStatus;
	private String applyMark;
	
	private String applyUserKey;  //������userKey
	
	private Claimer mainClaimer; //��Ŀ�鳤
	private List<Claimer> otherCliamers;
	
	private String SfileKey;//�ϴ�����
	
	
	public String getSfileKey() {
		return SfileKey;
	}

	public void setSfileKey(String sfileKey) {
		SfileKey = sfileKey;
	}

	public Apply(){
		if(this.item == null){
			this.item = new Item();
		}
		if(this.otherCliamers == null){
			this.otherCliamers = new ArrayList<Claimer>();
		}
			
	}
	
	public Claimer getMainClaimer() {
		return mainClaimer;
	}

	public void setMainClaimer(Claimer mainClaimer) {
		this.mainClaimer = mainClaimer;
	}

	public List<Claimer> getOtherCliamers() {
		return otherCliamers;
	}

	public void setOtherCliamers(List<Claimer> otherCliamers) {
		this.otherCliamers.addAll(otherCliamers);
	}

	public String getApplyUserKey() {
		return applyUserKey;
	}

	public void setApplyUserKey(String applyUserKey) {
		this.applyUserKey = applyUserKey;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public int getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(int applyStatus) {
		this.applyStatus = applyStatus;
	}

	public String getApplyMark() {
		return applyMark;
	}

	public void setApplyMark(String applyMark) {
		this.applyMark = applyMark;
	}
	
}
