package org.framework.authorize.base.utils;

import java.util.Date;

/**
 * date tool
 * @author ztgoto
 *
 */
public class DateUtils {
	/**
	 * 计算两个时间段交集部分时长
	 * @param begin1	第一个时间段起始时间
	 * @param end1		第一个时间段结束时间
	 * @param begin2	第二个时间段起始时间
	 * @param end2		第二个时间段结束时间
	 * @return
	 */
	public static long dateIntersection(Date begin1,Date end1,Date begin2,Date end2){
		long inter=0;
		if(begin1==null) begin1=new Date();
		if(end1==null) end1=new Date();
		if(begin2==null) begin2=new Date();
		if(end2==null) end2=new Date();
		long begin1Ms=begin1.getTime();
		long end1Ms=end1.getTime();
		long begin2Ms=begin2.getTime();
		long end2Ms=end2.getTime();
		inter=(((end1Ms-begin1Ms)+(end2Ms-begin2Ms))-(Math.abs(begin2Ms-begin1Ms)+Math.abs(end2Ms-end1Ms)))/2;
		
		return inter<0?0:inter;
	}
}
