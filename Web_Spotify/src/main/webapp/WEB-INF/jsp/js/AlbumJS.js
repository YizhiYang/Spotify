$( document ).ready(function() {
	$("#submitCreateAlbumButton").click(function(event) {
		createAlbum();
		event.preventDefault();
	});
});

function createAlbum(){
	var formData = new FormData();
	var $inputs = $('#create-album-form :input');
    var values = {};
    $inputs.each(function() {
        values[this.name] = $(this).val();
        formData.append(this.name, $(this).val());
    });
    formData.append($('#createAlbumImageFile')[0].name,$('#createAlbumImageFile')[0].files[0]);
	$.ajax({
		type : "POST",
		enctype: 'multipart/form-data',
		url : "UploadAlbum.html",
		data : formData,
        success: function (data) {
            $('#createAlbumPopUp').modal('hide');
            refreshCenterContent();
        },
        cache: false,
        contentType: false,
        processData: false
	});
}

function removeAlbum(albumID){
	$.ajax({
		type : "POST",
		url : "removeAlbum/" + albumID + ".html",
        success: function (data) {
	        refreshCenterContent();
        }
	});
}

/*Not Useful Anymore, but keeping for now just in case*/
function removeAlbumFromRender(albumID){
	$(".AlbumContentPicture").each(function(index) {
        var albumIDString = $(this).attr("id");
        if(albumIDString === ("album"+albumID)){
        	$(".AlbumPageContent").eq(index).remove();
        }
    });
}