$( document ).ready(function() {
		$("#signupUsername").blur(function(event) {

			checkUserNameViaAjax();
			event.preventDefault();

		});
		
		$("#signupUsername").focus(function(event) {
			clearMessage();
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