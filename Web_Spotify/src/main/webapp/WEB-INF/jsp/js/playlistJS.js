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
            //POPUP MESSAGE SUCCESS
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
			//SO THAT SONGS IN THE CENTER CAN BE RELOADED TO HAVE THIS PLAYLIST AS AN OPTION TO ADD TO
			refreshCenterContent();
		}
	});
}

//ADD A PLAYLIST
function addToPlaylistList(jsonData){
	var jsonArray = jQuery.parseJSON(jsonData);
	userPlaylists = jsonArray;
	var contentToBeAdded = '';
	for(i=0; i<jsonArray.length; i++){
		var playlist = jsonArray[i];
		//ADD PLAYLIST TO LEFT SIDE MENU BAR
		contentToBeAdded += '<li class="userSongWrapperList playlistTab" id="playlist'+ playlist.playlistID+'">'
								+ playlist.playlistName + '</li>';
	}
	$("#My-Playlist-List-Wrapper").append(contentToBeAdded);
	//REGISTER PLAYLIST CLICK
	$(".playlistTab").each(function(index){
		$(this).click(function(event){
			goToPlaylistSongs(index);
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

function removeSongFromPlaylist(playlistId, songId){
	$.ajax({
		type : "POST",
		url : "removeSongFromPlaylist/" + playlistId + "/" + songId+ ".html",
        success: function (data) {
        	refreshCenterContent();
        	$("#puopUpMessage").html("Remove Success");
        	$("#successPopUp").modal("show");
        }
	});
}

function goToPlaylistSongs(playlistIndex){
	$.ajax({
		type : "GET",
		url : "getPlaylistSongs/" + userPlaylists[playlistIndex].playlistID + ".html",
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
					removeSongFromPlaylist(userPlaylists[playlistIndex].playlistID, songIdString.substring(4, songIdString.length));
				});
			});
			
			//CHANGE "Songs" TO PLAYLIST NAME,AND REGISTER RENAME AND DELETE FUNCTIONS
			$('.song-table-title').html("");
			$('.song-table-title').append('<div id="playlist-page-name-title">' + userPlaylists[playlistIndex].playlistName + '</div>');
			$('.song-table-title').append('<i class="material-icons" id="removePlaylistButton">delete</i>');
			$('#playlist-page-name-title').attr("data-toggle", "modal");
			$('#playlist-page-name-title').attr("data-target", "#changePlaylistNamePopUp");
			$('#changePlaylistNameInput').val(userPlaylists[playlistIndex].playlistName);
			selectedPlaylistId = userPlaylists[playlistIndex].playlistID;
			$('#removePlaylistButton').click(function(event){
				removePlaylist(userPlaylists[playlistIndex].playlistID);
			});
			lastAjaxCallToRenderToCenter = "goToPlaylistSongs(" + playlistIndex + ")";
			console.log(lastAjaxCallToRenderToCenter);
        }
	});
}


function changePlaylistName(){
	var newPlaylistNameData = $('#change-playlist-name-form').serialize();
	$.ajax({
		type : "POST",
		url : "renamePlaylist/" + selectedPlaylistId + ".html",
		data : newPlaylistNameData,
		success : function(data) {
			getUserPlaylists();
			$('#changePlaylistNamePopUp').modal('hide');
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
        	//PLAYLIST NO LONGER EXISTS, NOT REFRESHING A PAGE THAT SHOULDN'T EXIST, GO BACK TO BROWSE
			window.location.reload();
		}
	});
}



function goToFriendPlaylist(playlistId, playlistName){
	$.ajax({
		type : "GET",
		url : "getPlaylistSongs/" + playlistId + ".html",
        success: function (data) {
        	$('#centerSideContent').empty();
			addSongsToCenterContent(data);
			//CHANGE "Songs" TO PLAYLIST NAME
			$('.song-table-title').html("");
			$('.song-table-title').append('<div id="playlist-page-name-title">' + playlistName + '</div>');
			lastAjaxCallToRenderToCenter = "goToFriendPlaylist(" + playlistId + "," +"'" + playlistName + "')";
			console.log(lastAjaxCallToRenderToCenter);
        }
	});
}