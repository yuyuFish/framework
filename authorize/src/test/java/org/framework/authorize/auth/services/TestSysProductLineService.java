package org.framework.authorize.auth.services;

import javax.annotation.Resource;

import org.framework.authorize.auth.model.SysProductLine;
import org.framework.authorize.base.utils.IdUtils;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestSysProductLineService extends AbstractJUnit4SpringContextTests {
	@Resource
	private SysProductLineService sysProductLineService;
	
	@Test
	public void testAdd(){
		String nodeid1=IdUtils.generatorUUID();
		String nodeid2=IdUtils.generatorUUID();
		String nodeid3=IdUtils.generatorUUID();
		String nodeid4=IdUtils.generatorUUID();
		
		SysProductLine node1=new SysProductLine();
		node1.setProductId(nodeid1);
		node1.setProductName("node 1");
		node1.setProductInfo("node 1 info");
		sysProductLineService.add(node1);
		
		SysProductLine node2=new SysProductLine();
		node2.setProductId(nodeid2);
		node2.setProductName("node 2");
		node2.setProductInfo("node 2 info");
		sysProductLineService.add(node2);
		
		SysProductLine node3=new SysProductLine();
		node3.setProductId(nodeid3);
		node3.setParentId(nodeid1);
		node3.setProductName("node 1-1");
		node3.setProductInfo("node 1-1 info");
		sysProductLineService.add(node3);
		
		SysProductLine node4=new SysProductLine();
		node4.setProductId(nodeid4);
		node4.setParentId(nodeid3);
		node4.setProductName("node 1-1-1");
		node4.setProductInfo("node 1-1-1 info");
		sysProductLineService.add(node4);
	}
	
	@Test
	public void testChangePoint(){
		sysProductLineService.changePoint("e7f69f19bb6e4ecb961e2153b1c63a6f", "a8d4fdd531564547ab8717b6f29d168e", "");
	}
}
