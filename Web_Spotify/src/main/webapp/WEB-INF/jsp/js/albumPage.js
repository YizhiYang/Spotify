$(document).ready(function() {
	$("#Home-Album-Button").click(function(event){
		loadAllAlbums();
		event.preventDefault();
	});

});


function loadAllAlbums(){
	$.ajax({
		type : "GET",
		url : "getAllAlbums.html",
		success : function(data) {
			addAllAlbumsToCenterContent(data);
		}
	});
}

function addAllAlbumsToCenterContent(jsonData){
	
	var jsonArray = jQuery.parseJSON(jsonData);
	
	
    $('#centerSideContent').empty();
    $("#centerSideContent").append('<div class=centerSideContentWrapper id=AlbumPageTop>\
                                        <div id="contentTitle">\
                                            <div id="contentTitleWrapper" style="border-bottom:transparent; font-size:45px;">Album</div>\
                                        </div>\
                                   </div>');
    
    var contentToBeAdded = '<div class=centerSideContentWrapper>';
    for(i = 0; i<jsonArray.length; i++){
    	var album = jsonArray[i];
    	contentToBeAdded += '<div class="AlbumPageContent" id="album' + album.albumId + '">\
                							<div class=AlbumContentPicture style="background-image: url(requestAlbumImage/' + album.albumId + '.html);"></div>\
                								<div class=AlbumContentDescription>\
                    							<div class=AlbumContentName>' + album.albumName + '</div>';
    	
//    	$("#centerSideContent").append('<div class="AlbumPageContent">\
//				<img src="requestAlbumImage/' + album.albumId + '.html">\
//					<div class=AlbumContentDescription>\
//					<div class=AlbumContentName>' + album.albumName + '</div>\
//<div class=AlbumContentArtist>');
    	
    	var albumArtistNames = album.artistNames;
    	
    	for(j = 0; j<albumArtistNames.length; j++){
    		contentToBeAdded += '<div class=AlbumContentArtist>'+albumArtistNames[j]+'</div>';
    	}
    	
    	contentToBeAdded +='</div>\
    						</div>';
    	
    }
    
    contentToBeAdded+='</div>';
    $("#centerSideContent").append(contentToBeAdded);
    
    $(".AlbumPageContent").each(function(index) {
        $(this).on("click", function(event){
        	var albumID = $(this).attr("id");
        	goToAlbumSongs(albumID.substring(5, albumID.length));
        });
    });
}

function goToAlbumSongs(albumID){
	$.ajax({
		type : "GET",
		url : "getAllSongsInAlbum/" + albumID + ".html",
		success : function(data) {
			addAllSongsToCenterContent(data);
		}
	});
}