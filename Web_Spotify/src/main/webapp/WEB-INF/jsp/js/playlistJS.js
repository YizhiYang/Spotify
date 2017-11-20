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
            alert(data)
            $('#addNewPlaylistPopUp').modal('hide');
            getUserPlaylists();
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
            alert(data)
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
			$('.song-table-title').html("");
			$('.song-table-title').append('<div id="playlist-page-name-title">' + playlistName + '</div>');
			$('.song-table-title').append('<i class="material-icons" id="removePlaylistButton">delete</i>');
			$('#playlist-page-name-title').attr("data-toggle", "modal");
			$('#playlist-page-name-title').attr("data-target", "#changePlaylistNamePopUp");
			$('#changePlaylistNameInput').val(playlistName);
			console.log($('#selectedPlaylist').html());
			$('#selectedPlaylist').html(playlistId);
			console.log($('#selectedPlaylist').html());
			
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
			window.location.reload();
		}
	});
}



