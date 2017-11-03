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
			url : "testing.html",
			data : data2,
			success : function(data) {
				console.log("SUCCESS: ", data);
				if(data === "false"){
					displayErrorMessage();
				}else{
					window.location.href="goToHome.html";
				}
			}
		});

	}
	
	function objectifyForm(formArray) {//serialize data function

		  var returnArray = {};
		  for (var i = 0; i < formArray.length; i++){
		    returnArray[formArray[i]['name']] = formArray[i]['value'];
		  }
		  return returnArray;
	}
	
	function displayErrorMessage() {
		
		document.getElementById("feedback").style.display="inline"
	}