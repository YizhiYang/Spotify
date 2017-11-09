var myPlaylist;

$( document ).ready(function() {
		$("#profile-image-chooser").change(function(event) {
			uploadProfileImage();
		});
		
		$("#save-user-info-update-changes-button").click(function(event) {
			updateUserInfo();
		});
		
		
		
		
		$('#jquery_jplayer_1').on($.jPlayer.event.play,  function(e){
		    console.log("Current track", e.jPlayer.status.media);
		    console.log("Currentr track index", myPlaylist.current);
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
	        d = new Date();
	        $(".profile-image-home").attr("src", "Profile-Image.html"+"?"+d.getTime());
	    },
	    error: function() {
	        alert("unable to create the record");
	    }
	});
}


function editPopUp(){

	$.ajax({
		type : "GET",
		url : "getUserProfile.html",
		success : function(jsonData) {
			console.log(jsonData);
			var jsonObj = jQuery.parseJSON(jsonData);
			
			$('#userinfo-username-input').val(jsonObj.userName);
			$('#userinfo-email-input').val(jsonObj.email);
			$('#userinfo-location-input').val(jsonObj.location);
		
			$("#editUserInfoPopUp").modal('show');
		}
	});	
}

function updateUserInfo(){
	var newUserInfoData = $('#update-user-info-form').serialize();
	
	$.ajax({
		type : "POST",
		url : "changeUserProfileInfo.html",
		data : newUserInfoData,
		success : function(data) {
			console.log(data);
			$('#editUserInfoPopUp').modal('hide');
		}
	});

}

