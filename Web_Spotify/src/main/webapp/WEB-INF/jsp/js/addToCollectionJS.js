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

function addToFollowedAlbums(albumId){
	$.ajax({
		type : "POST",
		url : "addToFollowedAlbums/" + albumId + ".html",
        success: function (data) {
            alert(data)
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