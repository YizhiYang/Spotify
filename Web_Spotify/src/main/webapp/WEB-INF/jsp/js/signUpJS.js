$( document ).ready(function() {
		$("#signupUsername").blur(function(event) {
			checkUserNameViaAjax();
			event.preventDefault();
		});
		
		$("#signupUsername").focus(function(event) {
			clearMessage();
		});
		
		$("#signUpForm").submit(function(event) {
			signUpViaAjax();
			event.preventDefault();

		});
		
		$("#signupPasswordRepeat").blur(function(event){
			var password = $("#signupPassword").val();
			var passwordRepeat = $(this).val();
			if(!(password === passwordRepeat)){
				displayPasswordErrorMessage();
			}
		});
		
		$("#signupPasswordRepeat").focus(function(event) {
			ClearPasswordErrorMessage();
		});

	});

	function checkUserNameViaAjax() {
		
		var data2 = $('#signupUsername').serialize();
			
		$.ajax({
			type : "GET",
			url : "validateUsername.html",
			data : data2,
			success : function(data) {
				console.log("SUCCESS: ", data);
				if(data === "false"){
					displayErrorMessage();
				}else{
					displaySuccessMessage();
				}
			}
		});

	}
	
	function signUpViaAjax() {
		
		var password = $("#signupPassword").val();
		var passwordRepeat = $("#signupPasswordRepeat").val();
		if(!(password === passwordRepeat)){
			displayPasswordErrorMessage();
			return;
		}
		
		var data2 = $('#signUpForm').serialize();
			
		$.ajax({
			type : "POST",
			url : "signup.html",
			data : data2,
			success : function(data) {
				console.log("SUCCESS: ", data);
				if(data === "false"){
					displayErrorMessage();
				}else{
					$("#signupPopover").modal('show');
					setTimeout(function(){window.location.href="/Web_Spotify";}, 3000);
				}
			}
		});

	}
	
	function ClearPasswordErrorMessage() {
		document.getElementById("password-feedback").style.display="none";
	}
	
	function displayPasswordErrorMessage() {
		document.getElementById("password-feedback").style.display="inline";
	}
	
	function clearMessage(){
		document.getElementById("feedback").style.display="none";
		document.getElementById("feedback-success").style.display="none";
	}
	
	function displayErrorMessage() {
		document.getElementById("feedback").style.display="inline";
	}
	
	function displaySuccessMessage() {
		document.getElementById("feedback-success").style.display="inline";
	}