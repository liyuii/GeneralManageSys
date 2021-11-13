package com.web.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.web.util.string.StringUtil;

/**
 * 日期工具类
 * @author wzj
 * @version v1.0
 */
public class DateUtil {
	/**
	 * 日期格式
	 */
	public static final String DATE = "yyyyMMdd";
	public static final String YEAR = "yyyy";
	public static final String YEAR_MONTH = "yyyy-MM";
	public static final String DATETIME = "yyyyMMddHHmmss";
	public static final String DATETIMEMILLI = "yyyyMMddHHmmssSSS";
	public static final String HYPHEN_DATE = "yyyy-MM-dd";
	public static final String HC_DATETIME = "yyyy-MM-dd HH:mm:ss";
	public static final String EXCEL_DATETIME = "MM/dd/yyyy HH:mm:ss";
	public static final String EXCEL_DATE = "MM/dd/yyyy";
	public static final String EXCEL_TIME = "HH:mm:ss";
	
	/**
	 * 获取指定日期格式的日期格式化对象
	 * 若指定的日期格式为非法格式，则返回NULL
	 * @param pattern
	 * @return 日期格式化对象，若指定的日期格式为非法格式，则返回NULL
	 */
	public static DateFormat getDateFormat(String pattern){
		if(StringUtil.nullOrEmpty(pattern)){
			throw new IllegalArgumentException("arg 'pattern' can't be empty");
		}
		DateFormat dateFormat = null;
		try{
			dateFormat = new SimpleDateFormat(pattern);
		}catch(IllegalArgumentException e){
			// do nothing
		}
		return dateFormat;
	}
	
	/**
	 * 将目标日期对象按指定的日期格式转换为日期字符串
	 * @param target 目标对象
	 * @param pattern 日期格式
	 * @return 日期字符串
	 */
	public static String formatDate(Date target, String pattern){
		String formattedDate = null;
		DateFormat dateFormat = getDateFormat(pattern);
		if(dateFormat != null){
			if(target != null){
				formattedDate = dateFormat.format(target);
			}
		}else{
			throw new IllegalArgumentException("not support pattern: " + pattern);
		}
		return formattedDate;
	}
	
//	/**
//	 * 将目标日期字符串按指定的日期格式解析为日期对象
//	 * @param dateStr 日期字符串
//	 * @param pattern 日期格式
//	 * @return 日期对象
//	 */
//	public static Date parseDate(String dateStr, String pattern){
//		Date parsedDate = null;
//		DateFormat dateFormat = getDateFormat(pattern);
//		if(dateFormat != null){
//			try {
//				parsedDate = dateFormat.parse(dateStr);
//			} catch (ParseException e) {
//				throw new FrameworkCoreException(e);
//			}
//		}else{
//			throw new IllegalArgumentException("not support pattern: " + pattern);
//		}
//		return parsedDate;
//	}
	
//	/**
//	 * 将目标日期字符串按指定的日期格式解析为日期对象
//	 * @param dateStr 日期字符串
//	 * @param pattern 日期格式
//	 * @return 日期对象
//	 */
//	public static java.sql.Date parseSqlDate(String dateStr, String pattern){
//		java.sql.Date parsedDate = null;
//		DateFormat dateFormat = getDateFormat(pattern);
//		if(dateFormat != null){
//			try {
//				Date date = dateFormat.parse(dateStr);
//				parsedDate = new java.sql.Date(date.getTime());
//			} catch (ParseException e) {
//				throw new FrameworkCoreException(e);
//			}
//		}else{
//			throw new IllegalArgumentException("not support pattern: " + pattern);
//		}
//		return parsedDate;
//	}
	
//	/**
//	 * 将目标日期字符串按指定的日期格式解析为时间戳对象
//	 * @param dateStr 日期字符串
//	 * @param pattern 日期格式
//	 * @return 日期对象
//	 */
//	public static java.sql.Timestamp parseSqlTimestamp(String dateStr, String pattern){
//		java.sql.Timestamp parsedTimestamp = null;
//		DateFormat dateFormat = getDateFormat(pattern);
//		if(dateFormat != null){
//			try {
//				Date date = dateFormat.parse(dateStr);
//				parsedTimestamp = new java.sql.Timestamp(date.getTime());
//			} catch (ParseException e) {
//				throw new FrameworkCoreException(e);
//			}
//		}else{
//			throw new IllegalArgumentException("not support pattern: " + pattern);
//		}
//		return parsedTimestamp;
//	}
	
	public static int getLastDay(String year, String month){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.valueOf(year));
		cal.set(Calendar.MONTH, Integer.valueOf(month) - 1);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public static List<String> getYearList(int leftPlusX, int rightPlusX, boolean desc){
		List<String> yearList = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		if(desc){ // 倒序
			for(int year = (curYear+rightPlusX); year >= (curYear-leftPlusX); year--){
				yearList.add(String.valueOf(year));
			}
		}else{ // 正序
			for(int year = (curYear-leftPlusX); year <= (curYear+rightPlusX); year++){
				yearList.add(String.valueOf(year));
			}
		}
		return yearList;
	}
	
	public static Date getFirstDayOfLastXMonth(int x){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - x);
		return new Date(cal.getTimeInMillis());
	}
	
	public static String getFirstDayStrOfLastXMonth(int x){
		Date firstDayDate = getFirstDayOfLastXMonth(x);
		return formatDate(firstDayDate, HYPHEN_DATE);
	}
	
	public static Date getLastDayOfLastXMonth(int x){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - x);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return new Date(cal.getTimeInMillis());
	}
	
	public static String getLastDayStrOfLastXMonth(int x){
		Date lastDayDate = getLastDayOfLastXMonth(x);
		return formatDate(lastDayDate, HYPHEN_DATE);
	}
	
	public static Date getCurrDayOfLastXMonth(int x){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - x);
		return new Date(cal.getTimeInMillis());
	}
	
	public static String getCurrDayStrOfLastXMonth(int x){
		Date currDayOfLastXMonth = getCurrDayOfLastXMonth(x);
		return formatDate(currDayOfLastXMonth, HYPHEN_DATE);
	}
	
	/**
	 * 获取指定Calendar对象的YY格式月份字符串
	 * 如 4月份，返回 04；11月份，返回11
	 * @param cal
	 * @return
	 */
	public static String getMonthInYYFormat(Calendar cal){
		int monthInt = cal.get(Calendar.MONTH);
		String monthStr = String.valueOf(monthInt + 1);
		return monthStr.length()==2 ? monthStr : "0" + monthStr;
	}
	/**
	 * 
	* @Title: getMonthLastDay 
	* @Description: 查询该月最后一天
	* @param @param currentDate
	* @param @return    设定文件 
	* @author ShiChao 
	* @return String    返回类型 
	* @throws
	 */
	public static String getMonthLastDay(Date currentDate) {
		SimpleDateFormat lastDayFormatter = new SimpleDateFormat("dd");    
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(currentDate);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.roll(Calendar.DATE, -1); 
		return lastDayFormatter.format(cal.getTime());//当前月最后一天
	}
	public static String getDay(Date currentDate) {
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
		return dayFormat.format(currentDate);
	}
	public static String getMonth(Date currentDate) {
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		return monthFormat.format(currentDate);
	}
	public static String getYear(Date currentDate) {
		SimpleDateFormat yyyyFormat = new SimpleDateFormat("yyyy");
		return yyyyFormat.format(currentDate);
	}
	public static void main(String[] args) {
		System.out.println(getLastDay("2012","2"));
	}
}
