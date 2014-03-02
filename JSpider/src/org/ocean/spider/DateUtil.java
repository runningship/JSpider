package org.ocean.spider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public static String getCurrentTimeInString(){
		Date date = new Date();
		return sdf.format(date);
	}
	
	public static Date fromString(String strDate) throws ParseException{
		return sdf.parse(strDate);
	}
	public static void main(String[] args) throws ParseException{
		System.out.println(fromString("2014-03-02 11:02:07"));
		System.out.println(getCurrentTimeInString());
	}
}
