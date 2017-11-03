<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title></title>
        <style type="text/css"><%@ include file="WEB-INF/jsp/css/login/bodyStyle.css" %> </style>
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
            <script src="js/jquery-1.7.2.min.js"></script>
    <script src="jsp/js/jquery-ui-1.8.21.custom.min.js"></script>
                        <script src="temp.js"></script>

    </head>
    <body>
        <div id="loginPageCenter">
            <div id="loginPageWrapper">
<!--                <div id='loginScreenLogo'>Re</div>-->
                <div id=loginSpace>
                    <div id=registerLogo>REGISTER</div>
                    <form action='signup.html' method='POST'>
                      <input class="usernamePassword" type="text" name="email" placeholder="Email">
                      <input class="usernamePassword" id="signupUsername" type="text" name="username" placeholder="Username">
                      <div id="feedback" style="display:none; color:red">
                  		Username alraedy taken.
                  		</div>
                  		<div id="feedback-success" style="display:none; color:red">
                  		Username is available.
                  		</div>
                      <input class="usernamePassword" type="password" name="password" placeholder="Password">                      
                      <input class="usernamePassword" type="password" name="confirm-password" placeholder="Confirm Password">
                      <input class="usernamePassword" type="password" name="location" placeholder="Location">
					  <input type = "submit" id='loginButton' value="submit">

                    </form>
                </div>
            </div>
            
        </div>
    </body>
</html>