package com.cxjd.common;

import java.util.Calendar;

import java.util.Date;
import java.util.GregorianCalendar;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import com.cxjd.domain.sfile.Sfile;
import com.cxjd.entity.EntityObject;
public class CommonUtil {

	/**
	 * 加、减时间
	 * @author 李腾超
	 * @since 2013-09-19r
	*/
	public static Date ChangeTime(Date time, int day)
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(time);
		gc.set(Calendar.DAY_OF_MONTH, gc.get(Calendar.DAY_OF_MONTH) + day);
		return gc.getTime();
	}
	
	/**
	 * 校验文件
	 * @return 可上传返回true
	 */
	public static boolean checkFile(java.io.File file) {
		if(file.exists() && file.isFile())
			return true;
		else {
			return false;
		}
	}
	
	/**
	 * 上传文件
	 * @param file
	 * @param fileName
	 * @return 上传的文件名
	 */
	public static String uploadFile(File file, String savePath, String fileName) {
		FileOutputStream fos = null;
        FileInputStream fis = null;
        try{
        fos = new FileOutputStream(savePath + "/" + file.getName());
        // 建立文件上传流
        fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = fis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }
	    } catch (Exception e) {
	        System.out.println("文件上传失败");
	        e.printStackTrace();
	    } finally {
	        close(fos, fis);
	    }
		return file.getName();
	}
	 
	private static void close(FileOutputStream fos, FileInputStream fis) {
	        if (fis != null) {
	            try {
	                fis.close();
	            } catch (IOException e) {
	                System.out.println("FileInputStream关闭失败");
	                e.printStackTrace();
	            }
	        }
	        if (fos != null) {
	            try {
	                fos.close();
	            } catch (IOException e) {
	                System.out.println("FileOutputStream关闭失败");
	                e.printStackTrace();
	            }
	      }
	}
	
}
