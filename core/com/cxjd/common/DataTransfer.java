package com.cxjd.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataTransfer {

	public static String dateToString(Date time){
		return DateFormat.getDateInstance(DateFormat.DEFAULT).format(time);
	}
	
	public static Date stringtoDate(String time){
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formatDate.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
