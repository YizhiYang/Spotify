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
			addArtistTitleToArtistPage(artistID);
			
			lastAjaxCallToRenderToCenter = "goToArtistAlbums(" + artistID + ")";
		}
	});
}


function addArtistTitleToArtistPage(artistID){
	$('.album-table-title').html(selectedArtistName);
	if(userType == "ADMIN" || loggedInArtistId == artistID){
		$('.album-table-title').parent().append('<div id="addAlbumToArtistButton">Add Album To Artist</div>');
		
		$('#addAlbumToArtistButton').click(function(event){
			$('#createAlbumArtistIDInput').prop('disabled', true);
			$('#createAlbumArtistIDInput').val(artistID);
			$('#createAlbumPopUp').modal('show');
		});
		return;
	}
}