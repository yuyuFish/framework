<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
    <input type="search" />
    	<div>
    		<form:form method="POST" commandName="user" >
    			<form:input path="username" /> <form:errors path="username"></form:errors>
    			<form:password path="password" /> <form:errors path="password"></form:errors>
    			<input type="submit" value="login"/>
    			<input name="language" value="en_US" type="hidden" /><br/>
    			<img src="auth/authen/captcha" id="captcha"/>
    			<div style="color: red;"><form:errors path="*"></form:errors></div>
    			
    		</form:form>
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
