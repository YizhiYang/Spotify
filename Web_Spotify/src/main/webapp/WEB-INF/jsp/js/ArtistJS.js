/**
 * 
 */
$( document ).ready(function() {
		$("#submitMakeUserArtistButton").click(function(event) {
			makeUserArtist();
			event.preventDefault();

		});
	
});


function makeUserArtist(){
	var UserArtistData = $('#make-user-artist-form').serialize();
	
	console.log(UserArtistData);
	
	$.ajax({
		type : "POST",
		url : "MakeUserArist.html",
		data : UserArtistData,
        success: function (data) {
            alert(data)
            $('#make-user-artist-form').reset();
            $('#makeUserArtistPopUp').modal('hide');
        },
	});
}