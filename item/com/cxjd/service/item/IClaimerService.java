package com.cxjd.service.item;

import java.util.List;

import com.cxjd.domain.item.Claimer;
import com.cxjd.service.IBaseService;

public interface IClaimerService extends IBaseService<Claimer> {

	
	//通过applyKey读取claimer
	public List<Claimer> readClaimerByApplyKey(Object applyKey, int claimerType);
		
	//通过applyKey删除claimer
	public boolean deleteClaimerByApplyKey(Object applyKey);
}
