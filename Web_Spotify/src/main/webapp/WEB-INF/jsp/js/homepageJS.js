//MEDIA PLAYER OBJECT
var player;
//THE LIST OF SONGS FOR THE MUSIC PLAYER
var myPlaylist=[];
//THE LENGTH OF PLAYLIST LOADED IN PLAYER
var playlistLength;
//THE INDEX OF THE SONG BEING PLAYED BY PLAYER
var currentPlayingIndex;
//REPEAT TYPE ( 0 for no repeat, 1 for one song repeat, 2 for playlist repeat, 3 for shuffle)
var currentRepeatType = 0;
//IF SHUFFLE
var isShuffleOn = false;
//CURRENT SONG LYRICS
var currentLyricsLine;
//CURRENT SONG LYRICS TIME
var currentLyricsTime;
//STORES THE LIST OF SONGS USER IS FOLLOWING
var userFollowedSongs;
//STORES THE LIST OF ALBUMS
var userFollowedAlbums;
//STORES THE LIST OF ALBUMS
var userFollowedArtists;
//STORES USER PLAYLISTS
var userPlaylists=[];
//STORES PLAYLIST CURRENTLY BEING DISPLAYED;
var selectedPlaylistId;
//STORES WHAT USERTYPE THIS USER IS, BASIC, PREMUIUM, ARTIST, ADMIN
var userType;
//RECORDS THE LAST AJAX SO WE CAN REFRESH WHEN NEEDED
var lastAjaxCallToRenderToCenter;



//FOR ALBUM PAGE
var selectedAlbumName;
//FOR ARTIST PAGE
var selectedArtistName;
//ARTIST OWNED ALBUMS
var ownedAlbumIDs=[];
//LOGGED IN ARTIST ID
var loggedInArtistId = -1;


//FRIENDS
var friendIds=[];



$( document ).ready(function() {
		//GET INITIAL DATA NEEDED AND STORE THEM
		getUserTypeAndRerender();
		getUserName();
		reloadFollowedSongs(false);
		reloadFollowedAlbums(false);
		reloadFollowedArtists(false);
		reloadOwnedAlbumIDs(false);
		loadLoggedInArtistID();
		getBrowsePageContent();
		//REGISTER ACCOUNT RELATED EVENTS
		$("#profile-image-chooser").change(function(event) {
			uploadProfileImage();
		});
		$("#save-user-info-update-changes-button").click(function(event) {
			updateUserInfo();
		});
		$("#confirm-upgrade-button").click(function(event){
			upgradeAccount();
		});
		$("#confirm-downgrade-button").click(function(event){
			downgradeAccount();
		});
		$("#logout-button").click(function(event) {
			logout();
		});
		
		$("#remove-loggedin-user-button").click(function(event) {
			$("#confirm-button").off("click");
			$("#confirm-button").click(function(event){
				removeThisAccount();
			});
			$("#editUserInfoPopUp").modal("hide");
			$("#confirmationMessage").html("Are you sure you want to unregister your account?");
			$("#confirmationPopUp").modal("show");
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

function upOrDowngradeAccountPopUp(){
	if(userType == "BASIC"){
		$('#accountUpgradePopUp').modal('show');
	}else if(userType == "PREMIUM"){
		$('#accountDowngradePopUp').modal('show');
	}
}

function upgradeAccount(){
	var creditCardInfoData = $('#credit-card-form').serialize();
	$.ajax({
		type : "POST",
		url : "upgradeAccount.html",
		data: creditCardInfoData,
		success : function(data) {
			location.reload();
		}
	});
}

function downgradeAccount(){
	$.ajax({
		type : "POST",
		url : "downgradeAccount.html",
		success : function(data) {
			location.reload();
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

function removeThisAccount(){
	$.ajax({
		type : "POST",
		url : "removeAccount.html",
		success : function(data) {
			window.location.replace("index.jsp");
		}
	});
}

function adminRemoveAccount(userId){
	$.ajax({
		type : "POST",
		url : "removeAccountByAdmin/" + userId + ".html",
		success : function(data) {
			getFriendList();
		}
	});
}

function reloadFollowedSongs(isRefreshCall){
	$.ajax({
		type : "GET",
		url : "loadFollowedSongs.html",
		success : function(data) {
			userFollowedSongs = data;
			if(isRefreshCall){
				//IF WE ARE ON THE FOLLOWED SONG PAGE, THEN WE NEED TO REMOVE THIS SONG BY REFRESHING CENTER CONTENT
	        	refreshCenterContent();
			}
		}
	});
}

function reloadFollowedAlbums(isRefreshCall){
	$.ajax({
		type : "GET",
		url : "loadFollowedAlbums.html",
		success : function(data) {
			userFollowedAlbums = data;
			if(isRefreshCall){
				//IF WE ARE ON THE FOLLOWED SONG PAGE, THEN WE NEED TO REMOVE THIS SONG BY REFRESHING CENTER CONTENT
	        	refreshCenterContent();
			}
		}
	});
}

function reloadFollowedArtists(isRefreshCall){
	$.ajax({
		type : "GET",
		url : "loadFollowedArtists.html",
		success : function(data) {
			userFollowedArtists = data;
			if(isRefreshCall){
				//IF WE ARE ON THE FOLLOWED SONG PAGE, THEN WE NEED TO REMOVE THIS SONG BY REFRESHING CENTER CONTENT
	        	refreshCenterContent();
			}
		}
	});
}

function reloadOwnedAlbumIDs(isRefreshCall){
	$.ajax({
		type : "GET",
		url : "loadOwnedAlbums.html",
		success : function(data) {
			ownedAlbumIDs = [];
			if(data!=""){
				ownedAlbumIDs = data.split(",");
			}
			if(isRefreshCall){
	        	refreshCenterContent();
			}
		}
	});
}

function loadLoggedInArtistID(){
	$.ajax({
		type : "GET",
		url : "loadLoggedInArtistID.html",
		success : function(data) {
			loggedInArtistId = data;
		}
	});
}

function getUserTypeAndRerender(){
	$.ajax({
		type : "GET",
		url : "getUserType.html",
		success : function(data) {
			userType = data;
			displayUserType(userType);
			if(userType != "ADMIN"){
				hideAdminGUI();
			}
			if(userType != "ARTIST"){
				hideArtistGUI();
			}
		}
	});
}

function displayUserType(userType){
	if(userType == "BASIC"){
		$("#usertype").html("Basic User");
	}else if(userType == "PREMIUM"){
		$("#usertype").html("Premium User");
	}else if(userType == "ARTIST"){
		$("#usertype").html("Artist User");
	}else if(userType == "ADMIN"){
		$("#usertype").html("Admin User");
	}
}


function hideAdminGUI(){
	$("#admin-menu-header").hide();
	$("#Admin-UploadSong-Button").parent().hide();
	$("#Admin-CreateAlbum-Button").parent().hide();
	$("#Admin-MakeUserArtist-Button").parent().hide();
	$("#Admin-PendingSongs-Button").parent().hide();
	$("#Admin-AddAdvertisement-Button").parent().hide();
}

function hideArtistGUI(){
	$("#artist-menu-header").hide();
	$("#Artist-My-Albums-Button").parent().hide();
	$("#Artist-My-Followers-Button").parent().hide();
}

function refreshCenterContent(){
	console.log(lastAjaxCallToRenderToCenter);
	eval(lastAjaxCallToRenderToCenter);
}
