package org.framework.authorize.auth.services;

import javax.annotation.Resource;

import org.framework.authorize.auth.model.SysResources;
import org.framework.authorize.base.utils.IdUtils;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestSysResourcesService extends AbstractJUnit4SpringContextTests {
	@Resource
	private SysResourcesService sysResourcesService;
	
	@Test
	public void testAdd(){
		String nodeid1=IdUtils.generatorUUID();
		String nodeid2=IdUtils.generatorUUID();
		String nodeid3=IdUtils.generatorUUID();
		String nodeid4=IdUtils.generatorUUID();
		
		SysResources node1=new SysResources();
		node1.setResourcesId(nodeid1);
		node1.setResourcesName("node 1");
		node1.setResourcesInfo("node 1 info");
		sysResourcesService.add(node1);
		
		SysResources node2=new SysResources();
		node2.setResourcesId(nodeid2);
		node2.setResourcesName("node 2");
		node2.setResourcesInfo("node 2 info");
		sysResourcesService.add(node2);
		
		SysResources node3=new SysResources();
		node3.setResourcesId(nodeid3);
		node3.setParentId(nodeid1);
		node3.setResourcesName("node 1-1");
		node3.setResourcesInfo("node 1-1 info");
		sysResourcesService.add(node3);
		
		SysResources node4=new SysResources();
		node4.setResourcesId(nodeid4);
		node4.setParentId(nodeid3);
		node4.setResourcesName("node 1-1-1");
		node4.setResourcesInfo("node 1-1-1 info");
		sysResourcesService.add(node4);
	}
	
	@Test
	public void testChangePoint(){
		sysResourcesService.changePoint("e12ef208646349d1b07d538a5d1e5fe9", "92f190cbb98244d7883196c6cf67179c", "");
	}
}
