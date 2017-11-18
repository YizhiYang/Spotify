/**
 * 
 */
function addToFollowedSongs(songId){
	$.ajax({
		type : "POST",
		url : "addToFollowedSongs/" + songId + ".html",
        success: function (data) {
            alert(data)
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