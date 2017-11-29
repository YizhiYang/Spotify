$( document ).ready(function() {
	$("#submitMakeUserArtistButton").click(function(event) {
		makeUserArtist();
		event.preventDefault();
	});
});

function makeUserArtist(){
	var UserArtistData = $('#make-user-artist-form').serialize();
	$.ajax({
		type : "POST",
		url : "MakeUserArist.html",
		data : UserArtistData,
        success: function (data) {
            $('#makeUserArtistPopUp').modal('hide');
            refreshCenterContent();
        },
	});
}

function removeArtist(artistID){
	$.ajax({
		type : "POST",
		url : "removeArtist/" + artistID + ".html",
        success: function (data) {
        	refreshCenterContent();
        }
	});
}

/*Not useful anymore, keeping for now just in case*/
function removeArtistFromRender(artistID){
	$(".AlbumContentPicture").each(function(index) {
        var artistIDString = $(this).attr("id");
        if(artistIDString === ("artist"+artistID)){
        	$(".AlbumPageContent").eq(index).remove();
        }
    });
}