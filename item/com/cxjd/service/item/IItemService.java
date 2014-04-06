package com.cxjd.service.item;

import com.cxjd.domain.item.Item;
import com.cxjd.service.IBaseService;

public interface IItemService extends IBaseService<Item> {

	public Item ReadByName(String itemName);
}
