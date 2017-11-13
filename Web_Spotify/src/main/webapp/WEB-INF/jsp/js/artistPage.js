$(document).ready(function() {
	$("#Home-Artist-Button").click(function(event){
		loadAllArtists();
		event.preventDefault();
	});

});

function loadAllArtists(){
	$.ajax({
		type : "GET",
		url : "getAllArtists.html",
		success : function(data) {
			addAllArtistsToCenterContent(data);
		}
	});
}


function addAllArtistsToCenterContent(jsonData){
	
	console.log(jsonData);
	
	var jsonArray = jQuery.parseJSON(jsonData);
	
	
    $('#centerSideContent').empty();
    $("#centerSideContent").append('<div class=centerSideContentWrapper id=AlbumPageTop>\
                                        <div id="contentTitle">\
                                            <div id="contentTitleWrapper" style="border-bottom:transparent; font-size:45px;">Artist</div>\
                                        </div>\
                                    </div>');
    
    var contentToBeAdded = '<div class=centerSideContentWrapper>';
    for(i=0; i<jsonArray.length; i++){
    	var artist = jsonArray[i];
    	contentToBeAdded += '<div class=ArtistPageContent>\
            					<div class=ArtistContentPicture style="background-image: url(getProfileImageWithUsername/' + artist.userName + '.html);"></div>\
            						<div class=ArtistContentDescription>\
    									<div class=ArtistContentName>'+ artist.ArtistName + '</div>\
               						<div class=ArtistContentListLength>' + artist.totalNumberOfSongs + ' Songs</div>\
            					</div>\
    						</div>'
    }
    
    contentToBeAdded+='</div>';
    $("#centerSideContent").append(contentToBeAdded);
    
    
    
//    $("#centerSideContent").append('<div class=centerSideContentWrapper>\
//                                        <div class=ArtistPageContent>\
//                                            <div class=ArtistContentPicture style="background-image: url(./images/artistCover1.jpg);"></div>\
//                                            <div class=ArtistContentDescription>\
//                                                <div class=ArtistContentName>Disturbed</div>\
//                                               <div class=ArtistContentListLength>23 Songs</div>\
//                                            </div>\
//                                        </div>\
//                                        <div class=ArtistPageContent>\
//                                            <div class=ArtistContentPicture style="background-image: url(./images/artistCover2.jpg);"></div>\
//                                            <div class=ArtistContentDescription>\
//                                                <div class=ArtistContentName>Bullet For My Valentine</div>\
//                                               <div class=ArtistContentListLength>23 Songs</div>\
//                                            </div>\
//                                        </div>\
//                                        <div class=ArtistPageContent>\
//                                            <div class=ArtistContentPicture style="background-image: url(./images/artistCover3.jpg);"></div>\
//                                            <div class=ArtistContentDescription>\
//                                                <div class=ArtistContentName>Five Finger Death Punch</div>\
//                                               <div class=ArtistContentListLength>23 Songs</div>\
//                                            </div>\
//                                        </div>\
//                                        <div class=ArtistPageContent>\
//                                            <div class=ArtistContentPicture style="background-image: url(./images/artistCover4.jpg);"></div>\
//                                            <div class=ArtistContentDescription>\
//                                                <div class=ArtistContentName>Green Day</div>\
//                                               <div class=ArtistContentListLength>23 Songs</div>\
//                                            </div>\
//                                        </div>\
//                                    </div>');
    

}