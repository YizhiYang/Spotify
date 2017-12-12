$(document).ready(function() {
	$("#Home-Album-Button").click(function(event){
		loadFollowedAlbums();
		event.preventDefault();
	});
	
	$("#Artist-My-Albums-Button").click(function(event){
		selectedArtistName = $("#username").html();
		selectedArtistImageURL = "url(" + $(".profile-image-home").attr("src") +")";
		goToArtistAlbums(loggedInArtistId);
	});
});

function loadFollowedAlbums(){
//	$.ajax({
//		type : "GET",
//		url : "loadFollowedAlbums.html",
//		success : function(data) {
//			$('#centerSideContent').empty();
//			addAlbumsToCenterContent(data);
//			lastAjaxCallToRenderToCenter = "loadFollowedAlbums()";
//		}
//	});
	$('#centerSideContent').empty();
	addAlbumsToCenterContent(userFollowedAlbums);
	$('.album-table-title').html("Albums I Like");
	lastAjaxCallToRenderToCenter = "loadFollowedAlbums()";
}

function goToAlbumSongs(albumID){
	$.ajax({
		type : "GET",
		url : "getAllSongsInAlbum/" + albumID + ".html",
		success : function(data) {
			$('#centerSideContent').empty();
			addSongsToCenterContent(data);
			//CHANGE SONG TABLE TITLE
			addAlbumTitleToSongPage(albumID);
			getRelatedAlbums(albumID);
			lastAjaxCallToRenderToCenter = "goToAlbumSongs(" + albumID + ")";
		}
	});
}

function addAlbumTitleToSongPage(albumID){
	$('.song-table-title').html(selectedAlbumName);
	
	$('#PlayListPrototypeTop').css("background-image", selectedAlbumImageURL);
	$('#PlayListPrototypeTop').css("background-size", "cover");
	$('#PlayListPrototypeTop').css("min-height","500px");
	$('#PlayListPrototypeTop').children().eq(0).css("background-color", "rgba(0, 0, 0, 0.6)");
	$('#PlayListPrototypeTop').children().eq(0).css("min-height", "500px");
	$('.song-table-title').parent().append('<div id="album-top-buttons" style="position: absolute; bottom: 0; padding: 10px;"></div>');
	
	$('#album-top-buttons').append('<button id="playAlbumButton" class="btn btn-success" style="margin: 5px;">Play Album</button>');
	$("#playAlbumButton").click(function(event){
		player.jPlayer("setMedia", myPlaylist[0]);
		currentPlayingIndex = 0;
		player.jPlayer("play");
	});
	
	if(userType == "ADMIN"){
		$('#album-top-buttons').append('<button id="addSongToAlbumButton" class="btn btn-warning" style="margin: 5px;">Add Song To Album</button>');
		
		$('#addSongToAlbumButton').click(function(event){
			$('#formGroupAlbumIDInput').prop('disabled', true);
			$('#formGroupAlbumIDInput').val(albumID);
			$('#uploadSongPopUp').modal('show');
		});
		
		return;
	}
	
	//ELSE CHECK IF THIS USER OWNS THIS ALBUM
	for(i=0; i< ownedAlbumIDs.length; i++){
		if(albumID == ownedAlbumIDs[i]){
			$('#album-top-buttons').append('<button id="addSongToAlbumButton" class="btn btn-warning" style="margin: 5px;">Add Song To Album</button>');
			
			$('#addSongToAlbumButton').click(function(event){
				$('#formGroupAlbumIDInput').prop('disabled', true);
				$('#formGroupAlbumIDInput').val(albumID);
				$('#uploadSongPopUp').modal('show');
			});
		}
	}
	
}

function getRelatedAlbums(albumID){
	$.ajax({
		type : "GET",
		url : "getRelatedAlbum/" + albumID + ".html",
		success : function(data) {
			addAlbumsToCenterContent(data);
			$('.album-table-title').html("Related/Similar Albums");

		}
	});
}