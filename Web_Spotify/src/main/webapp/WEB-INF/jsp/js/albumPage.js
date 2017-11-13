$(document).ready(function() {
	$("#Home-Album-Button").click(function(event){
		loadAllAlbums();
		event.preventDefault();
	});

});


function loadAllAlbums(){
	$.ajax({
		type : "GET",
		url : "getAllAlbums.html",
		success : function(data) {
			$('#centerSideContent').empty();
			addAlbumsToCenterContent(data);
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
		}
	});
}