$( document ).ready(function() {
		$("#signInForm").submit(function(event) {
			signInViaAjax();
			event.preventDefault();
		});
});

function signInViaAjax() {
	var signInData = $('#signInForm').serialize();		
	$.ajax({
		type : "POST",
		url : "login.html",
		data : signInData,
		success : function(data) {
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