$( document ).ready(function() {
		$("#signupUsername").blur(function(event) {

			checkUserNameViaAjax();
			event.preventDefault();

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
	
	function displayErrorMessage() {
		document.getElementById("feedback").style.display="inline"
	}
	
	function displaySuccessMessage() {
		document.getElementById("feedback-success").style.display="inline"
	}