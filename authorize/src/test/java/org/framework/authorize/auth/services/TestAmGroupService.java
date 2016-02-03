package org.framework.authorize.auth.services;

import java.util.List;

import javax.annotation.Resource;

import org.framework.authorize.auth.model.AmGroup;
import org.framework.authorize.base.utils.IdUtils;
import org.framework.authorize.base.utils.Pager;
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
		group.setParentGroupId("ba297b14882d4e6791330d8774c152e6");
		group.setGroupName("节点1-4");
		group.setGroupInfo("节点1-4描述");
		amGroupService.add(group);
	}
	
	@Test
	public void testChangePoint(){
		amGroupService.changePoint("d667181289fd4f0ebebe181095830dcb", "222b41cfb5d74a6981f95f1ad3f8c6a6", "");
	}
	
	@Test
	public void testDeleteLoic(){
		amGroupService.deleteLogic("a8deb8088ab14b909907e7b0fbb612a3");
	}
	
	@Test
	public void testDeleteLogicRestore(){
		amGroupService.deleteLogicRestore("a8deb8088ab14b909907e7b0fbb612a3");
	}
	
	@Test
	public void testDeletePhysical(){
		amGroupService.deletePhysical("a8deb8088ab14b909907e7b0fbb612a3");
	}
	
	@Test
	public void testGetDirectChild(){
		List<AmGroup> amGroups=amGroupService.getDirectChild("ba297b14882d4e6791330d8774c152e6");
		for (AmGroup amGroup : amGroups) {
			System.out.println(amGroup.getGroupName());
		}
	}
	
	@Test
	public void testGetDirectChildCount(){
		int result=amGroupService.getDirectChildCount("ba297b14882d4e6791330d8774c152e6");
		System.out.println(result);
	}
	
	@Test
	public void testGetAllChild(){
		List<AmGroup> amGroups=amGroupService.getAllChild("ba297b14882d4e6791330d8774c152e6");
		for (AmGroup amGroup : amGroups) {
			System.out.println(amGroup.getGroupName());
		}
	}
	
	@Test
	public void testGetAllChildCount(){
		int result=amGroupService.getAllChildCount("ba297b14882d4e6791330d8774c152e6");
		System.out.println(result);
	}
	
	@Test
	public void testGetPath(){
		List<AmGroup> amGroups=amGroupService.getPath("d667181289fd4f0ebebe181095830dcb");
		for (AmGroup amGroup : amGroups) {
			System.out.println(amGroup.getGroupName());
		}
	}
	
	@Test
	public void testGetLevel(){
		int result=amGroupService.getLevel("d667181289fd4f0ebebe181095830dcb");
		System.out.println(result);
	}
	
	@Test
	public void testGetByWherePage(){
		Pager<AmGroup> page=new Pager<AmGroup>();
		page.setCurPage(2);
		page.setPageSize(5);
		amGroupService.getByWherePage(page, "", null, "");
		System.out.println(page);
	}
	
}
