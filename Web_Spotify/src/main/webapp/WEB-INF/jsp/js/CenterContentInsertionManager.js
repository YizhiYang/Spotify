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
    $('#centerSideContent').append('</table>\
	</div>');
                                    
    var jsonArray = jQuery.parseJSON(jsonData);
    
    for(var i=0; i<jsonArray.length; i++){
    	var song = jsonArray[i];
    	var contentToBeAdded = '<tr id=songPageListContent>\
			<td><i class="material-icons song-content-play-button">play_circle_outline</i>\
				<i class="material-icons SongPageFollowSong">add</i>\
    		<div class="dropdown" style="position:absolute;display:inline-block;">\
    		  <button class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color:#000;">\
    		    Add\
    		  </button>\
    		  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">';
    		  
    	$(".playlistTab").each(function(index){
    			contentToBeAdded += '<div class="dropdown-item add-to-playlist-item" id="' + $(this).attr('id') + ',song' + song.songId
    			+ '" style="color:#000;">' + $(this).html() + '</div>';
    	});
    		    
    	contentToBeAdded += '</div>\
            			</div>\
            			</td>';
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
    	
    	$('#PlayListPrototypeTable').append(contentToBeAdded);
    	
    }
    
    
    var songList =[];
    
    for(var i=0; i<jsonArray.length; i++){
    	console.log("requestSongFile/" + jsonArray[i].songId +".html");
    	var song = {title: jsonArray[i].song_name,
    			mp3:"requestSongFile/" + jsonArray[i].songId +".html",
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
	
    //REGISTER PLAY BUTTON CLICK
	$(".song-content-play-button").each(function(index){
		$(this).click(function(event){
			myPlaylist.play(index);
		});
	});
	
	//REGISTER FOLLOW SONG CLICK
	$(".SongPageFollowSong").each(function(index){
		$(this).click(function(event){
			addToFollowedSongs(jsonArray[index].songId);
		});
	});
	
	//REGISTER FOLLOW SONG CLICK
	$(".SongPageFollowSong").each(function(index){
		$(this).click(function(event){
			addToFollowedSongs(jsonArray[index].songId);
		});
	});
	
	//REGISTER ADD SONG TO PLAYLIST CLICK
	$(".add-to-playlist-item").each(function(index){
		$(this).click(function(event){
			var ids = $(this).attr('id').split(",");
			var playlistId = ids[0].substring(8,ids[0].length);
			var songId = ids[1].substring(4,ids[1].length);
			addSongToPlayList(playlistId, songId);
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
    	contentToBeAdded += '<div class="AlbumPageContent">\
                							<div class=AlbumContentPicture id="album' + album.albumId 
                								+ '" style="background-image: url(requestAlbumImage/' + album.albumId + '.html);"></div>\
                								<div class=AlbumContentDescription>\
                    							<div class=AlbumContentName>' + album.albumName
                    							+ '</div>';
    	
//    	$("#centerSideContent").append('<div class="AlbumPageContent">\
//				<img src="requestAlbumImage/' + album.albumId + '.html">\
//					<div class=AlbumContentDescription>\
//					<div class=AlbumContentName>' + album.albumName + '</div>\
//<div class=AlbumContentArtist>');
    	
    	var albumArtistNames = album.artistNames;
    	
    	for(j = 0; j<albumArtistNames.length; j++){
    		contentToBeAdded += '<div class=AlbumContentArtist>'+albumArtistNames[j]+'</div>';
    	}
    	
    	contentToBeAdded +='<i class="material-icons AlbumPageFollowAlbum">add</i>\
    						</div>\
    						</div>';
    	
    }
    
    contentToBeAdded+='</div>';
    $("#centerSideContent").append(contentToBeAdded);
    
    $(".AlbumContentPicture").each(function(index) {
        $(this).on("click", function(event){
        	var albumID = $(this).attr("id");
        	goToAlbumSongs(albumID.substring(5, albumID.length));
        });
    });
    
    $(".AlbumPageFollowAlbum").each(function(index){
		$(this).click(function(event){
			addToFollowedAlbums(jsonArray[index].albumId);
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
    	contentToBeAdded += '<div class=ArtistPageContent>\
            					<div class=ArtistContentPicture id="artist' + artist.artistID
            						+ '" style="background-image: url(getProfileImageWithUsername/' + artist.userName + '.html);"></div>\
            						<div class=ArtistContentDescription>\
    									<div class=ArtistContentName>'+ artist.artistName + '</div>\
               						<div class=ArtistContentListLength>' + artist.totalNumberOfSongs + ' Songs</div>\
            					</div>\
            					<i class="material-icons ArtistPageFollowArtist">add</i>\
    						</div>'
    }
    
    contentToBeAdded+='</div>';
    $("#centerSideContent").append(contentToBeAdded);
    
    $(".ArtistContentPicture").each(function(index) {
        $(this).on("click", function(event){
        	var artistID = $(this).attr("id");
        	goToArtistAlbums(artistID.substring(6, artistID.length));
        });
    });
    
    $(".ArtistPageFollowArtist").each(function(index){
		$(this).click(function(event){
			addToFollowedArtists(jsonArray[index].artistID);
		});
	});

}