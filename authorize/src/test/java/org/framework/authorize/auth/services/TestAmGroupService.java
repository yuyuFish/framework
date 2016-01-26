package org.framework.authorize.auth.services;

import javax.annotation.Resource;

import org.framework.authorize.auth.model.AmGroup;
import org.framework.authorize.base.utils.IdUtils;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestAmGroupService extends AbstractJUnit4SpringContextTests {

	@Resource
	private AmGroupService amGroupService;
	
	@Test
	public void testAddGroup(){
		AmGroup group=new AmGroup();
		group.setGroupId(IdUtils.generatorUUID());
		//group.setParentGroupId("a8deb8088ab14b909907e7b0fbb612a3");
		group.setGroupName("根节点3");
		group.setGroupInfo("根节点3描述");
		amGroupService.addGroup(group);
	}
	
}
