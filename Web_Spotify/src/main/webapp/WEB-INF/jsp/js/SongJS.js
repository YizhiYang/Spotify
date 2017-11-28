$( document ).ready(function() {
		$("#submitSongFormButton").click(function(event) {
			uploadSong();
			event.preventDefault();
		});
});

function uploadSong(){
	var formData = new FormData();
	var $inputs = $('#upload-song-form :input');
    var values = {};
    $inputs.each(function() {
        values[this.name] = $(this).val();
        formData.append(this.name, $(this).val());
    });
    formData.append($('#FormSongFile')[0].name,$('#FormSongFile')[0].files[0]);
    formData.append($('#FormLyricsFile')[0].name,$('#FormLyricsFile')[0].files[0]);
	$.ajax({
		type : "POST",
		enctype: 'multipart/form-data',
		url : "SongFileUpload.html",
		data : formData,
        success: function (data) {
            alert(data)
            $('#upload-song-form').reset();
            $('#uploadSongPopUp').modal('hide');
        },
        cache: false,
        contentType: false,
        processData: false
	});
}

function sendRemoveSongsRequestToServer(songId){
	$.ajax({
		type : "POST",
		url : "removeSong/" + songId + ".html",
		success : function(data) {
			getBrowsePageContent();
		}
	});
}