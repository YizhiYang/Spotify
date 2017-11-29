$(document).ready(function() {
	$("#Home-Song-Button").click(function(event){
		loadFollowedSongs();
		event.preventDefault();
	});

});

function loadFollowedSongs(){
	//DATA IS ALREADY LOADED, JUST RENDER
	$('#centerSideContent').empty();
	addSongsToCenterContent(userFollowedSongs);
	lastAjaxCallToRenderToCenter = "loadFollowedSongs()";
}