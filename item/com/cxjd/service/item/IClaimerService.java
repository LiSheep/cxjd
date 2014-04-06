package com.cxjd.service.item;

import java.util.List;

import com.cxjd.domain.item.Claimer;
import com.cxjd.service.IBaseService;

public interface IClaimerService extends IBaseService<Claimer> {

	
	//ͨ��applyKey��ȡclaimer
	public List<Claimer> readClaimerByApplyKey(Object applyKey, int claimerType);
		
	//ͨ��applyKeyɾ��claimer
	public boolean deleteClaimerByApplyKey(Object applyKey);
}
