function addToFollowedSongs(songId, index){
	$.ajax({
		type : "POST",
		url : "addToFollowedSongs/" + songId + ".html",
        success: function (data) {
        	//RELOAD THE DATA OF FOLLOWED SONGS STORED ON CLIENT SIDE
        	reloadFollowedSongs();
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
        	reloadFollowedSongs();
        	$(".SongPageUnfollowSong").eq(index).hide();
        	$(".SongPageFollowSong").eq(index).show();
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
        	reloadFollowedAlbums();
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
        	reloadFollowedAlbums();
        	$(".AlbumPageUnfollowAlbum").eq(index).hide();
        	$(".AlbumPageFollowAlbum").eq(index).show();
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
        	reloadFollowedArtists();
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
        	reloadFollowedArtists();
        	$(".ArtistPageUnfollowArtist").eq(index).hide();
        	$(".ArtistPageFollowArtist").eq(index).show();
        	$("#puopUpMessage").html("Unfollow Success.");
        	$("#successPopUp").modal("show");
        }
	});
}