package com.cxjd.action.organizer;

import com.cxjd.action.CrudAction;
import com.cxjd.domain.organizer.Organizer;
import com.cxjd.service.organizer.IOrganizerService;
import com.cxjd.service.organizer.OrganizerService;

/**
 * organizer's control  ²ã
 * @author ÀîÌÚ³¬
 * @since 2014-01-17
 *
 */
public class OrganizerAction extends CrudAction<Organizer, IOrganizerService>{

	private static final long serialVersionUID = 6888550608446359956L;

	@Override
	public Organizer getModel() {
		if(this.model == null){
			this.model = new Organizer();
		}
		return model;
	}

	@Override
	public IOrganizerService getService() {
		if(this.service == null){
			this.service = new OrganizerService();
		}
		return this.service;
	}

	@Override
	public String list() throws Exception {
		pager.setPageSize(5);
		setEntities(getService().readWithPage(pager, null));
		return "list";
	}

	@Override
	public String input() throws Exception {
		String nav = "addinput";
		if( getKey()!=null && !getKey().isEmpty()){
			nav = "updateinput";
			model = getService().readUnique(getKey());
		}
		
		return nav;
	}

	@Override
	public String add() throws Exception {
		getService().create(getModel());
		return "add";
	}

	@Override
	public String update() throws Exception {
		getService().update(getModel());
		return "update";
	}

	@Override
	public String delete() throws Exception {
		getService().deleteUnique(getKey());
		return list();
	}

}
