$(document).ready(function() {
	$("#Home-Album-Button").click(function(event){
		loadFollowedAlbums();
		event.preventDefault();
	});
	
	$("#Artist-My-Albums-Button").click(function(event){
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
			
			lastAjaxCallToRenderToCenter = "goToAlbumSongs(" + albumID + ")";
		}
	});
}

function addAlbumTitleToSongPage(albumID){
	$('.song-table-title').html(selectedAlbumName);
	if(userType == "ADMIN"){
		$('.song-table-title').parent().append('<div id="addSongToAlbumButton">Add Song To Album</div>');
		
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
			$('.song-table-title').parent().append('<div id="addSongToAlbumButton">Add Song To Album</div>');
			
			$('#addSongToAlbumButton').click(function(event){
				$('#formGroupAlbumIDInput').prop('disabled', true);
				$('#formGroupAlbumIDInput').val(albumID);
				$('#uploadSongPopUp').modal('show');
			});
		}
	}
}