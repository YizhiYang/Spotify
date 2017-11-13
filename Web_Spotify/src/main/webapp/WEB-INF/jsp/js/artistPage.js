$(document).ready(function() {
	$("#Home-Artist-Button").click(function(event){
		loadAllArtists();
		event.preventDefault();
	});

});

function loadAllArtists(){
	$.ajax({
		type : "GET",
		url : "getAllArtists.html",
		success : function(data) {
			$('#centerSideContent').empty();
			addArtistsToCenterContent(data);
		}
	});
}

function goToArtistAlbums(artistID){
	$.ajax({
		type : "GET",
		url : "getAllAlbumsInArtist/" + artistID + ".html",
		success : function(data) {
			$('#centerSideContent').empty();
			addAlbumsToCenterContent(data);
		}
	});
}