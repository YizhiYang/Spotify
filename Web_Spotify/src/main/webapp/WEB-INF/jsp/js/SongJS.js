//FOR DETERMINING SONG DURATION
var objectUrl;
$( document ).ready(function() {
	
		$("#submitSongFormButton").click(function(event) {
			uploadSong();
			event.preventDefault();
		});
		
		$("#upload-temp-audio").on("canplaythrough", function(e){
		    var time = Math.floor($(this).get(0).duration);
		    var minute = Math.floor(time / 60);
		    var second = time % 60;
		    var timeString = "";
		    if(minute >= 10){
		    	timeString += minute;
		    }else{
		    	timeString += "0" + minute;
		    }
		    timeString +=":";
		    if(second >= 10){
		    	timeString += second;
		    }else{
		    	timeString += "0"+second;
		    }
		    $("#formGroupDurationInput").val(timeString);
		    URL.revokeObjectURL(objectUrl);
		});

		$("#FormSongFile").change(function(e){
		    var file = e.currentTarget.files[0]; 
		    objectUrl = URL.createObjectURL(file);
		    $("#upload-temp-audio").prop("src", objectUrl);
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
            $('#uploadSongPopUp').modal('hide');
            refreshCenterContent();
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

function sendApproveSongsRequestToServer(songId){
	$.ajax({
		type : "POST",
		url : "approveSong/" + songId + ".html",
		success : function(data) {
			refreshCenterContent();
		}
	});
}