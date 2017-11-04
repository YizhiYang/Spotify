$( document ).ready(function() {
		$("#profile-image-chooser").change(function(event) {
			uploadProfileImage();
		});
	});


function uploadProfileImage(){
	var formData = new FormData($("#uploadProfileImageForm")[0]);
	
	$.ajax({
	    url: "ProfileImageUpload.html",
	    type: "POST",
	    contentType: false,
	    processData: false,
	    cache: false,
	    data: formData,
	    success: function(response) {
	        alert("success");
	    },
	    error: function() {
	        alert("unable to create the record");
	    }
	});
}