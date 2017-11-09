$( document ).ready(function() {
		$("#signInForm").submit(function(event) {

			signInViaAjax();
			event.preventDefault();

		});
	});

	function signInViaAjax() {
		
		var data2 = $('#signInForm').serialize();
			
		$.ajax({
			type : "POST",
			url : "login.html",
			data : data2,
			success : function(data) {
				console.log("SUCCESS: ", data);
				if(data === "failure"){
					displayErrorMessage();
				}else{
					window.location.href="goToHome.html";
				}
			}
		});

	}
	
	function displayErrorMessage() {
		
		document.getElementById("feedback").style.display="inline"
	}