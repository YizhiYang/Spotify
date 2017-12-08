//PARSE SONG DATA PASSED IN AND APPEND IN CENTER
function addSongsToCenterContent(jsonData){
	//APPEND TITLE
    $('#centerSideContent').append('<div class=centerSideContentWrapper id=PlayListPrototypeTop>\
                                            <div id="contentTitle">\
                                                <div id="contentTitleWrapper" class="song-table-title" style="border-bottom:transparent; font-size:45px;">Songs</div>\
                                            </div>\
                                        </div>\
                                    </div>');
    //APPEND COLUMN TITLES
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
    //GET DATA IN JSON
    var jsonArray = jQuery.parseJSON(jsonData);
    //GET THE USER FOLLOWED SONGS DATA
    var jsonUserFollowedSongs = "";
    if(userFollowedSongs!=null){
    	jsonUserFollowedSongs = jQuery.parseJSON(userFollowedSongs);
    }
    //LOOP TO APPEND EACH SONG
    for(var i=0; i<jsonArray.length; i++){
    	var song = jsonArray[i];
    	//APPEND THE BUTTONS ON THE LEFT SIDE OF SONG ITEM
    	var contentToBeAdded = '<tr class="songPageListContent" id="song' + song.songId + '">\
			<td><i class="material-icons song-content-play-button">play_circle_outline</i>';
    	//RENDER FOLLOW/UNFOLLOW BUTTON BASED ON IF FOLLOWED
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
    	//ADD THE DROPDOWN MENU FOR USER TO SELECT WHICH PLAYLIST TO ADD TO
    	contentToBeAdded += '<div class="dropdown" style="position:absolute;display:inline-block;">\
    		  <i class="material-icons dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">\
    		    add\
    		  </i>\
    		  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">';
    	//FOR EACH PLAYLIST, ADD A MENU ITEM
    	$(".playlistTab").each(function(index){
    			contentToBeAdded += '<div class="dropdown-item add-to-playlist-item" id="' + $(this).attr('id') + ',song' + song.songId
    			+ '" style="color:#000;">' + $(this).html() + '</div>';
    	});
    	contentToBeAdded += '</div>\
            			</div>'
    	contentToBeAdded += '</td>';
    	//ADD SONG NAME
    	contentToBeAdded += '<td>' + song.songName + '</td>';
    	//ADD ARTISTS' NAMES
    	var artistNames = song.artistNames;
    	contentToBeAdded += '<td>';
    	for(j=0; j<artistNames.length; j++){
    		contentToBeAdded += artistNames[j] + ' ';
    	}
    	contentToBeAdded += '</td>';
    	//ADD ALBUM NAME
    	contentToBeAdded += '<td>' + song.albumName + '</td>';
    	//ADD RELEASE DATE
    	contentToBeAdded += '<td>' + '</td>';
    	//ADD DURATION
    	contentToBeAdded += '<td>' + song.duration;
    	//ADD BUTTON FOR ADMIN TO DELETE SONG
    	contentToBeAdded += '<i class="material-icons adminDeleteSongButton" style="float:right;" id="song' + song.songId + '">delete</i>';
    	contentToBeAdded += '</td>'
    	contentToBeAdded += '</tr>';
    	$('#PlayListPrototypeTable').append(contentToBeAdded);
    }
    
    //ADD THE SONGS TO THE MUSIC PLAYER PLAYLIST
    myPlaylist = [];
    for(i=0; i<jsonArray.length; i++){
    	myPlaylist.push({mp3:"requestSongFile/" + jsonArray[i].songId +".html"});
    }
    playlistLength = jsonArray.length;
	
    //REGISTER PLAY BUTTON CLICK
	$(".song-content-play-button").each(function(index){
		$(this).click(function(event){
			player.jPlayer("setMedia", myPlaylist[index]);
			currentPlayingIndex = index;
			player.jPlayer("play");
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
		if(userType == "ADMIN"){
			$(this).click(function(event){
				var songIdStr = $(this).attr("id");
				sendRemoveSongsRequestToServer(songIdStr.substring(4, songIdStr.length));
			});
		}else{
			$(this).hide();
		}
	});
	
}


function addAlbumsToCenterContent(jsonData){
	//GET DATA IN JSON
	var jsonArray = jQuery.parseJSON(jsonData);
	//GET THE USER FOLLOWED ALBUMS DATA
	var jsonUserFollowedAlbums = "";
	if(userFollowedAlbums !=null){
		jsonUserFollowedAlbums = jQuery.parseJSON(userFollowedAlbums);
	}
	//APPEND ALBUM TITLE
    $("#centerSideContent").append('<div class=centerSideContentWrapper id=AlbumPageTop>\
                                        <div id="contentTitle">\
                                            <div id="contentTitleWrapper" class="album-table-title" style="border-bottom:transparent; font-size:45px;">Album</div>\
                                        </div>\
                                   </div>');
    //LOOP TO APPEND EACH ALBUM
    var contentToBeAdded = '<div class=centerSideContentWrapper>';
    for(i = 0; i<jsonArray.length; i++){
    	var album = jsonArray[i];
    	//ADD ALBUM IMAGE AND NAME
    	contentToBeAdded += '<div class="AlbumPageContent">\
                							<div class=AlbumContentPicture id="album' + album.albumId 
                								+ '" style="background-image: url(requestAlbumImage/' + album.albumId + '.html);"></div>\
                								<div class=AlbumContentDescription>\
                    							<div class=AlbumContentName>' + album.albumName
                    							+ '</div>';
    	//LOOP TO APPEND ALL ARTISTS NAMES
    	var albumArtistNames = album.artistNames;
    	for(j = 0; j<albumArtistNames.length; j++){
    		contentToBeAdded += '<div class=AlbumContentArtist>'+albumArtistNames[j]+'</div>';
    	}
    	//RENDER FOLLOW/UNFOLLOW BUTTON BASED ON IF FOLLOWED
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
    	//ADD ADMIN DELETE ALBUM BUTTON
    	contentToBeAdded +='<i class="material-icons removeAlbumButton">delete</i>\
    						</div>\
    						</div>';
    }
    contentToBeAdded+='</div>';
    $("#centerSideContent").append(contentToBeAdded);
    
    //REGISTER ALBUM IMAGE CLICK EVENT
    $(".AlbumContentPicture").each(function(index) {
        $(this).on("click", function(event){
        	var albumID = $(this).attr("id");
        	selectedAlbumName = $(".AlbumContentName").eq(index).html();
        	goToAlbumSongs(albumID.substring(5, albumID.length));
        });
    });
    
    //REGISTER FOLLOW ALBUM BUTTON
    $(".AlbumPageFollowAlbum").each(function(index){
		$(this).click(function(event){
			addToFollowedAlbums(jsonArray[index].albumId, index);
		});
	});
    
    //REGISTER UNFOLLOW ALBUM BUTTON
    $(".AlbumPageUnfollowAlbum").each(function(index){
		$(this).click(function(event){
			removeFromFollowedAlbums(jsonArray[index].albumId, index);
		});
	});
    
    //REGISTER REMOVE ALBUM BUTTON
    $(".removeAlbumButton").each(function(index){
    	if(userType == "ADMIN"){
    		$(this).click(function(event){
    			removeAlbum(jsonArray[index].albumId);
    		});
    	}else{
    		$(this).hide();
    	}
	});
}

function addArtistsToCenterContent(jsonData){
	//GET DATA IN JSON
	var jsonArray = jQuery.parseJSON(jsonData);
	//GET THE USER FOLLOWED ARTISTS DATA
	var jsonUserFollowedArtists = "";
	if(userFollowedArtists !=null){
		jsonUserFollowedArtists = jQuery.parseJSON(userFollowedArtists);
	}
	//ADD ARIST PAGE TITLE
    $("#centerSideContent").append('<div class=centerSideContentWrapper id=AlbumPageTop>\
                                        <div id="contentTitle">\
                                            <div id="contentTitleWrapper" class="artist-table-title" style="border-bottom:transparent; font-size:45px;">Artist</div>\
                                        </div>\
                                    </div>');
    //LOOP TO ADD EACH ARTIST
    var contentToBeAdded = '<div class=centerSideContentWrapper>';
    for(i=0; i<jsonArray.length; i++){
    	var artist = jsonArray[i];
    	//ADD ARTIST NAME, IMAGE, ETC
    	contentToBeAdded += '<div class=ArtistPageContent>\
            					<div class=ArtistContentPicture id="artist' + artist.artistID
            						+ '" style="background-image: url(getProfileImageWithUsername/' + artist.userName + '.html);"></div>\
            						<div class=ArtistContentDescription>\
    									<div class=ArtistContentName>'+ artist.artistName + '</div>\
               						<div class=ArtistContentListLength>' + artist.totalNumberOfSongs + ' Songs</div>\
            					</div>';
        //RENDER FOLLOW/UNFOLLOW BUTTON BASED ON IF FOLLOWED  						
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
    	//ADD ADMIN DELETE ARTIST BUTTON
    	contentToBeAdded +=	'<i class="material-icons removeArtistButton">delete</i>\
    						</div>'
    }
    contentToBeAdded+='</div>';
    $("#centerSideContent").append(contentToBeAdded);
    
    //REGISTER ARTIST IMAGE CLICK EVENT
    $(".ArtistContentPicture").each(function(index) {
        $(this).on("click", function(event){
        	var artistID = $(this).attr("id");
        	goToArtistAlbums(artistID.substring(6, artistID.length));
        });
    });
    
    //REGISTER FOLLOW ARTIST BUTTON
    $(".ArtistPageFollowArtist").each(function(index){
		$(this).click(function(event){
			addToFollowedArtists(jsonArray[index].artistID, index);
		});
	});
    
    //REGISTER UNFOLLOW ARTIST BUTTON
    $(".ArtistPageUnfollowArtist").each(function(index){
		$(this).click(function(event){
			removeFromFollowedArtists(jsonArray[index].artistID, index);
		});
	});
    
    //REGISTER DELETE ARTIST BUTTON
    $(".removeArtistButton").each(function(index){
    	if(userType == "ADMIN"){
    		$(this).click(function(event){
    			removeArtist(jsonArray[index].artistID);
    		});
    	}else{
    		$(this).hide();
    	}
	});
}

function addPlaylistsToCenterContent(jsonData){
	console.log(jsonData);
	var jsonArray = jQuery.parseJSON(jsonData);
	$("#centerSideContent").append('<div class=centerSideContentWrapper id=AlbumPageTop>\
            <div id="contentTitle">\
                <div id="contentTitleWrapper" class="playlist-table-title" style="border-bottom:transparent; font-size:45px;">Playlists</div>\
            </div>\
        </div>');
	var contentToBeAdded = '<div class=centerSideContentWrapper>';
	for(i=0; i<jsonArray.length; i++){
		var pl = jsonArray[i];
		contentToBeAdded += '<div class="centerPlaylistItem" id="playlist'+ pl.playlistID+'">'
		+ pl.playlistName + '</div>';
	}
	contentToBeAdded += '</div>';
	$("#centerSideContent").append(contentToBeAdded);
	
	$(".centerPlaylistItem").each(function(index){
		$(this).click(function(event){
			var playlistIdStr = $(this).attr("id");
			goToFriendPlaylist(playlistIdStr.substr(8, playlistIdStr.length), $(this).html());
		});
	});
}

function addFriendsToCenterContent(jsonData){
	console.log(jsonData);
	var jsonArray = jQuery.parseJSON(jsonData);
	$("#centerSideContent").append('<div class=centerSideContentWrapper id=AlbumPageTop>\
            <div id="contentTitle">\
                <div id="contentTitleWrapper" class="users-table-title" style="border-bottom:transparent; font-size:45px;">User Search Result</div>\
            </div>\
        </div>');
	var contentToBeAdded = '<div class=centerSideContentWrapper>';
	for(i=0; i<jsonArray.length; i++){
		var friend = jsonArray[i];
		contentToBeAdded += '<div class="FSContent centerFriendListItem" id="friend'+ friend.friendId+'">\
        <div class="FSImage"><div class=FSImageInner style="background-image: url(getProfileImageWithUsername/'+ friend.friendUserName +'.html);"></div></div>\
        <div class="FSFriendName centerFriendListFriendName">'+friend.friendUserName+'</div>';
        
        var isfriend = false;
        for(j=0;j<friendIds.length;j++){
        	if(friend.friendId == friendIds[j]){
        		isfriend = true;
        	}
        }
        if(!isfriend){
        	contentToBeAdded += '<div class="addFriendButton">Add Friend</div>';
        }
        contentToBeAdded += '</div>';
	}
	contentToBeAdded += '</div>';
	$("#centerSideContent").append(contentToBeAdded);
	
	$(".centerFriendListItem").each(function(index){
		$(this).click(function(event){
			var friendIdStr= $(this).attr("id");
			goToFriendPage(friendIdStr.substr(6, friendIdStr.length), $(".centerFriendListFriendName").eq(index).html());
		});
	});
	
	$(".addFriendButton").each(function(index){
		$(this).click(function(event){
			addFriend($(".centerFriendListFriendName").eq(index).html());
		});
	});
}