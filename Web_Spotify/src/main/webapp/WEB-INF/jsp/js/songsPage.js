$(document).ready(function() {
	$("#Home-Song-Button").click(function(event){
		loadFollowedSongs();
		event.preventDefault();
	});
	
	$("#Home-History-Button").click(function(event){
		loadPlayHistory();
		event.preventDefault();
	});
	
	$("#Admin-PendingSongs-Button").click(function(event) {
		loadPendingSongs();
		event.preventDefault();
	});

});

function loadFollowedSongs(){
	//DATA IS ALREADY LOADED, JUST RENDER
	$('#centerSideContent').empty();
	addSongsToCenterContent(userFollowedSongs);
	lastAjaxCallToRenderToCenter = "loadFollowedSongs()";
}

function loadPendingSongs(){
	$.ajax({
		type : "GET",
		url : "getAllSongsPendingApproval.html",
		success : function(data) {
			$('#centerSideContent').empty();
			addSongsToCenterContent(data);
			//ADD APPROVE BUTTON
			addApproveButtonToSongs();
			$('.song-table-title').html("Songs Appending Approval");
			lastAjaxCallToRenderToCenter = "loadPendingSongs()";
		}
	});
}

function addApproveButtonToSongs(){
	$(".adminDeleteSongButton").each(function(index){
		var approveButton = '<i class="material-icons adminApproveSongButton" style="float:right;" id="' + $(this).attr('id')
		+ '">done</i>';
		$(this).parent().append(approveButton);
	});
	
	$(".adminApproveSongButton").each(function(index){
		$(this).click(function(event){
			var songIdStr = $(this).attr("id");
			sendApproveSongsRequestToServer(songIdStr.substring(4, songIdStr.length));
		});
	});
}


function loadPlayHistory(){
	$.ajax({
		type : "GET",
		url : "getPlayHistorySongs.html",
		success : function(data) {
			$('#centerSideContent').empty();
			addSongsToCenterContent(data);
			
			$('.song-table-title').html("Play History");
			lastAjaxCallToRenderToCenter = "loadPlayHistory()";
		}
	});
}
