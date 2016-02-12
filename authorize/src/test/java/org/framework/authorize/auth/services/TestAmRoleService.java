package org.framework.authorize.auth.services;

import javax.annotation.Resource;

import org.framework.authorize.auth.model.AmRole;
import org.framework.authorize.base.utils.IdUtils;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestAmRoleService extends AbstractJUnit4SpringContextTests {

	@Resource
	private AmRoleService amRoleService;
	
	@Test
	public void testAdd(){
		AmRole amRole=new AmRole();
		amRole.setRoleId(IdUtils.generatorUUID());
		amRole.setParentId("22f9f94ab17e494d93df9a37c1b7a784");
		amRole.setRoleName("节点1-1-1");
		amRole.setRoleInfo("节点1-1-1描述");
		amRole.setRoleCode("1-1-1");
		amRoleService.add(amRole);
	}
	
	@Test
	public void testChangePoint(){
		amRoleService.changePoint("22f9f94ab17e494d93df9a37c1b7a784", "3e9f6098b36c46f48334a073d173d44a", "");
	}
	
	@Test
	public void testGetLevel(){
		int lev=amRoleService.getLevel("0643d676261f4b0fab7747fcd3aa8979");
		System.out.println(lev);
	}
}
