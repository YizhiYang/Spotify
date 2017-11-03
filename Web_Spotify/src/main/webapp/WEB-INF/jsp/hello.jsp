<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>

    <!-- <link rel="stylesheet" href="css/login/bodyStyle.css" > -->
    
    <style type="text/css"><%@ include file="css/login/bodyStyle.css" %> </style>
          
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
    </head>
    <body>
    	
        <div id="loginPageCenter">
            <div id="loginPageWrapper">
<!--                <div id='loginScreenLogo'></div>-->
                <div id=loginSpace>
                    <form action='passwordController.html' method='POST'>
                      <input class="usernamePassword" type="text" name="username" placeholder="huigyoigyuifgtyi7"><br>
                      <input class="usernamePassword" type="password" name="password" placeholder="Password">
                      <input type = "submit" onclick="location.href/testing" value="submit">
                    </form>
                   
                </div>
                <div id=accountSpace>
                    <div class="loginExtras">Sign up test</div>
                    <div class="loginExtras">Reset Password</div>
                </div>
            </div>
            
        </div>
    </body>
</html>