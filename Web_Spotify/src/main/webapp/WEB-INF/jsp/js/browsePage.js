$( document ).ready(function() {
	$("#Home-Browse-Button").click(function(event) {
		getRecommendedPage();
		event.preventDefault();
	});
	
	$("#Home-Library-Overview-Button").click(function(event) {
		loadLibraryOverview();
		event.preventDefault();
	});
	
	$("#recommended-button").click(function(event){
		getRecommendedPage();
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

function getRecommendedPage(){
	getRecommendedSongsContent();
	lastAjaxCallToRenderToCenter = "getRecommendedPage()";
}

function getRecommendedSongsContent(){
	$.ajax({
		type : "GET",
		url : "recommendedSongs.html",
        success: function (data) {
        	recommendedSongs = data;
        	$('#centerSideContent').empty();
        	addSongsToCenterContent(data);
        	getRecommendedAlbumsContent();
        }
	});
}

function getRecommendedAlbumsContent(){
	$.ajax({
		type : "GET",
		url : "recommendedAlbums.html",
        success: function (data) {
        	recommendedAlbums = data;
        	addAlbumsToCenterContent(data);
        	getRecommendedArtistsContent();
        }
	});
}

function getRecommendedArtistsContent(){
	$.ajax({
		type : "GET",
		url : "recommendedArtist.html",
        success: function (data) {
        	recommendedArtists = data;
        	addArtistsToCenterContent(data);
        	changeTableTitlesForRecommended();
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

function changeTableTitlesForRecommended(){
	$(".song-table-title").html("Songs You Might Like");
	$(".artist-table-title").html("Artists You Might Like");
	$(".album-table-title").html("Albums You Might Like");
}