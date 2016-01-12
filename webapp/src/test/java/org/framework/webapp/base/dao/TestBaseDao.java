package org.framework.webapp.base.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.framework.webapp.base.utils.IdUtils;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestBaseDao extends AbstractJUnit4SpringContextTests {
	@Resource
	private BaseDao baseDao;
	
	@Test
	public void testSelectBySql(){
		List result=baseDao.selectBySql("show tables");
		System.out.println(result.size());
	}
	
	@Test
	public void testInsertBySql(){
		String sql="insert into sys_product_line(PRODUCT_ID,PARENT_PRODUCT_ID,PRODUCT_NAME,PRODUCT_INFO"
				+ ",PRODUCT_PROTOCOL,PRODUCT_HOSTNAME,PRODUCT_IP,PRODUCT_PORT,PRODUCT_CODE,LEFT_VALUE"
				+ ",RIGHT_VALUE,CREATE_TIME,EDIT_TIME,DELETE_TIME,DATA_STATE) "
				+ "values(#{parameters[0]},#{parameters[1]},#{parameters[2]},#{parameters[3]},#{parameters[4]}"
				+ ",#{parameters[5]},#{parameters[6]},#{parameters[7]},#{parameters[8]},#{parameters[9]},#{parameters[10]},#{parameters[11]}"
				+ ",#{parameters[12]},#{parameters[13]},#{parameters[14]})";
		int result=baseDao.insertBySql(sql, IdUtils.generatorUUID(),null,"测试","测试数据","http"
				,"www.devgoto.com","192.168.102.181",80,"webapp",1L,2L,new Date(),new Date(),null,"normal");
		System.out.println(result);
	}
}
