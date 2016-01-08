<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="static/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">
		function change(obj){
			$(obj).attr("src",$(obj).attr("src"));
		}
	</script>
  </head>
  
  <body>
    <center>
    	<div>
    		<form:form method="POST" commandName="user" >
    			<form:input path="username" /> <form:errors path="username"></form:errors>
    			<form:password path="password" /> <form:errors path="password"></form:errors>
    			<input type="submit" value="login"/>
    			<input name="language" value="en_US" type="hidden" /><br/>
    			<img src="auth/authen/captcha" onclick="change(this)"/>
    			<div style="color: red;"><form:errors path="*"></form:errors></div>
    			
    		</form:form>
    	</div>
    </center>
  </body>
</html>
