$(document).ready(function() {
	$("#Home-Artist-Button").click(function(event){
		loadFollowedArtists();
		event.preventDefault();
	});
});

function loadFollowedArtists(){
	$.ajax({
		type : "GET",
		url : "loadFollowedArtists.html",
		success : function(data) {
			$('#centerSideContent').empty();
			addArtistsToCenterContent(data);
			lastAjaxCallToRenderToCenter = "loadFollowedArtists()";
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
			lastAjaxCallToRenderToCenter = "goToArtistAlbums(" + artistID + ")";
		}
	});
}