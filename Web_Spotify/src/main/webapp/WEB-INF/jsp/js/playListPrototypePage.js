

$(document).ready(function() {
	$("#My-PlayList-Button").click(function(event){
		loadAllSongs();
		event.preventDefault();
	});

});

function loadAllSongs(){
	$.ajax({
		type : "GET",
		url : "getAllSongs.html",
		success : function(data) {
			addAllSongsToCenterContent(data);
		}
	});
}