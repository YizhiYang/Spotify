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
		}
	});
}

function addArtistTitleToArtistPage(artistID){
	$('.album-table-title').html(selectedArtistName);
	if(userType == "ADMIN" || loggedInArtistId == artistID){
		$('.album-table-title').parent().append('<button id="addAlbumToArtistButton" class="btn btn-warning">Add Album To Artist</button>');
		
		$('#addAlbumToArtistButton').click(function(event){
			$('#createAlbumArtistIDInput').prop('disabled', true);
			$('#createAlbumArtistIDInput').val(artistID);
			$('#createAlbumPopUp').modal('show');
		});
		return;
	}
}

