var myPlaylist;

$( document ).ready(function() {
		$("#profile-image-chooser").change(function(event) {
			uploadProfileImage();
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
	        $("#profile-image-home").attr("src", "Profile-Image.html"+"?"+d.getTime());
	    },
	    error: function() {
	        alert("unable to create the record");
	    }
	});
}


