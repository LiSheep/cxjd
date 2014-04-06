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
	 * �ӡ���ʱ��
	 * @author ���ڳ�
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
	 * У���ļ�
	 * @return ���ϴ�����true
	 */
	public static boolean checkFile(java.io.File file) {
		if(file.exists() && file.isFile())
			return true;
		else {
			return false;
		}
	}
	
	/**
	 * �ϴ��ļ�
	 * @param file
	 * @param fileName
	 * @return �ϴ����ļ���
	 */
	public static String uploadFile(File file, String savePath, String fileName) {
		FileOutputStream fos = null;
        FileInputStream fis = null;
        try{
        fos = new FileOutputStream(savePath + "/" + file.getName());
        // �����ļ��ϴ���
        fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = fis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }
	    } catch (Exception e) {
	        System.out.println("�ļ��ϴ�ʧ��");
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
	                System.out.println("FileInputStream�ر�ʧ��");
	                e.printStackTrace();
	            }
	        }
	        if (fos != null) {
	            try {
	                fos.close();
	            } catch (IOException e) {
	                System.out.println("FileOutputStream�ر�ʧ��");
	                e.printStackTrace();
	            }
	      }
	}
	
}
