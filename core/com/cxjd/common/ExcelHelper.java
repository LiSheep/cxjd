package com.cxjd.common;

import java.io.File; 
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date; 
import java.util.List;

import com.cxjd.domain.item.Apply;
import com.cxjd.domain.item.Claimer;

import jxl.*; 
import jxl.write.*; 
import jxl.write.biff.RowsExceededException;

/**
 * excel文件生成工具类
 * 
 *@author 李腾超
 *@since 2014-01-17
 *
 */
public class ExcelHelper {

	WritableWorkbook book = null;
	WritableSheet sheet = null;
	
	public void initXlsStream(OutputStream os){
		
		try {
			book = Workbook.createWorkbook(os);
			sheet = book.createSheet("book1", 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void operatorXls(List<Apply> applies){
		try {
			sheet.mergeCells( 1, 0, 7, 1);
			WritableFont wfont = new jxl.write.WritableFont(WritableFont.TIMES, 16, WritableFont.BOLD, true); 
	        WritableCellFormat wc = new WritableCellFormat(wfont); 
			wc.setAlignment(Alignment.CENTRE); 
			if(applies.isEmpty())
				return;
			Label labTitle = new Label(1,0,applies.get(0).getItem().getItemName(),wc);
			Label labCollege = new Label(9,1,applies.get(0).getItem().getItemCollege(), wc);
			List<Label> t = new ArrayList<Label>();
			t.add(new Label(0,3, "参赛作品题目"));
			t.add(new Label(1,3, "组员"));
			t.add(new Label(2,3, "性别"));
			t.add(new Label(3,3, "学号"));
			t.add(new Label(4,3, "学院"));
			t.add(new Label(5,3, "专业"));
			t.add(new Label(6,3, "班级"));
			
			for (Label label : t) {
				sheet.addCell(label);
			}
			sheet.setColumnView(0,20);
			sheet.setColumnView(6,20);
			sheet.addCell(labTitle);
			sheet.addCell(labCollege);
			
			int row = 4;
			for (Apply apply : applies) {
				Label tLabel = new Label(0, row, apply.getApplyName());
				sheet.addCell(tLabel);
				for (Claimer claimer : apply.getOtherCliamers()) {
					List<Label> claimerLabel = new ArrayList<Label>();
					claimerLabel.add(new Label(1, row,claimer.getClaimerName()));
					if(claimer.isClaimerSex() == Dictionary.male)
						claimerLabel.add(new Label(2, row,"男"));
					else {
						claimerLabel.add(new Label(2, row,"女"));
					}
					claimerLabel.add(new Label(3, row,claimer.getClaimerNo()));
					claimerLabel.add(new Label(4, row,claimer.getClaimerCollege()));
					claimerLabel.add(new Label(5, row,claimer.getClaimerPro()));
					claimerLabel.add(new Label(6, row,claimer.getClaimerClass()));
					++row;
					for (Label cl : claimerLabel) {
						sheet.addCell(cl);
					}
				}
				++row;
			}
			
			
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void closeXlsStream(){
		try {
			book.write();
			book.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
