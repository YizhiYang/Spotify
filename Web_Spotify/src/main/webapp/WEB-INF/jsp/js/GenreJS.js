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
        }
	});
}