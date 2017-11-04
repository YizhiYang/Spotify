<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title></title>
        <style type="text/css"><%@ include file="WEB-INF/jsp/css/login/bodyStyle.css" %> </style>
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">

    <script
  src="https://code.jquery.com/jquery-3.2.1.min.js"
  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
  crossorigin="anonymous"></script>
                        <script type="application/javascript"><%@ include file="WEB-INF/jsp/js/signUpJS.js" %></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script
  src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
  crossorigin="anonymous"></script>
    </head>
    <body>
    	<div class="modal fade" id="signupPopover">
  			<div class="modal-dialog" role="document">
    			<div class="modal-content">
      			<div class="modal-header">
        			<h5 class="modal-title">SignUp Success</h5>
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
        			</button>
      			</div>
      			<div class="modal-body">
        			<p>Congrats! You now have a Spotify account.</p>
      			</div>
      			<div class="modal-footer">
        			<button type="button" class="btn btn-primary" ><a href="/Web_Spotify" style="color:#fff;">Sign in</a></button>
      			</div>
    			</div>
  			</div>
		</div>
    
        <div id="loginPageCenter">
            <div id="loginPageWrapper">
<!--                <div id='loginScreenLogo'>Re</div>-->
                <div id=loginSpace>
                    <div id=registerLogo style="font-size:250%"; color="#ffcc00">REGISTER</div>
                    <br/>
                    
                    <button type="button" class="btn btn-primary btn-lg" id="Basic-Account-Button">Basic Account</button>
                    <button type="button" class="btn btn-primary btn-lg" id="Premium-Account-Button">Premium Account</button>
                    
                    <form id="signUpForm" method='POST'>
                      <input class="usernamePassword" type="text" name="email" placeholder="Email">
                      <input class="usernamePassword" id="signupUsername" type="text" name="username" placeholder="Username">
                      <div id="feedback" style="display:none; color:red">
                  		Username alraedy taken.
                  		</div>
                  		<div id="feedback-success" style="display:none; color:green">
                  		Username is available.
                  		</div>
                      <input class="usernamePassword" id="signupPassword" type="password" name="password" placeholder="Password">                      
                      <input class="usernamePassword" id="signupPasswordRepeat" type="password" name="confirm-password" placeholder="Confirm Password">
                      <div id="password-feedback" style="display:none; color:red">
                  		Password Repeat does not match!
                  		</div>
                  		
                  	  <input class="usernamePassword" id="cardNumber" type="text" style="display:none;" name="cardNumber" placeholder="Card number">                      
                      <input class="usernamePassword" id="cardName" type="text" style="display:none;" name="cardHolderName" placeholder="Card holder name">
                      <input class="usernamePassword" id="billingAddress" type="text" style="display:none;" name="billingAddress" placeholder="Billing address">
                      <input class="usernamePassword" id="expiredDate" type="text" style="display:none;" name="expiredDate" placeholder="Expiration date">                      
                      <input class="usernamePassword" id="cvv" type="text" name="cvv" style="display:none;" placeholder="CVV">
                      
                  		
                      <input class="usernamePassword" type="text" name="location" placeholder="Location">
					  <input type = "submit" id='loginButton' value="submit">

                    </form>
                </div>
            </div>
            
        </div>
    </body>
</html>