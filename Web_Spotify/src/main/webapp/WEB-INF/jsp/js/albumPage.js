$(document).ready(function() {
	$("#Home-Album-Button").click(function(event){
		loadFollowedAlbums();
		event.preventDefault();
	});
});

function loadFollowedAlbums(){
	$.ajax({
		type : "GET",
		url : "loadFollowedAlbums.html",
		success : function(data) {
			$('#centerSideContent').empty();
			addAlbumsToCenterContent(data);
			lastAjaxCallToRenderToCenter = "loadFollowedAlbums()";
		}
	});
}

function goToAlbumSongs(albumID){
	$.ajax({
		type : "GET",
		url : "getAllSongsInAlbum/" + albumID + ".html",
		success : function(data) {
			$('#centerSideContent').empty();
			addSongsToCenterContent(data);
			lastAjaxCallToRenderToCenter = "goToAlbumSongs(" + albumID + ")";
		}
	});
}