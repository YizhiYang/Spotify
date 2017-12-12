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
                                                <th><div style="margin: 15px;">TITLE</div></th>\
                                                <th><div style="margin: 15px;">ARTIST</div></th>\
                                                <th><div style="margin: 15px;">ALBUM</div></th>\
                                                <th><div style="margin: 15px;">GENRE</div></th>\
    											<th><div style="margin: 15px;">LISTEN COUNT</div></th>\
                                                <th><div style="margin: 15px;">DURATION</div></th>\
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
    var loopNumber = jsonArray.length;
    if(jsonArray.length > 20){
    	loopNumber = 20;
    }
    for(var i=0; i<loopNumber; i++){
    	var song = jsonArray[i];
    	//APPEND THE BUTTONS ON THE LEFT SIDE OF SONG ITEM
    	var contentToBeAdded = '<tr class="songPageListContent" id="song' + song.songId + '">\
			<td><div><i class="material-icons song-content-play-button">play_circle_outline</i>';
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
    	contentToBeAdded += '</div></div>\
            			</div>'
    	contentToBeAdded += '</td>';
    	//ADD SONG NAME
    	contentToBeAdded += '<td><div>' + song.songName + '</div></td>';
    	//ADD ARTISTS' NAMES
    	var artistNames = song.artistNames;
    	contentToBeAdded += '<td><div>';
    	for(j=0; j<artistNames.length; j++){
    		contentToBeAdded += artistNames[j] + ' ';
    	}
    	contentToBeAdded += '</div></td>';
    	//ADD ALBUM NAME
    	contentToBeAdded += '<td><div>' + song.albumName + '</div></td>';
    	//ADD GENRE
    	contentToBeAdded += '<td><div>' + song.songGenre + '</div></td>';
    	//ADD LISTEN COUNT
    	contentToBeAdded += '<td><div>' + song.listenCount + '</div></td>';
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
    songAlbumImageUrls = [];
    songAlbumName=[];
    songArtistName=[];
    for(i=0; i<loopNumber; i++){
    	myPlaylist.push({mp3:"requestSongFile/" + jsonArray[i].songId +".html"});
    	console.log(jsonArray[i].imageType + ", " + i);
    	if(jsonArray[i].imageType == "file"){
    		songAlbumImageUrls.push('requestAlbumImage/' + jsonArray[i].albumId + '.html');
    	}else{
    		songAlbumImageUrls.push(jsonArray[i].albumImageUrl);
    	}
    	songAlbumName.push(jsonArray[i].albumName);
    	var artistNames = "";
    	for(j=0; j<jsonArray[i].artistNames.length; j++){
    		artistNames += jsonArray[i].artistNames[j] + ' ';
    	}
    	songArtistName.push(artistNames);
    }
    playlistLength = loopNumber;
	
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
				$("#confirm-button").off("click");
				var songIdStr = $(this).attr("id");
				$("#confirm-button").click(function(event){
					sendRemoveSongsRequestToServer(songIdStr.substring(4, songIdStr.length));
					$("#confirmationPopUp").modal("hide");
				});
				$("#editUserInfoPopUp").modal("hide");
				$("#confirmationMessage").html("Are you sure you want to remove this song?");
				$("#confirmationPopUp").modal("show");
			});
		}else{
			var ownsSong = false;
    		for(i=0; i< ownedSongIDs.length; i++){
    			if(jsonArray[index].songId == ownedSongIDs[i]){
    				ownsSong = true;
    			}
    		}
    		if(ownsSong){
    			$(this).click(function(event){
    				$("#confirm-button").off("click");
    				var songIdStr = $(this).attr("id");
    				$("#confirm-button").click(function(event){
    					sendRemoveSongsRequestToServer(songIdStr.substring(4, songIdStr.length));
    					$("#confirmationPopUp").modal("hide");
    				});
    				$("#editUserInfoPopUp").modal("hide");
    				$("#confirmationMessage").html("Are you sure you want to remove this song?");
    				$("#confirmationPopUp").modal("show");
    			});
    		}else{
    			$(this).hide();
    		}
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
                                            <div id="contentTitleWrapper" class="album-table-title" style="border-bottom:transparent; font-size:45px;">Albums</div>\
                                        </div>\
                                   </div>');
    //LOOP TO APPEND EACH ALBUM
    var contentToBeAdded = '<div class=centerSideContentWrapper>';
    var loopNumber = jsonArray.length;
    if(jsonArray.length > 10){
    	loopNumber = 10;
    }
    for(i = 0; i<loopNumber; i++){
    	var album = jsonArray[i];
    	//ADD ALBUM IMAGE AND NAME
    	contentToBeAdded += '<div class="AlbumPageContent">\
                							<div class=AlbumContentPicture id="album' + album.albumId;
                							
        if(album.imageType == "file"){
            contentToBeAdded += '" style="background-image: url(requestAlbumImage/' + album.albumId + '.html);"></div>';
        }else{
            contentToBeAdded += '" style="background-image: url(' + album.albumImageUrl + ');"></div>';
        }							
        contentToBeAdded += '<div class=AlbumContentDescription>\
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
        	selectedAlbumImageURL = $(".AlbumContentPicture").eq(index).css("background-image");
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
    			$("#confirm-button").off("click");
				$("#confirm-button").click(function(event){
					removeAlbum(jsonArray[index].albumId);
					$("#confirmationPopUp").modal("hide");
				});
				$("#editUserInfoPopUp").modal("hide");
				$("#confirmationMessage").html("Are you sure you want to remove this Album?");
				$("#confirmationPopUp").modal("show");
    		});
    	}else{
    		var ownsAlbum = false;
    		for(i=0; i< ownedAlbumIDs.length; i++){
    			if(jsonArray[index].albumId == ownedAlbumIDs[i]){
    				ownsAlbum = true;
    			}
    		}
    		if(ownsAlbum){
    			$(this).click(function(event){
        			$("#confirm-button").off("click");
    				$("#confirm-button").click(function(event){
    					removeAlbum(jsonArray[index].albumId);
    					$("#confirmationPopUp").modal("hide");
    				});
    				$("#editUserInfoPopUp").modal("hide");
    				$("#confirmationMessage").html("Are you sure you want to remove this Album?");
    				$("#confirmationPopUp").modal("show");
        		});
    		}else{	
    			$(this).hide();
    		}
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
    $("#centerSideContent").append('<div class=centerSideContentWrapper id=ArtistPageTop>\
                                        <div id="contentTitle">\
                                            <div id="contentTitleWrapper" class="artist-table-title" style="border-bottom:transparent; font-size:45px;">Artist</div>\
                                        </div>\
                                    </div>');
    //LOOP TO ADD EACH ARTIST
    
    var contentToBeAdded = '<div class=centerSideContentWrapper>';
    
    var loopNumber = jsonArray.length;
    if(jsonArray.length > 10){
    	loopNumber = 10;
    }
    for(i=0; i<loopNumber; i++){
    	var artist = jsonArray[i];
    	//ADD ARTIST NAME, IMAGE, ETC
    	contentToBeAdded += '<div class=ArtistPageContent>\
            					<div class=ArtistContentPicture id="artist' + artist.artistID
            						+ '" style="background-image:';
    	
    	if(artist.imageType == "file"){
    		contentToBeAdded += 'url(getProfileImageWithUsername/' + artist.userName + '.html)';
    	}else{
    		contentToBeAdded += 'url(' + artist.artistImageUrl + ')';
    	}
    	
    	contentToBeAdded += ';"></div>\
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
        	selectedArtistName = $(".ArtistContentName").eq(index).html();
        	selectedArtistImageURL = $(".ArtistContentPicture").eq(index).css("background-image");
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
    			$("#confirm-button").off("click");
				$("#confirm-button").click(function(event){
					removeArtist(jsonArray[index].artistID);
					$("#confirmationPopUp").modal("hide");
				});
				$("#editUserInfoPopUp").modal("hide");
				$("#confirmationMessage").html("Are you sure you want to remove this Artist?");
				$("#confirmationPopUp").modal("show");
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
                <div id="contentTitleWrapper" class="playlist-table-title" style="border-bottom:transparent; font-size:45px">Playlists</div>\
            </div>\
        </div>');
	var contentToBeAdded = '<div class=centerSideContentWrapper>';
	for(i=0; i<jsonArray.length; i++){
		var pl = jsonArray[i];
		if(!pl.private){
			contentToBeAdded += '<div class="centerPlaylistItem" id="playlist'+ pl.playlistID+'">'
				+ pl.playlistName + '</div>';
		}
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
		contentToBeAdded += '<div class="FSContent centerFriendListItem">\
        <div class="FSImage"><div class=FSImageInner centerFriendImage id="friend'+ friend.friendId+'" style="background-image: url(getProfileImageWithUsername/'+ friend.friendUserName +'.html);"></div></div>\
        <div class="FSFriendName centerFriendListFriendName">'+friend.friendUserName+'</div>';
        
        var isfriend = false;
        for(j=0;j<friendIds.length;j++){
        	if(friend.friendId == friendIds[j]){
        		isfriend = true;
        	}
        }
        if(!isfriend){
        	contentToBeAdded += '<button class="addFriendButton btn btn-primary" id="addFriend'+ friend.friendUserName+'">Add</button>';
        }
        if(userType == "ADMIN"){
        	contentToBeAdded += '<i class="material-icons adminDeleteUserButton" id="user' + friend.friendId + '">delete</i>';
        	if(!friend.friendBanned){
        		contentToBeAdded += '<i class="material-icons adminBanUserButton" id="user' + friend.friendId + '">not_interested</i>';
        	}
        }
        contentToBeAdded += '</div>';
	}
	contentToBeAdded += '</div>';
	$("#centerSideContent").append(contentToBeAdded);
	
	$(".centerFriendImage").each(function(index){
		$(this).click(function(event){
			var friendIdStr= $(this).attr("id");
			selectedUserImageURL = 'url(getProfileImageWithUsername/'+ jsonArray[index].friendUserName +'.html)';
			goToFriendPage(friendIdStr.substr(6, friendIdStr.length), $(".centerFriendListFriendName").eq(index).html());
		});
	});
	
	$(".addFriendButton").each(function(index){
		$(this).click(function(event){
			var friendIdStr= $(this).attr("id");
			addFriend(friendIdStr.substr(9, friendIdStr.length));
		});
	});
	
	$(".adminDeleteUserButton").each(function(index){
		$(this).click(function(event){
			var friendIdStr= $(this).attr("id");
			$("#confirm-button").off("click");
			$("#confirm-button").click(function(event){
				adminRemoveAccount(friendIdStr.substr(4, friendIdStr.length));
				$("#confirmationPopUp").modal("hide");
			});
			$("#editUserInfoPopUp").modal("hide");
			$("#confirmationMessage").html("Are you sure you want to unregister this user's account?");
			$("#confirmationPopUp").modal("show");
		});
	});
	
	$(".adminBanUserButton").each(function(index){
		$(this).click(function(event){
			var friendIdStr= $(this).attr("id");
			$("#confirm-button").off("click");
			$("#confirm-button").click(function(event){
				adminBanAccount(friendIdStr.substr(4, friendIdStr.length));
				$("#confirmationPopUp").modal("hide");
			});
			$("#editUserInfoPopUp").modal("hide");
			$("#confirmationMessage").html("Are you sure you want to ban this user's account?");
			$("#confirmationPopUp").modal("show");
		});
	});
}


function addGenreBoxesToCenter(){
	console.log("lalala");
	$('#centerSideContent').append('<div class=centerSideContentWrapper id=PlayListPrototypeTop>\
            <div id="contentTitle">\
                <div id="contentTitleWrapper" class="genre-table-title" style="border-bottom:transparent; font-size:45px;">Genres</div>\
            </div>\
        </div>\
    </div>');
	
	console.log("lalalaaaaaaa");
	
    $("#centerSideContent").append('<div class="genre-box" id="Rock"\
    		style="background-image:url(./images/Rock.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Indie-Rock"\
			style="background-image:url(./images/Indie-Rock.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Asia"\
	style="background-image:url(./images/Asia.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Audio-Collage"\
			style="background-image:url(./images/Audio-Collage.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Black-Metal"\
	style="background-image:url(./images/Black-Metal.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Blues"\
	style="background-image:url(./images/Blues.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Classical"\
	style="background-image:url(./images/Classical.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Disco"\
	style="background-image:url(./images/Disco.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Drone"\
	style="background-image:url(./images/Drone.png);"></div>');

    $("#centerSideContent").append('<div class="genre-box" id="Electroacoustic"\
	style="background-image:url(./images/Electronic.png);"></div>');

    $("#centerSideContent").append('<div class="genre-box" id="Experientmental-Pop"\
	style="background-image:url(./images/Experimental-Pop.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Experientmental"\
	style="background-image:url(./images/Experimental.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Folk-Music"\
	style="background-image:url(./images/Folk-Music.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Garage"\
	style="background-image:url(./images/Garage.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Hip-Hop"\
	style="background-image:url(./images/Hip-Hop.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Historic"\
	style="background-image:url(./images/Historic.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Improv"\
	style="background-image:url(./images/Improv);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Indian"\
	style="background-image:url(./images/Indian.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Interview"\
	style="background-image:url(./images/Interview.png);"></div>');

    $("#centerSideContent").append('<div class="genre-box" id="Jazz-Vocals"\
	style="background-image:url(./images/Experimental-Pop.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Jazz"\
	style="background-image:url(./images/Jazz.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Krautrock"\
	style="background-image:url(./images/Krautrock.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Latin"\
	style="background-image:url(./images/Latin.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Lo-Fi"\
	style="background-image:url(./images/Lo-Fi.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Loud-Rock"\
	style="background-image:url(./images/Loud-Rock.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Metal"\
	style="background-image:url(./images/Metal.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="No-Wave"\
	style="background-image:url(./images/No-Wave.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Pop"\
	style="background-image:url(./images/Pop.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Post-Punk"\
	style="background-image:url(./images/Post-Punk.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Post-Rock"\
	style="background-image:url(./images/Post-Rock.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Psych-Folk"\
	style="background-image:url(./images/Psych-Folk.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Punk"\
	style="background-image:url(./images/Punk.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Radio-Art"\
	style="background-image:url(./images/Radio-Art.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Reggae"\
	style="background-image:url(./images/Reggae.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Spoken-Weird"\
	style="background-image:url(./images/Spoken-Weird.png);"></div>');
    
    $("#centerSideContent").append('<div class="genre-box" id="Electronic"\
			style="background-image:url(./images/Electronic.png);"></div>');
    $("#centerSideContent").append('<div class="genre-box" id="Folk"\
			style="background-image:url(./images/Folk.png);"></div>');
    $("#centerSideContent").append('<div class="genre-box" id="Industrial"\
			style="background-image:url(./images/Industrial.png);"></div>');
    $("#centerSideContent").append('<div class="genre-box" id="Garage"\
			style="background-image:url(./images/Garage.png);"></div>');
    $("#centerSideContent").append('<div class="genre-box" id="Punk"\
			style="background-image:url(./images/Punk.png);"></div>');
    $("#centerSideContent").append('<div class="genre-box" id="Avant-Garde"\
			style="background-image:url(./images/Avant-Garde.png);"></div>');
    $("#centerSideContent").append('<div class="genre-box" id="Noise"\
			style="background-image:url(./images/Noise.png);"></div>');
    $("#centerSideContent").append('<div class="genre-box" id="Noise-Rock"\
			style="background-image:url(./images/Noise-Rock.png);"></div>');
    $("#centerSideContent").append('<div class="genre-box" id="Field-Recordings"\
			style="background-image:url(./images/Field-Recordings.png);"></div>');

    $("#centerSideContent").append('<div class="genre-box" id="Psych-Rock"\
			style="background-image:url(./images/Psych-Rock.png);"></div>');
    $("#centerSideContent").append('<div class="genre-box" id="International"\
			style="background-image:url(./images/International.png);"></div>');
    $("#centerSideContent").append('<div class="genre-box" id="Ambient-Electronic"\
			style="background-image:url(./images/Ambient-Electronic.png);"></div>');
    
    $(".genre-box").click(function(event){
    	getTopSongsOfGenre($(this).attr("id"));
    });
}
