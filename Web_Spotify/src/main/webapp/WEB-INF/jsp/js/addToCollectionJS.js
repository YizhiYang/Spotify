/**
 * 
 */
function addToFollowedSongs(songId, index){
	$.ajax({
		type : "POST",
		url : "addToFollowedSongs/" + songId + ".html",
        success: function (data) {
        	reloadFollowedSongs();
        	$(".SongPageFollowSong").eq(index).hide();
        	$(".SongPageUnfollowSong").eq(index).show();
        },
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
        },
	});
}

function addToFollowedAlbums(albumId, index){
	$.ajax({
		type : "POST",
		url : "addToFollowedAlbums/" + albumId + ".html",
        success: function (data) {
        	reloadFollowedAlbums();
        	$(".AlbumPageFollowAlbum").eq(index).hide();
        	$(".AlbumPageUnfollowAlbum").eq(index).show();
        },
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
        },
	});
}

function addToFollowedArtists(artistId){
	$.ajax({
		type : "POST",
		url : "addToFollowedArtists/" + artistId + ".html",
        success: function (data) {
            alert(data)
        },
	});
}