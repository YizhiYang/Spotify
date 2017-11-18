$(document).ready(function() {
	$("#Home-Song-Button").click(function(event){
		loadFollowedSongs();
		event.preventDefault();
	});

});


function loadFollowedSongs(){
	$.ajax({
		type : "GET",
		url : "loadFollowedSongs.html",
		success : function(data) {
			$('#centerSideContent').empty();
			addSongsToCenterContent(data);
		}
	});
}