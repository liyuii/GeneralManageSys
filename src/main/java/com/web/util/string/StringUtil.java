package com.web.util.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * @author wzj
 * @version v1.0
 */
public class StringUtil {
	public final static String EMPTY = "";
	public final static String SIGN_SINGLE_BLANK = " ";
	public final static String SIGN_ELLIPSIS = "...";
	public final static String SIGN_HYPHEN = "-";
	public final static String SIGN_COMMA = ",";
	// 简易HTML标签正则
	private final static Pattern REGEXP_HTML_TAG = Pattern.compile("<[^>]+>");
	// 双引号正则
	private final static Pattern REGEXP_DOUBLE_QUOTE = Pattern.compile("\"");
	
	private final static Pattern REGEXP_LINE_BREAK = Pattern.compile("[\\\r\\\n]");
	/**
	 * 清除字符串所有的HTML标签
	 * @param target 目标字符串
	 * @return 去除HTML标签后的纯文本文件
	 */
	public static String removeHtmlTag(String target){
		if(target == null){
			return null;
		}
		Matcher tagMatcher = REGEXP_HTML_TAG.matcher(target);
		return tagMatcher.replaceAll(EMPTY);
	}
	
	/**
	 * 规整HTML串：
	 * 将串中所有双引号转换成单引号，以利于解析成JSON格式；
	 * 将串中所有换行符转换成单空格。
	 * @param target
	 * @return
	 */
	public static String uniformHTMLStr(String target){
		String singleQuoteForm = doubleQuoteToSingleQuote(target);
		String noLineBreak = removeLineBreak(singleQuoteForm);
		return noLineBreak;
	}
	
	/**
	 * 双引号转单引号
	 * @param target 目标字符串
	 * @return
	 */
	public static String doubleQuoteToSingleQuote(String target){
		if(target == null){
			return null;
		}
		Matcher quoteMatcher = REGEXP_DOUBLE_QUOTE.matcher(target);
		return quoteMatcher.replaceAll("'");
	}
	/**
	 * 清除字符串中所有换行符
	 * @param target 目标字符串
	 * @return
	 */
	public static String removeLineBreak(String target){
		if(target == null){
			return null;
		}
		Matcher breakMatcher = REGEXP_LINE_BREAK.matcher(target);
		return breakMatcher.replaceAll(SIGN_SINGLE_BLANK);
	}
	
	/**
	 * 连接字符串
	 * @param targets
	 * @return
	 */
	public static String catenate(String... targets){
		if(targets == null){
			throw new IllegalArgumentException("args 'targets' can not be null");
		}
		StringBuilder builder = new StringBuilder();
		for(String target : targets){
			builder.append(target);
		}
		return builder.toString();
	}
	
	/**
	 * 连接字符串
	 * @param targets
	 * @return
	 */
	public static String catenate(Object... targets){
		if(targets == null){
			throw new IllegalArgumentException("args 'targets' can not be null");
		}
		StringBuilder builder = new StringBuilder();
		for(Object target : targets){
			builder.append(target.toString());
		}
		return builder.toString();
	}
	
	/**
	 * 拼接数组或集合元素成字符串，并以指定分隔符分隔
	 * 本方法只处理数组或集合，否则，返回target.toString()
	 * 若数组或集合大小为0，则返回空字符串
	 * @param target 数组或集合
	 * @param token 分隔符
	 * @return
	 */
	public static String catenate(Object target, String token){
		Collection<?> targets = null;
		if(target == null || token == null){
			throw new IllegalArgumentException("args 'targets' can not be null");
		}
		StringBuilder builder = new StringBuilder();
		if(target.getClass().isArray()){
			targets = Arrays.asList(target);
		}else if(target instanceof Collection<?>){
			targets = (Collection<?>)target;
		}else{
			return target.toString();
		}
		if(targets.size() == 0){
			return StringUtil.EMPTY;
		}else{
			for(Object element : targets){
				builder.append(element);
				builder.append(token);
			}
			return builder.substring(0, builder.length() - token.length());
		}
	}
	
	/**
	 * 判断是否为Null或空
	 * @param target 目标字符串
	 * @return
	 */
	public static boolean nullOrEmpty(String target){
		return target==null || target.isEmpty();
	}
	
	/**
	 * 若目标字符串为空字符串（""），则转换为NULL，否则，返回原值
	 * @param target 目标字符串
	 * @return
	 */
	public static String emptyToNull(String target){
		return nullOrEmpty(target) ? null : target;
	}
	
	/**
	 * 以指定分隔符分隔字符串
	 * @param target 目标字符串
	 * @param token 分隔符
	 * @return 分隔后的子字符串集合
	 */
	public static List<String> splitByToken(String target, String token){
		if(target == null || token == null){
			throw new IllegalArgumentException("args can not be null");
		}
		String[] splitted = target.split(token);
		return Arrays.asList(splitted);
	}
	
	/**
	 * 获取指定分隔符前的字符串，不包含分隔符
	 * @param target 目标字符串
	 * @param token 分隔符
	 * @return 指定分隔符前的字符串
	 */
	public static String formerPartByToken(String target, String token){
		if(target == null || token == null){
			throw new IllegalArgumentException("args can not be null");
		}
		int tokenIndex = target.indexOf(token);
		return target.substring(0, tokenIndex);
	}
	
	/**
	 * 获取指定分隔符后的字符串，不包含分隔符
	 * @param target 目标字符串
	 * @param token 分隔符
	 * @return 指定分隔符后的字符串
	 */
	public static String latterPartByToken(String target, String token){
		if(target == null || token == null){
			throw new IllegalArgumentException("args can not be null");
		}
		int tokenIndex = target.indexOf(token);
		return target.substring(tokenIndex + 1);
	}
	/**
	 * 截断字符串
	 * @param target 目标字符串
	 * @param maxLength 最大长度
	 * @param appendEllipsis 追加省略号
	 * @return
	 */
	public static String truncate(String target, int maxLength, boolean appendEllipsis){
		if(target == null){
			return null;
		}
		if(target.length() > maxLength){
			if(appendEllipsis){
				return target.substring(0, maxLength) + SIGN_ELLIPSIS;
			}else{
				return target.substring(0, maxLength);
			}
		}else{
			return target;
		}
	}
	
	/**
	 * 判断字符串是否为空，不为空返回true
	 * @param string
	 * @return
	 */
	public static boolean isNotEmpty(String string) {
		return string != null && string.length() > 0;
	}
	
	/**
	 * 判断字符串是否为空，为空返回true
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string) {
		return string == null || string.length() == 0;
	}
	
	/**
	 * 根据数据库字段名获取JavaBean的字段名称
	 * 比如一个数据库字段名为aaa_bbb_ccm_pia则返回aaaBbbCcmPia
	 * @param key
	 * @return
	 */
	public static String getFieldName(String key){
		key = key.toLowerCase();
		if(key.contains("_")){ 
			int _index =key.indexOf('_'); //'_'第一次出现的位置
			String f = key.substring(0, _index);//aaa
			String p = key.substring(_index+1, key.length()); //bbb_ccm_pia
			String m = p.substring(0, 1).toUpperCase().concat(p.substring(1, p.length())); //Bbb_ccm_pia
			String result =f.concat(m);//aaaBbb_ccm_pia
			
			if(result.contains("_")){
				return getFieldName(result);
			}else{
				return result;
			}
		}else{
			return key;
		}
	}
	
	/**
	 * 检验fullStr是否包含str
	 * @param fullStr一个或者多个str，中间用,隔开
	 * @param str
	 * @return
	 */
	public static boolean validateContains(String fullStr, String str) {
		boolean flag = false;
		if(fullStr.contains(",")){
			String[] strArray = fullStr.split(",");
			for(String strCell : strArray){
				if(strCell.equals(str)){
					flag = true;
					break;
				}
			}
		}else{
			if(fullStr.equals(str)){
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 获取某个字符串中某个字符出现的次数
	 * @param str
	 * @param c
	 * @return
	 */
	public static int getCharNumInStr(String str, String c) {
		int count = 0;
		//如果字符串中有c
		while( str.indexOf(c)!=-1){
		  count++;
		  //将字符串出现c的位置之前的全部截取掉
		  str = str.substring(str.indexOf(c)+1);
		} 
		return count;
	}
	/**
	 * 
	* @Title: isNumericZidai 
	* @Description: 验证字符串是否是数字（正数）
	* @param @param str
	* @param @return    设定文件 
	* @author ShiChao 
	* @return boolean    返回类型 
	* @throws
	 */
	public static boolean isNumericZs(String str) {
		Pattern pattern = Pattern.compile("^\\d+(\\.\\d+)?$");
		return pattern.matcher(str).matches();
	}
	public static void main(String[] args){
		String colunmNameStr ="w,rget,ehy,d,h,gay";
		String str = colunmNameStr.substring(0, colunmNameStr.indexOf("gay"));
		int num = StringUtil.getCharNumInStr(str,",");
		System.out.println(num);
		
		List<Object> valueObj = new ArrayList<Object>();//值
		valueObj.add(1);
		valueObj.add("2");
		valueObj.add("3");
		System.out.println(valueObj);
		valueObj.set(2, 4);
		System.out.println(valueObj);
	}
}
