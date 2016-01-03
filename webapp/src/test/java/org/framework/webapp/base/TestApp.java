package org.framework.webapp.base;

import org.junit.Test;

import junit.framework.TestCase;


public class TestApp extends TestCase {
	@Test
	public void testSay(){
		App app=new App();
		app.say();
	}
}
