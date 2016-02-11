package org.framework.authorize.auth.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.framework.authorize.auth.model.AmGroup;
import org.framework.authorize.base.utils.IdUtils;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestAmGroupDao extends AbstractJUnit4SpringContextTests {
	@Resource
	private AmGroupDao amGroupDao;
	
	@Test
	public void testSave(){
		AmGroup amGroup=new AmGroup();
		amGroup.setGroupId(IdUtils.generatorUUID());
		amGroup.setProductId(IdUtils.generatorUUID());
		amGroup.setParentId(null);
		amGroup.setGroupName("测试组");
		amGroup.setGroupInfo("测试组描述");
		amGroup.setGroupCode("code");
		amGroup.setIsInherit(0);
		amGroup.setLeftValue(1L);
		amGroup.setRightValue(2L);
		Date current=new Date();
		amGroup.setCreateTime(current);
		amGroup.setEditTime(current);
		amGroup.setDeleteTime(null);
		amGroup.setDataState("normal");
		int result=amGroupDao.save(amGroup);
		System.out.println(result);
	}
	
	@Test
	public void testUpdate(){
		AmGroup amGroup=new AmGroup();
		amGroup.setGroupId("5a6e33e98c1b4ebfa8c4cf5d71fe80e8");
		amGroup.setProductId(IdUtils.generatorUUID());
		amGroup.setParentId(null);
		amGroup.setGroupName("测试组");
		amGroup.setGroupInfo("测试组描述");
		amGroup.setGroupCode("code");
		amGroup.setIsInherit(0);
		amGroup.setLeftValue(1L);
		amGroup.setRightValue(2L);
		Date current=new Date();
		amGroup.setCreateTime(current);
		amGroup.setEditTime(current);
		amGroup.setDeleteTime(null);
		amGroup.setDataState("normal");
		int result=amGroupDao.update(amGroup);
		System.out.println(result);
	}
	
	@Test
	public void testGetById(){
		AmGroup result=amGroupDao.getById("5a6e33e98c1b4ebfa8c4cf5d71fe80e8");
		System.out.println(result);
	}
	
	@Test
	public void testGetAll(){
		List<AmGroup> result=amGroupDao.getAll();
		System.out.println(result);
	}
	
	@Test
	public void testGetByWhere(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("groupId", null);
		List<AmGroup> result=amGroupDao.getByWhere("where PARENT_ID is #{pa.groupId}", "pa", map);
		System.out.println("$$$$:"+result.size());
	}
	
	@Test
	public void testGetByWherePage(){
		List<AmGroup> result=amGroupDao.getByWherePage("PARENT_ID=#{par[0]}", 0L, 5L, "par", "ba297b14882d4e6791330d8774c152e6");
		System.out.println(result.size());
	}
}
