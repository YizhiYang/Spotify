$( document ).ready(function() {
		$("#searchButton").click(function(event) {
			sendSearchRequest();	
			event.preventDefault();
		});
});

function sendSearchRequest(){
	var searchContent = $('#searchInput').serialize();
	$.ajax({
		type : "GET",
		url : "searchContent.html",
		data : searchContent,
        success: function (data) {
        	$('#centerSideContent').empty();
        	renderAllSearchResults(data);
        }
	});
}

function renderAllSearchResults(jsonString){
	var jsonObject = jQuery.parseJSON(jsonString);
	var songsJson = jsonObject.songsJson;
	var artistsJson = jsonObject.artistsJson;
	var albumsJson = jsonObject.albumsJson;
	//RENDER ALL THREE CATEGORIES
	addSongsToCenterContent(songsJson);
	addArtistsToCenterContent(artistsJson);
	addAlbumsToCenterContent(albumsJson);
}