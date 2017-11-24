//THE LOADED LIST OF SONGS FOR THE MUSIC PLAYER
var myPlaylist;
//STORES THE LIST OF SONGS USER IS FOLLOWING
var userFollowedSongs;
//STORES THE LIST OF ALBUMS
var userFollowedAlbums;
//STORES THE LIST OF ALBUMS
var userFollowedArtists;

$( document ).ready(function() {
		getUserName();
		reloadFollowedSongs();
		reloadFollowedAlbums();
		
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
		
		$("#logout-button").click(function(event) {
			logout();
		});
	});


function getUserName(){
	$.ajax({
	    url: "getUserNameOnPageLoad.html",
	    type: "GET",
	    success: function(response) {
	    	$("#username").html(response);
	    }
	});
}


function uploadProfileImage(){
	//var formData = new FormData($("#uploadProfileImageForm")[0]);
	
	var formData = new FormData();
	
	formData.append($('#profile-image-chooser')[0].name,$('#profile-image-chooser')[0].files[0]);
	
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
	        alert("haha");
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

function logout(){
	$.ajax({
		type : "POST",
		url : "logout.html",
		success : function(data) {
			window.location.replace("index.jsp");
		}
	});
}

function reloadFollowedSongs(){
	$.ajax({
		type : "GET",
		url : "loadFollowedSongs.html",
		success : function(data) {
			userFollowedSongs = data;
		}
	});
}

function reloadFollowedAlbums(){
	$.ajax({
		type : "GET",
		url : "loadFollowedAlbums.html",
		success : function(data) {
			userFollowedAlbums = data;
		}
	});
}

function reloadFollowedArtists(){
	$.ajax({
		type : "GET",
		url : "loadFollowedArtists.html",
		success : function(data) {
			userFollowedArtists = data;
		}
	});
}
