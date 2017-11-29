function addToFollowedSongs(songId, index){
	$.ajax({
		type : "POST",
		url : "addToFollowedSongs/" + songId + ".html",
        success: function (data) {
        	//RELOAD THE DATA OF FOLLOWED SONGS STORED ON CLIENT SIDE
        	reloadFollowedSongs(false);
        	//SWITCH THE BUTTON FOR FOLLOW/UNFOLLOW
        	$(".SongPageFollowSong").eq(index).hide();
        	$(".SongPageUnfollowSong").eq(index).show();
        	$("#puopUpMessage").html("Follow Success.");
        	$("#successPopUp").modal("show");
        }
	});
}

function removeFromFollowedSongs(songId, index){
	$.ajax({
		type : "POST",
		url : "removeFromFollowedSongs/" + songId + ".html",
        success: function (data) {
        	reloadFollowedSongs(true);
        	$("#puopUpMessage").html("Unfollow Success.");
        	$("#successPopUp").modal("show");
        }
	});
}

function addToFollowedAlbums(albumId, index){
	$.ajax({
		type : "POST",
		url : "addToFollowedAlbums/" + albumId + ".html",
        success: function (data) {
        	//RELOAD THE DATA OF FOLLOWED ALBUMS STORED ON CLIENT SIDE
        	reloadFollowedAlbums(false);
        	//SWITCH THE BUTTON FOR FOLLOW/UNFOLLOW
        	$(".AlbumPageFollowAlbum").eq(index).hide();
        	$(".AlbumPageUnfollowAlbum").eq(index).show();
        	$("#puopUpMessage").html("Follow Success.");
        	$("#successPopUp").modal("show");
        }
	});
}

function removeFromFollowedAlbums(albumId, index){
	$.ajax({
		type : "POST",
		url : "removeFromFollowedAlbums/" + albumId + ".html",
        success: function (data) {
        	reloadFollowedAlbums(true);
        	$("#puopUpMessage").html("Unfollow Success.");
        	$("#successPopUp").modal("show");
        }
	});
}

function addToFollowedArtists(artistId, index){
	$.ajax({
		type : "POST",
		url : "addToFollowedArtists/" + artistId + ".html",
        success: function (data) {
        	//RELOAD THE DATA OF FOLLOWED ARTISTS STORED ON CLIENT SIDE
        	reloadFollowedArtists(false);
        	//SWITCH THE BUTTON FOR FOLLOW/UNFOLLOW
        	$(".ArtistPageFollowArtist").eq(index).hide();
        	$(".ArtistPageUnfollowArtist").eq(index).show();
        	$("#puopUpMessage").html("Follow Success.");
        	$("#successPopUp").modal("show");
        }
	});
}

function removeFromFollowedArtists(artistId, index){
	$.ajax({
		type : "POST",
		url : "removeFromFollowedArtists/" + artistId + ".html",
        success: function (data) {
        	reloadFollowedArtists(true);
        	$("#puopUpMessage").html("Unfollow Success.");
        	$("#successPopUp").modal("show");
        }
	});
}