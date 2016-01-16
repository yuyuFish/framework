package org.framework.authorize.base.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlUtils {
	/**
	 * 
	 * 功能简述：移除sql语句中的排序语句
	 * <br>
	 * 功能详细描述：<功能详细描述>
	 * 
	 * @param sql
	 * @return
	 */
	public static String removeOrderSql(String sql){
		StringBuffer sb=new StringBuffer();
		Pattern patt=Pattern.compile("order\\s+by\\s+(\\w+\\.)?\\w+\\s+(desc|asc)?(\\s*,\\s*(\\w+\\.)?\\w+\\s*(desc|asc)?)*",Pattern.CASE_INSENSITIVE);
		Matcher mat=patt.matcher(sql);
		while(mat.find()){
			mat.appendReplacement(sb, " ");
		}
		mat.appendTail(sb);
		return sb.toString();
	}
}
