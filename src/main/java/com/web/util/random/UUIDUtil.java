package com.web.util.random;

import java.util.UUID;

/**
 * UUID工具类
 * 
 * @author wzj
 * @version v1.0
 */
public class UUIDUtil {
	
	/**
	 * 生成UUID字符串
	 * @return
	 */
	public static String generateUuid(){
		return UUID.randomUUID().toString().replace("-","");
	}
}
