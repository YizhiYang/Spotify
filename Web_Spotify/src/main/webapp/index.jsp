<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>

    <!-- <link rel="stylesheet" href="css/login/bodyStyle.css" > -->
    
    <style type="text/css"><%@ include file="WEB-INF/jsp/css/login/bodyStyle.css" %> </style>
	<script
  src="https://code.jquery.com/jquery-3.2.1.min.js"
  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
  crossorigin="anonymous"></script>      
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
    
    <script type="application/javascript"><%@ include file="WEB-INF/jsp/js/signInJS.js" %></script>
    </head>
    <body>
    	
        <div id="loginPageCenter">
            <div id="loginPageWrapper">
<!--                <div id='loginScreenLogo'></div>-->
                <div id=loginSpace>
              	<div id="feedback" style="display:none; color:red">
                  	Invalid Password and Username
                  	</div>
                    <form id="signInForm" method="post">
                      <input class="usernamePassword" type="text" name="username" placeholder="Username or email"><br>
                      <input class="usernamePassword" type="password" name="password" placeholder="Password">
                      <input type = "submit" value="submit" id='loginButton'>
                    </form>
                  
                </div>
                <div id=accountSpace>
                    <div class="loginExtras" onclick="location.href='signup.jsp'">Sign up</div>
                    <div class="loginExtras">Reset Password</div>
                </div>
            </div>
            
        </div>
    </body>
</html>