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
        },
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
		
		contentToBeAdded += '<li class="userSongWrapperList" id="playlist'+ playlist.playlistID+'">'
								+ playlist.playlistName + '</li>';
		
	}
	
	$("#My-Playlist-List-Wrapper").append(contentToBeAdded);
	
}



