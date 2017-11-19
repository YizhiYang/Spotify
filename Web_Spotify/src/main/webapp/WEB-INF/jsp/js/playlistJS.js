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
			goToPlaylistSongs(jsonArray[index].playlistID);
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

function goToPlaylistSongs(playlistId){
	$.ajax({
		type : "GET",
		url : "getPlaylistSongs.html",
        success: function (data) {
        	$('#centerSideContent').empty();
			addSongsToCenterContent(data);
        }
	});
}



