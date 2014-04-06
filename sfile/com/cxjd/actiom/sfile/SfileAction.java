package com.cxjd.actiom.sfile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;

import com.cxjd.action.CrudAction;
import com.cxjd.common.CommonUtil;
import com.cxjd.common.ExcelHelper;
import com.cxjd.domain.sfile.Sfile;
import com.cxjd.service.item.ApplyService;
import com.cxjd.service.sfile.SfileService;

/**
 * share file action层
 * 
 *@author 李腾超
 *@since 2013-10-05
 *
 */
public class SfileAction extends CrudAction<Sfile, SfileService> {

	private static final long serialVersionUID = -4636552199091393350L;

	private File file;
	private String fileContentType;
	private String fileFileName;
	private String savePath;
	
	
	 public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	/**
     * @return上传文件的保存位置
     */
    public String getSavePath() throws Exception{
    	
        return ServletActionContext.getServletContext().getRealPath(savePath); 
    }

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	@Override
	public Sfile getModel() {
		if(this.model == null){
			model = new Sfile();
		}
		return model;
	}

	@Override
	public SfileService getService() {
		if(this.service == null){
			this.service = new SfileService();
		}
		return service;
	}

	@Override
	public String list() throws Exception {
		pager.setPageSize(10);
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
	
	public String add2() throws Exception{
		return "addinput";
	}

	@Override
	public String add() throws Exception {
		if(!CommonUtil.checkFile(file)){
			addFieldError("uperror", "文件上传失败,文件不存在或者不可读！");
			return "input";
		}
		String fileNewName = null;
		
		fileNewName = CommonUtil.uploadFile(getFile(), getSavePath(), getFileFileName());
		
		getModel().setSfileOldName(getFileFileName());
		getModel().setSfileNewName(fileNewName);
		getModel().setSfileType(this.fileContentType); 
		
		if(getService().create(getModel())){
			addFieldError("uperror", "文件上传成功！");
			if(getKey()!=null && getKey()!=""){
				ApplyService aService = new ApplyService();
				aService.updateSfileKey(getModel().getId(), getKey());
			}
		}
		else
			addFieldError("uperror", "文件上传失败");
		return "add";
	}
	

	@Override
	public String update() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Exception {
		getService().deleteUnique(getKey());
		return "delete";
	}

	public String show(){
		model = getService().readUnique(getKey());
		return "show";
	}
	
	
	private InputStream downloadFile;
	private String fileName;
	private String fileOldName;
	private String fileType;
	
	
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileOldName() {
		return fileOldName;
	}
	public void setFileOldName(String fileOldName) {
		this.fileOldName = fileOldName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setDownloadFile(InputStream downloadFile){
		this.downloadFile = downloadFile;
	}
	public InputStream getDownloadFile(){
		return downloadFile;
	}
	
	public String down(){
		try {
			String path = savePath + File.separator + getFileName();
			setDownloadFile(ServletActionContext.getServletContext().getResourceAsStream(path));
			return "down";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "down";
		}
	}
	
}
