$( document ).ready(function() {
	$("#Home-Browse-Button").click(function(event) {
		getBrowsePageContent(true);
		event.preventDefault();
	});
	
	$("#Home-Library-Overview-Button").click(function(event) {
		loadLibraryOverview();
		event.preventDefault();
	});
	
	$("#recommended-button").click(function(event){
		getRecommendedPage(false);
	});
	
	$("#editor-choice-button").click(function(event){
		goToEditorChoiceSongs();
	});
});

function loadLibraryOverview(){
	$('#centerSideContent').empty();
	
	addSongsToCenterContent(userFollowedSongs);
	$('.song-table-title').html("Songs I Like");
	
	addAlbumsToCenterContent(userFollowedAlbums);
	$('.album-table-title').html("Albums I Like");
	
	addArtistsToCenterContent(userFollowedArtists);
	$('.artist-table-title').html("Artists I Follow");
	
	lastAjaxCallToRenderToCenter = "loadLibraryOverview()";
}

function getBrowsePageContent(withGenre){
	getRecommendedPage(withGenre);
	
}

function getRecommendedPage(withGenre){
	getRecommendedSongsContent(withGenre);
	if(firstLoad == true){
		firstLoad = false;
	}else{
		lastAjaxCallToRenderToCenter = "getRecommendedPage()";
	}
}

function getRecommendedSongsContent(withGenre){
	$.ajax({
		type : "GET",
		url : "recommendedSongs.html",
        success: function (data) {
        	recommendedSongs = data;
        	$('#centerSideContent').empty();
        	addSongsToCenterContent(data);
        	$(".song-table-title").html("Songs You Might Like");
        	getRecommendedAlbumsContent(withGenre);
        }
	});
}

function getRecommendedAlbumsContent(withGenre){
	$.ajax({
		type : "GET",
		url : "recommendedAlbums.html",
        success: function (data) {
        	recommendedAlbums = data;
        	addAlbumsToCenterContent(data);
        	$(".album-table-title").html("Albums You Might Like");
        	getRecommendedArtistsContent(withGenre);
        }
	});
}

function getRecommendedArtistsContent(withGenre){
	$.ajax({
		type : "GET",
		url : "recommendedArtist.html",
        success: function (data) {
        	recommendedArtists = data;
        	addArtistsToCenterContent(data);
        	$(".artist-table-title").html("Artists You Might Like");
        	
        	if(withGenre){
        		addGenreBoxesToCenter();
        	}
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


function goToEditorChoiceSongs(){
	$.ajax({
		type : "GET",
		url : "editorChoices.html",
        success: function (data) {
        	$('#centerSideContent').empty();
        	addSongsToCenterContent(data);
        	$(".song-table-title").html("Songs Of Editors' Choice");
        	lastAjaxCallToRenderToCenter = "goToEditorChoiceSongs()";
        }
	});
}