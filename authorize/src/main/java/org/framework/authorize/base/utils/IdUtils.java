package org.framework.authorize.base.utils;

import java.util.UUID;
/**
 * ID tool
 * @author ztgoto
 *
 */
public class IdUtils {
	/**
	 * 生成唯一值
	 * @return uuid
	 */
	public static String generatorUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
