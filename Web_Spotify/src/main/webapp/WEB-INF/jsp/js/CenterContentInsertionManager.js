/**
 * 
 */

function addSongsToCenterContent(jsonData){
    $('#centerSideContent').append('<div class=centerSideContentWrapper id=PlayListPrototypeTop>\
                                            <div id="contentTitle">\
                                                <div id="contentTitleWrapper" class="song-table-title" style="border-bottom:transparent; font-size:45px;">Songs</div>\
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
    var jsonUserFollowedSongs = jQuery.parseJSON(userFollowedSongs);
    
    for(var i=0; i<jsonArray.length; i++){
    	var song = jsonArray[i];
    	var contentToBeAdded = '<tr class="songPageListContent" id="song' + song.songId + '">\
			<td><i class="material-icons song-content-play-button">play_circle_outline</i>';
    	var isSongFollowed = false;
    	for(j=0; j<jsonUserFollowedSongs.length; j++){
    		if(jsonUserFollowedSongs[j].songId == song.songId){
    			isSongFollowed = true;
    		}
    	}
    	
    	if(isSongFollowed){
    		contentToBeAdded +=	'<i class="material-icons SongPageUnfollowSong">check_circle</i>';
        	contentToBeAdded +=	'<i class="material-icons SongPageFollowSong" style="display:none;">radio_button_unchecked</i>';
    	}else{
    		contentToBeAdded +=	'<i class="material-icons SongPageUnfollowSong" style="display:none;">check_circle</i>';
        	contentToBeAdded +=	'<i class="material-icons SongPageFollowSong">radio_button_unchecked</i>';
    	}
    	contentToBeAdded += '<div class="dropdown" style="position:absolute;display:inline-block;">\
    		  <button class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color:#000;">\
    		    Add\
    		  </button>\
    		  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">';
    		  
    	$(".playlistTab").each(function(index){
    			contentToBeAdded += '<div class="dropdown-item add-to-playlist-item" id="' + $(this).attr('id') + ',song' + song.songId
    			+ '" style="color:#000;">' + $(this).html() + '</div>';
    	});
    		    
    	contentToBeAdded += '</div>\
            			</div>'
    	contentToBeAdded += '</td>';
    	contentToBeAdded += '<td>' + song.songName + '</td>';
    	
    	var artistNames = song.artistNames;
    	contentToBeAdded += '<td>';
    	for(j=0; j<artistNames.length; j++){
    		contentToBeAdded += artistNames[j] + ' ';
    	}
    	contentToBeAdded += '</td>';
    	
    	contentToBeAdded += '<td>' + song.albumName + '</td>';
    	
    	contentToBeAdded += '<td>' + '</td>';
    	
    	contentToBeAdded += '<td>' + song.duration;
    	
    	contentToBeAdded += '<i class="material-icons adminDeleteSongButton" style="float:right;" id="song' + song.songId + '">delete</i>';
    	
    	contentToBeAdded += '</td>'
    	
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
			addToFollowedSongs(jsonArray[index].songId, index);
		});
	});
	
	//REGISTER UNFOLLOW SONG CLICK
	$(".SongPageUnfollowSong").each(function(index){
		$(this).click(function(event){
			removeFromFollowedSongs(jsonArray[index].songId, index);
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
	
	//REGISTER UNFOLLOW SONG CLICK
	$(".adminDeleteSongButton").each(function(index){
		$(this).click(function(event){
			var songIdStr = $(this).attr("id");
			sendRemoveSongsRequestToServer(songIdStr.substring(4, songIdStr.length));
		});
	});
}


function addAlbumsToCenterContent(jsonData){
	
	var jsonArray = jQuery.parseJSON(jsonData);
	var jsonUserFollowedAlbums = jQuery.parseJSON(userFollowedAlbums);
	
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
    	
    	var isAlbumFollowed = false;
    	for(j=0; j<jsonUserFollowedAlbums.length; j++){
    		if(jsonUserFollowedAlbums[j].albumId == album.albumId){
    			isAlbumFollowed = true;
    		}
    	}
    	
    	if(isAlbumFollowed){
    		contentToBeAdded +=	'<i class="material-icons AlbumPageUnfollowAlbum">check_circle</i>';
        	contentToBeAdded +=	'<i class="material-icons AlbumPageFollowAlbum" style="display:none;">radio_button_unchecked</i>';
    	}else{
    		contentToBeAdded +=	'<i class="material-icons AlbumPageUnfollowAlbum" style="display:none;">check_circle</i>';
        	contentToBeAdded +=	'<i class="material-icons AlbumPageFollowAlbum">radio_button_unchecked</i>';
    	}
    	
    	contentToBeAdded +='<i class="material-icons removeAlbumButton">delete</i>\
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
			addToFollowedAlbums(jsonArray[index].albumId, index);
		});
	});
    
    $(".AlbumPageUnfollowAlbum").each(function(index){
		$(this).click(function(event){
			removeFromFollowedAlbums(jsonArray[index].albumId, index);
		});
	});
    
    $(".removeAlbumButton").each(function(index){
		$(this).click(function(event){
			removeAlbum(jsonArray[index].albumId);
		});
	});
}



function addArtistsToCenterContent(jsonData){
	var jsonArray = jQuery.parseJSON(jsonData);
	var jsonUserFollowedArtists = jQuery.parseJSON(userFollowedArtists);
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
            					</div>'
               						
        var isArtistFollowed = false;
    	for(j=0; j<jsonUserFollowedArtists.length; j++){
    		if(jsonUserFollowedArtists[j].artistID == artist.artistID){
    			isArtistFollowed = true;
    		}
    	}
    	
    	if(isArtistFollowed){
    		contentToBeAdded +=	'<i class="material-icons ArtistPageUnfollowArtist">check_circle</i>';
        	contentToBeAdded +=	'<i class="material-icons ArtistPageFollowArtist" style="display:none;">radio_button_unchecked</i>';
    	}else{
    		contentToBeAdded +=	'<i class="material-icons ArtistPageUnfollowArtist" style="display:none;">check_circle</i>';
        	contentToBeAdded +=	'<i class="material-icons ArtistPageFollowArtist">radio_button_unchecked</i>';
    	}
    	contentToBeAdded +=	'<i class="material-icons removeArtistButton">delete</i>\
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
			addToFollowedArtists(jsonArray[index].artistID, index);
		});
	});
    
    $(".ArtistPageUnfollowArtist").each(function(index){
		$(this).click(function(event){
			removeFromFollowedArtists(jsonArray[index].artistID, index);
		});
	});
    
    $(".removeArtistButton").each(function(index){
		$(this).click(function(event){
			removeArtistButton(jsonArray[index].artistID);
		});
	});

}