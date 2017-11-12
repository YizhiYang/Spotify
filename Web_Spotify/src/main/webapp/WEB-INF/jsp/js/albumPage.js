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
	
	
	console.log(jsonData);
	
	var jsonArray = jQuery.parseJSON(jsonData);
	
	
    $('#centerSideContent').empty();
    $("#centerSideContent").append('<div class=centerSideContentWrapper id=AlbumPageTop>\
                                        <div id="contentTitle">\
                                            <div id="contentTitleWrapper" style="border-bottom:transparent; font-size:45px;">Album</div>\
                                        </div>\
                                   </div>');
    
    var contentTobeAdded = '<div class=centerSideContentWrapper>';
    for(i = 0; i<jsonArray.length; i++){
    	var album = jsonArray[i];
    	contentTobeAdded += '<div class="AlbumPageContent">\
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
    		contentTobeAdded += '<div class=AlbumContentArtist>'+albumArtistNames[j]+'</div>';
    	}
    	
    	contentTobeAdded +='</div>\
    						</div>\
            				</div>';
    	
    }
    $("#centerSideContent").append(contentTobeAdded);
    
    
    
    
    /*
                                        <div class="AlbumPageContent">\
                                            <div class=AlbumContentPicture style="background-image: url(./images/albumCover1.jpg);"></div>\
                                            <div class=AlbumContentDescription>\
                                                <div class=AlbumContentName>Immortalized</div>\
                                                <div class=AlbumContentArtist>Disturbed</div>\
                                            </div>\
                                        </div>\
                                        <div class="AlbumPageContent">\
                                            <div class=AlbumContentPicture style="background-image: url(./images/albumCover2.jpg);"></div>\
                                            <div class=AlbumContentDescription>\
                                                <div class=AlbumContentName>Hand Of Blood</div>\
                                                <div class=AlbumContentArtist>Bullet for my valentine</div>\
                                            </div>\
                                        </div>\
                                        <div class="AlbumPageContent">\
                                            <div class=AlbumContentPicture style="background-image: url(./images/albumCover3.jpg);"></div>\
                                            <div class=AlbumContentDescription>\
                                                <div class=AlbumContentName>Wrong Side of Heaven</div>\
                                                <div class=AlbumContentArtist>Five Finger Death Punch</div>\
                                            </div>\
                                        </div>\
                                        <div class="AlbumPageContent">\
                                            <div class=AlbumContentPicture style="background-image: url(./images/albumCover4.jpg);"></div>\
                                            <div class=AlbumContentDescription>\
                                                <div class=AlbumContentName>American Idiot</div>\
                                                <div class=AlbumContentArtist>Green Day</div>\
                                            </div>\
                                        </div>\
                                    </div>');
                                    */
}