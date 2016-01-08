package org.framework.commons.db.utils;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.framework.commons.db.mapper.BaseMapper;
import org.junit.Test;

import junit.framework.TestCase;

public class TestMyBatisSessionFactory extends TestCase {
	@Test
	public void testSqlSession(){
		MyBatisSessionFactory.setConfigFile("default-mybatis.xml");
		SqlSession sqlSession=MyBatisSessionFactory.getSqlSession();
		assertNotNull(sqlSession);
		
		BaseMapper baseMapper=sqlSession.getMapper(BaseMapper.class);
		
		assertNotNull(baseMapper);
		
		List<Map<String, Object>> result=baseMapper.selectBySql("show tables");
		System.out.println(result.size());
		
		MyBatisSessionFactory.closeSqlSession();
		
	}
	
	@Test
	public void testSqlBuilder(){
		SQL sql=new SQL();
		sql.SELECT("column1,column2,column3,column4");
		sql.FROM("table1");
		sql.WHERE("column5=?");
		sql.OR();
		sql.WHERE("column5=6");
		sql.AND();
		sql.WHERE("column7=7");
	
		System.out.println(sql.toString());
		
		
	}
	
}
