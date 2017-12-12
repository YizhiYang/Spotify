$(document).ready(function() {
	$("#Home-Artist-Button").click(function(event){
		loadFollowedArtists();
		event.preventDefault();
	});
});

function loadFollowedArtists(){
//	$.ajax({
//		type : "GET",
//		url : "loadFollowedArtists.html",
//		success : function(data) {
//			$('#centerSideContent').empty();
//			addArtistsToCenterContent(data);
//			lastAjaxCallToRenderToCenter = "loadFollowedArtists()";
//		}
//	});
	
	$('#centerSideContent').empty();
	addArtistsToCenterContent(userFollowedArtists);
	$('.artist-table-title').html("Artists I Follows");
	lastAjaxCallToRenderToCenter = "loadFollowedArtists()";
}

function goToArtistAlbums(artistID){
	$.ajax({
		type : "GET",
		url : "getAllAlbumsInArtist/" + artistID + ".html",
		success : function(data) {
			$('#centerSideContent').empty();
			addAlbumsToCenterContent(data);
			getArtistBio(artistID);
			
			lastAjaxCallToRenderToCenter = "goToArtistAlbums(" + artistID + ")";
		}
	});
}

function getArtistBio(artistID){
	$.ajax({
		type : "GET",
		url : "getArtistBio/" + artistID + ".html",
		success : function(data) {
			$('.album-table-title').parent().append('<div>' + data + '</div>');
			addArtistTitleToArtistPage(artistID);
			getRelatedArtists(artistID);
		}
	});
}

function addArtistTitleToArtistPage(artistID){
	$('.album-table-title').html(selectedArtistName);
	$('#AlbumPageTop').css("background-image", selectedArtistImageURL);
	$('#AlbumPageTop').css("background-size", "cover");
	$('#AlbumPageTop').css("height","500px");
	$('#AlbumPageTop').children().eq(0).css("background-color", "rgba(0, 0, 0, 0.6)");
	$('#AlbumPageTop').children().eq(0).css("height", "500px");
	$('#AlbumPageTop').children().eq(0).css("overflow", "hidden");
	//$('#AlbumPageTop').children().eq(0).css("height", "30px");
	
	$('.album-table-title').parent().append('<div id="artist-top-buttons" style="position: absolute; bottom: 0; padding: 10px;"></div>');

	if(userType == "ADMIN" || loggedInArtistId == artistID){
		console.log("yoyoyo");
		$('#artist-top-buttons').append('<button id="addAlbumToArtistButton" class="btn btn-warning" style="margin: 5px;">Add Album To Artist</button>');
		console.log("button added");
		$('#addAlbumToArtistButton').click(function(event){
			$('#createAlbumArtistIDInput').prop('disabled', true);
			$('#createAlbumArtistIDInput').val(artistID);
			$('#createAlbumPopUp').modal('show');
		});
		return;
	}
}

function getRelatedArtists(artistID){
	$.ajax({
		type : "GET",
		url : "getRelatedArtists/" + artistID + ".html",
		success : function(data) {
			addArtistsToCenterContent(data);
			$('.artist-table-title').html("Related/Similar Artists");
		}
	});
}