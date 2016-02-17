<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Login</title>

    <!-- Bootstrap -->
    <link href="../../static/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../static/css/flat-ui.min.css" rel="stylesheet">
    <link href="../../static/css/common.css" rel="stylesheet">
  </head>
  <body>
    
    <div class="container-fluid login-background">
      <div class="col-md-2"></div>
      <div class="col-md-2">
        <div class="mlogin-icon">
          <img src="../../static/img/login/icon.png" alt="Welcome to Mail App">
          <h4>Welcome to <small>Mail App</small></h4>
        </div>
      </div>
      <div class="col-md-4">
        <form class="login-form">
            <div class="form-group">
              <input type="text" id="login-name" placeholder="Enter your name" value="" class="form-control login-field">
              <label for="login-name" class="login-field-icon fui-user"></label>
            </div>

            <div class="form-group">
              <input type="password" id="login-pass" placeholder="Password" value="" class="form-control login-field">
              <label for="login-pass" class="login-field-icon fui-lock"></label>
            </div>

            <a href="#" class="btn btn-primary btn-lg btn-block">Log in</a>
            <a href="#" class="login-link">Lost your password?</a>
          </form>
        </div>
        <div class="col-md-4"></div>
      </div>
   
    </div>
   <script type="text/javascript" src="../../static/js/jquery-1.11.3.min.js"></script>
   <script type="text/javascript" src="../../static/js/bootstrap.min.js"></script>
  </body>
</html>