<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
  </head>
  
  <body>
    <center>
    	<div>
    		<form action="" method="post">
    			<spring:message code="base.authen.web.login.label.username"></spring:message>
    			<input type="text" name="username" value="${param.username}" /><br/>
    			<spring:message code="base.authen.web.login.label.password"></spring:message>
    			<input type="password" name="password"  /><br/>
    			<spring:message code="base.authen.web.login.label.captcha"></spring:message>
    			<input type="text" name="captcha" />
    			<img src="auth/authen/captcha" id="captcha"/>
    			<input type="submit" value="login"/><br/>
    			${requestScope.errorMessage }
    		</form>
    		<a href="auth/authen/login?language=zh_CN">中文</a><br/>
    		<a href="auth/authen/login?language=en_US">英文</a>
    		
    	</div>
    </center>
    <script type="text/javascript" src="static/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#captcha").on("click",function(){
				var timestamp=new Date().getTime();
				var url="auth/authen/captcha?timestamp="+timestamp;
				$("#captcha").attr("src",url);
			});
		});
	</script>
  </body>
</html>
