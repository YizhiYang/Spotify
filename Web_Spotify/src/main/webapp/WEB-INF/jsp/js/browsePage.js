$( document ).ready(function() {
	$("#Home-Browse-Button").click(function(event) {
		getBrowsePageContent();
		event.preventDefault();
	});
	
	$("#Home-Library-Overview-Button").click(function(event) {
		loadLibraryOverview();
		event.preventDefault();
	});
});

function loadLibraryOverview(){
	$('#centerSideContent').empty();
	
	addSongsToCenterContent(userFollowedSongs);
	$('.song-table-title').html("Songs I Like");
	
	addAlbumsToCenterContent(userFollowedAlbums);
	$('.album-table-title').html("Albums I Like");
	
	addArtistsToCenterContent(userFollowedArtists);
	$('.artist-table-title').html("Artists I Follows");
	
	lastAjaxCallToRenderToCenter = "loadLibraryOverview()";
}

function getBrowsePageContent(){
	$.ajax({
		type : "GET",
		url : "getBrowsePageContent.html",
        success: function (data) {
        	$('#centerSideContent').empty();
        	renderResults(data);
        	lastAjaxCallToRenderToCenter = "getBrowsePageContent()";
        }
	});
}

function renderResults(jsonString){
	var jsonObject = jQuery.parseJSON(jsonString);
	var songsJson = jsonObject.songsJson;
	var artistsJson = jsonObject.artistsJson;
	var albumsJson = jsonObject.albumsJson;
	//ADD ALL THREE TYPES OF RESULTS TO THE CENTER
	addSongsToCenterContent(songsJson);
	addArtistsToCenterContent(artistsJson);
	addAlbumsToCenterContent(albumsJson);
}