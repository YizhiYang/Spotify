/**
 * 
 */

$( document ).ready(function() {
		$("#submitAddNewPlaylistButton").click(function(event) {
			addNewPlaylist();
			event.preventDefault();
		});
		
		//GET ALL PLAYLISTS THAT BELONG TO THIS USER
		getUserPlaylists();
		
		$("#submitChangePlaylistNameButton").click(function(event) {
			changePlaylistName();
			event.preventDefault();
		});
});

function addNewPlaylist(){
	var newPlayListData = $('#add-new-playlist-form').serialize();
	
	$.ajax({
		type : "POST",
		url : "addNewPlaylist.html",
		data : newPlayListData,
        success: function (data) {
            $('#addNewPlaylistPopUp').modal('hide');
            getUserPlaylists();
        	$("#puopUpMessage").html("New Playlist Added.");
        	$("#successPopUp").modal("show");
        }
	});
}


function getUserPlaylists(){
	$.ajax({
		type : "GET",
		url : "getUserPlaylist.html",
		success : function(data) {
			$("#My-Playlist-List-Wrapper").empty();
			addToPlaylistList(data);
		}
	});
}



function addToPlaylistList(jsonData){
	var jsonArray = jQuery.parseJSON(jsonData);
	
	var contentToBeAdded = '';
	
	for(i=0; i<jsonArray.length; i++){
		var playlist = jsonArray[i];
		
		contentToBeAdded += '<li class="userSongWrapperList playlistTab" id="playlist'+ playlist.playlistID+'">'
								+ playlist.playlistName + '</li>';
		
	}
	
	$("#My-Playlist-List-Wrapper").append(contentToBeAdded);
	
	
	//REGISTER FOLLOW SONG CLICK
	$(".playlistTab").each(function(index){
		$(this).click(function(event){
			goToPlaylistSongs(jsonArray[index].playlistID, jsonArray[index].playlistName);
		});
	});
	
}


function addSongToPlayList(playlistId, songId){
	$.ajax({
		type : "POST",
		url : "addSongToPlaylist/" + playlistId + "/" + songId+ ".html",
        success: function (data) {
        	$("#puopUpMessage").html("Song Added");
        	$("#successPopUp").modal("show");
        }
	});
}

function removeSongFromPlaylist(playlistId, playlistName, songId){
	$.ajax({
		type : "POST",
		url : "removeSongFromPlaylist/" + playlistId + "/" + songId+ ".html",
        success: function (data) {
            goToPlaylistSongs(playlistId, playlistName);
        	$("#puopUpMessage").html("Remove Success");
        	$("#successPopUp").modal("show");
        }
	});
}

function goToPlaylistSongs(playlistId, playlistName){
	$.ajax({
		type : "GET",
		url : "getPlaylistSongs/" + playlistId + ".html",
        success: function (data) {
        	$('#centerSideContent').empty();
			addSongsToCenterContent(data);
			//ADD AND REGISTER REMOVE SONG FROM PLAYLIST BUTTON
			$('.songPageListContent').each(function(index){
				$(this).first().append('<i class="material-icons removeSongFromPlaylistButton">remove_circle</i>');
			});
			
			$('.removeSongFromPlaylistButton').each(function(index){
				$(this).click(function(event){
					var songIdString = $('.songPageListContent').eq(index).attr("id");
					removeSongFromPlaylist(playlistId, playlistName, songIdString.substring(4, songIdString.length));
				});
			});
			
			//CHANGE "Songs" TO PLAYLIST NAME,AND REGISTER RENAME AND DELETE FUNCTIONS
			$('.song-table-title').html("");
			$('.song-table-title').append('<div id="playlist-page-name-title">' + playlistName + '</div>');
			$('.song-table-title').append('<i class="material-icons" id="removePlaylistButton">delete</i>');
			$('#playlist-page-name-title').attr("data-toggle", "modal");
			$('#playlist-page-name-title').attr("data-target", "#changePlaylistNamePopUp");
			$('#changePlaylistNameInput').val(playlistName);
			$('#selectedPlaylist').html(playlistId);
			
			$('#removePlaylistButton').click(function(event){
				removePlaylist(playlistId);
			});
        }
	});
}


function changePlaylistName(){
var newPlaylistNameData = $('#change-playlist-name-form').serialize();
	
	$.ajax({
		type : "POST",
		url : "renamePlaylist/" + $('#selectedPlaylist').html() + ".html",
		data : newPlaylistNameData,
		success : function(data) {
			console.log(data);
			$('.song-table-title').html($('#changePlaylistNameInput').val());
			$('#changePlaylistNamePopUp').modal('hide');
			$('#playlist' + $('#selectedPlaylist').html()).html($('#changePlaylistNameInput').val());
		}
	});
}

function removePlaylist(playlistId){
	$.ajax({
		type : "POST",
		url : "deletePlaylist/" + playlistId + ".html",
		success : function(data) {
        	$("#puopUpMessage").html("Remove Success");
        	$("#successPopUp").modal("show");
			window.location.reload();
		}
	});
}



