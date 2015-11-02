package com.minws.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatUtil {
	
	public static String StringToTime(String s){
		
		
		Double time = Double.parseDouble(s);
	 	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	        
		 return  sdf.format(time);
		//System.currentTimeMillis()
	}
	public static String StringToTimeMillis(String s){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(s).getTime()+"";
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String StringToDate(String s){
		
		
		Double time = Double.parseDouble(s);
	 	SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd"); 
	        
		 return  sdf.format(time);
		
	}
	//s="2015-01-01",pattern="yyyy-MM-dd"
	public static Date strToDate(String s,String pattern){
		DateFormat format= new SimpleDateFormat(pattern);   
        Date d = null;  
        try {  
            d = format.parse(s);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }
        return d;
	}
	
	public static boolean emailFormat(String email) 
    { 
        boolean tag = true; 
        final String pattern1 = "^([a-z0-9A-Z]+[-|//.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?//.)+[a-zA-Z]{2,}$"; 
        final Pattern pattern = Pattern.compile(pattern1); 
        final Matcher mat = pattern.matcher(email); 
        if (!mat.find()) { 
            tag = false; 
        } 
        return tag; 
    } 

	public static boolean isMobileNO(String mobiles){  
		  
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
		  
		Matcher m = p.matcher(mobiles);  
		  
		return m.matches();  
		  
	}
	public static String en_date(){
		Calendar cal=Calendar.getInstance();
		//System.out.println(cal.get(Calendar.DATE));
		//System.out.println(cal.get(Calendar.YEAR));
		//System.out.println(en_month(cal.get(Calendar.MONTH)));
		return cal.get(Calendar.DATE)+"<sup>th</sup> "+en_month(cal.get(Calendar.MONTH))+" ,"+cal.get(Calendar.YEAR);
	}
	public static String en_month(int month){
		
		String[] m={"January","February","March","April","May","June","July","August","September","October","November","December"};
		
		return m[month];
	}
	

}
