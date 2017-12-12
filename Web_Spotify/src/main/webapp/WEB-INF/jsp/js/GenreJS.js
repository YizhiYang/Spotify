$( document ).ready(function() {
	$("#genre-button").click(function(event){
		$("#centerSideContent").empty();
		addGenreBoxesToCenter();
	});
	
});


function getTopSongsOfGenre(genre){
	$.ajax({
		type : "GET",
		url : "getSongsOfGenre/" + genre + ".html",
        success: function (data) {
        	$('#centerSideContent').empty();
        	addSongsToCenterContent(data);
        	$('.song-table-title').html("Top 20 Songs of: " + genre);
        	$('.song-table-title').parent().append('<button id="playGenreButton" class="btn btn-success" style="margin: 5px;">Play Songs In This Genre</button>');
        	$("#playGenreButton").click(function(event){
        		player.jPlayer("setMedia", myPlaylist[0]);
        		currentPlayingIndex = 0;
        		player.jPlayer("play");
        	});
        }
	});
}