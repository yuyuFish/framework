<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Login</title>

    <!-- Bootstrap -->
    <link href="<spring:url value="/static/css/bootstrap.min.css"></spring:url>" rel="stylesheet">
    <link href="<spring:url value="/static/css/flat-ui.min.css"></spring:url>" rel="stylesheet">
    <link href="<spring:url value="/static/css/common.css"></spring:url>" rel="stylesheet">
  </head>
  <body>
    <div class="container-fluid login-background">
      <div class="col-md-2"></div>
      <div class="col-md-2">
        <div class="mlogin-icon">
          <img src="<spring:url value="/static/img/login/icon.png"></spring:url>" alt="Welcome to Mail App">
          <h4><spring:message code="base.authen.web.login.label.prompt1"></spring:message><br/><small><spring:message code="base.authen.web.login.label.prompt2"></spring:message></small></h4>
        </div>
      </div>
      <div class="col-md-4">
        <form class="login-form" action="" method="post">
            <div class="form-group">
              <input type="text" id="login-name" placeholder="<spring:message code="base.authen.web.login.label.username"></spring:message>" name="username" value="${param.username }" class="form-control login-field">
              <label for="login-name" class="login-field-icon fui-user"></label>
            </div>

            <div class="form-group">
              <input type="password" id="login-pass" placeholder="<spring:message code="base.authen.web.login.label.password"></spring:message>" name="password" class="form-control login-field">
              <label for="login-pass" class="login-field-icon fui-lock"></label>
            </div>
            
            <div class="form-group">
              <div class="input-group">
	              <input type="text" id="login-captcha" placeholder="<spring:message code="base.authen.web.login.label.captcha"></spring:message>" name="captcha" class="form-control login-field">
	              <div class="input-group-addon"><img src="<spring:url value="/auth/authen/captcha"></spring:url>" id="captcha"/></div>
              </div>
            </div>
            
            <div class="form-group">
              <div class="bootstrap-switch-square">
	            <input type="checkbox" checked data-toggle="switch" id="custom-switch-03" data-on-text="<span class='fui-check'></span>" data-off-text="<span class='fui-cross'></span>" /> <spring:message code="base.authen.web.login.label.rememberMe"></spring:message>
	          </div>
            </div>

            <a href="#" class="btn btn-primary btn-lg btn-block"><spring:message code="base.authen.web.login.label.submit"></spring:message></a>
            <a href="#" class="login-link"><spring:message code="base.authen.web.login.label.forget"></spring:message></a>
          </form>
        </div>
        <div class="col-md-4"><a href="<spring:url value="/auth/authen/login?language=zh_CN"></spring:url>">中文</a>|
    		<a href="<spring:url value="/auth/authen/login?language=en_US"></spring:url>">English</a></div>
      </div>
   
    </div>
   <script type="text/javascript" src="<spring:url value="/static/js/jquery-1.11.3.min.js"></spring:url>"></script>
   <script type="text/javascript" src="<spring:url value="/static/js/flat-ui.min.js"></spring:url>"></script>
   <script type="text/javascript">
		$(document).ready(function(){
			$("#captcha").on("click",function(){
				
				var timestamp=new Date().getTime();
				var url="<spring:url value="/auth/authen/captcha"></spring:url>?timestamp="+timestamp;
				$("#captcha").attr("src",url);
			});
			$('[data-toggle="switch"]').bootstrapSwitch();
		});
	</script>
  </body>
</html>