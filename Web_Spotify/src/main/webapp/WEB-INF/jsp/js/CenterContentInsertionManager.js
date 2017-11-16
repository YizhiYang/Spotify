/**
 * 
 */

function addSongsToCenterContent(jsonData){
    $('#centerSideContent').append('<div class=centerSideContentWrapper id=PlayListPrototypeTop>\
                                            <div id="contentTitle">\
                                                <div id="contentTitleWrapper" style="border-bottom:transparent; font-size:45px;">Songs</div>\
                                            </div>\
                                        </div>\
                                    </div>')
                                    
                                    
    $('#centerSideContent').append('<div class=centerSideContentWrapper id=PlayListPrototypeBottom>\
                                        <table id=PlayListPrototypeTable>\
                                            <tr>\
                                                <th></th>\
                                                <th>TITLE</th>\
                                                <th>ARTIST</th>\
                                                <th>ALBUM</th>\
                                                <th>RELEASE</th>\
                                                <th>DURATION</th>\
                                            </tr>');
                                    
    var jsonObj = jQuery.parseJSON(jsonData);
    
    var contentToBeAdded ="";
    
    for(var i=0; i<jsonObj.length; i++){
    	contentToBeAdded += '<tr id=songPageListContent>\
			<td><i class="material-icons song-content-play-button">play_circle_outline</i>\
				<i class="material-icons SongPageAddSong">add</i></td>';
    	
    	var song = jsonObj[i];
    	contentToBeAdded += '<td>' + song.songName + '</td>';
    	
    	var artistNames = song.artistNames;
    	contentToBeAdded += '<td>';
    	for(j=0; j<artistNames.length; j++){
    		contentToBeAdded += artistNames[j] + ' ';
    	}
    	contentToBeAdded += '</td>';
    	
    	contentToBeAdded += '<td>' + song.albumName + '</td>';
    	
    	contentToBeAdded += '<td>' + '</td>';
    	
    	contentToBeAdded += '<td>' + song.duration +'</td>'
    	
    	contentToBeAdded += '</tr>';
    	
    }
    
    $('#centerSideContent').append(contentToBeAdded);
    $('#centerSideContent').append('</table>\
		</div>');
    
    var songList =[];
    
    for(var i=0; i<jsonObj.length; i++){
    	console.log("requestSongFile/" + jsonObj[i].songId +".html");
    	var song = {title: jsonObj[i].song_name,
    			mp3:"requestSongFile/" + jsonObj[i].songId +".html",
				artist:""}
    	songList.push(song);
    }
    
    
    
    myPlaylist = new jPlayerPlaylist({
		jPlayer: "#jquery_jplayer_1",
		cssSelectorAncestor: "#jp_container_1"
	}, 
	songList
	, {
		supplied: "mp3",
		solution: "flash, html",
		wmode: "window",
		useStateClassSkin: true,
		autoBlur: false,
		smoothPlayBar: true,
		keyEnabled: true
	});
	
	$(".song-content-play-button").each(function(index){
		$(this).click(function(event){
			myPlaylist.play(index);
		});
	});

}


function addAlbumsToCenterContent(jsonData){
	
	var jsonArray = jQuery.parseJSON(jsonData);
	
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



function addArtistsToCenterContent(jsonData){
	
	console.log(jsonData);
	
	var jsonArray = jQuery.parseJSON(jsonData);
    $("#centerSideContent").append('<div class=centerSideContentWrapper id=AlbumPageTop>\
                                        <div id="contentTitle">\
                                            <div id="contentTitleWrapper" style="border-bottom:transparent; font-size:45px;">Artist</div>\
                                        </div>\
                                    </div>');
    
    var contentToBeAdded = '<div class=centerSideContentWrapper>';
    for(i=0; i<jsonArray.length; i++){
    	var artist = jsonArray[i];
    	contentToBeAdded += '<div class=ArtistPageContent id="artist' + artist.artistID + '">\
            					<div class=ArtistContentPicture style="background-image: url(getProfileImageWithUsername/' + artist.userName + '.html);"></div>\
            						<div class=ArtistContentDescription>\
    									<div class=ArtistContentName>'+ artist.artistName + '</div>\
               						<div class=ArtistContentListLength>' + artist.totalNumberOfSongs + ' Songs</div>\
            					</div>\
    						</div>'
    }
    
    contentToBeAdded+='</div>';
    $("#centerSideContent").append(contentToBeAdded);
    
    $(".ArtistPageContent").each(function(index) {
        $(this).on("click", function(event){
        	var artistID = $(this).attr("id");
        	goToArtistAlbums(artistID.substring(6, artistID.length));
        });
    });

}