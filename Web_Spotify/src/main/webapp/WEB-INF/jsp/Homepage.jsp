<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Jay Bird&#8482;</title>
	<style type="text/css"><%@ include file="css/homepage/bodyStyle.css" %> </style>
	<style type="text/css"><%@ include file="css/homepage/playBar.css" %> </style>
	<style type="text/css"><%@ include file="css/homepage/left.css" %> </style>
	<style type="text/css"><%@ include file="css/homepage/center.css" %> </style>
	<style type="text/css"><%@ include file="css/homepage/right.css" %> </style>
	<style type="text/css"><%@ include file="css/homepage/playListPrototypePage.css" %> </style>
  <link href="https://fonts.googleapis.com/css?family=Raleway | Asap | Work+Sans | Ubuntu | Oxygen | Archivo+Black | Rokkitt | Passion+One" rel="stylesheet">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
      <script
  src="https://code.jquery.com/jquery-3.2.1.min.js"
  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
  crossorigin="anonymous"></script>
  
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
  
  <script type="application/javascript"><%@ include file="js/homepageJS.js" %></script>
  
  <script type="application/javascript"><%@ include file="js/jPlayer/jquery.jplayer.min.js" %></script>
  <script type="application/javascript"><%@ include file="js/jPlayer/jplayer.playlist.min.js" %></script>
  <script type="application/javascript"><%@ include file="js/SongJS.js" %></script>
  <script type="application/javascript"><%@ include file="js/playListPrototypePage.js" %></script>


    
</head>
<body>
    <div id="main">
        <div id=left>
            <div id="leftWrapper">
                <div id=userInfoWrapper>
                    <div id="userInfoInnerWrapper">
                    	<div id=profilePic>
    						<img width="60" height="60" class ="profile-image-home" src="Profile-Image.html"></img>
						</div>
                        <div id=userInfo>
                            <div id=username>Jay Bird</div>
                            <div id=usertype>Premium User</div>
                           	<div id=useredit><a onclick="editPopUp()">Profile</a></div>
                        </div>
                    </div>
                </div>
                <div id="userSongWrapper">
                    <ul>
                        <li class=userSongWrapperList><div class='userSongWrapperItem'>Browse</div></li>
                        <li class=userSongWrapperList><div class='userSongWrapperItem'>Radio</div></li>
                        <li class=userSongWrapperHeader>YOUR LIBRARY</li>
                        <li></li>
                        <li></li>
                        <li class=userSongWrapperList><div class='userSongWrapperItem'>Recently Played</div></li>
                        <li class=userSongWrapperList><div class='userSongWrapperItem'>Songs</div></li>
                        <li class=userSongWrapperList><div class='userSongWrapperItem'>Albums</div></li>
                        <li class=userSongWrapperList><div class='userSongWrapperItem'>Artists</div></li>
                        <li class=userSongWrapperList><div class='userSongWrapperItem'>Stations</div></li>
                        <li class=userSongWrapperList><div class='userSongWrapperItem'>Local Files</div></li>
                        <li class=userSongWrapperHeader>MY PLAYLISTS</li>
                        <li></li>
                        <li></li>
                        <li class=userSongWrapperList><div class='userSongWrapperItem'>Liked from Radio</div></li>
                        <li class=userSongWrapperList><div class='userSongWrapperItem' id="My-PlayList-Button">My Play List</div></li>
                        <li class=userSongWrapperList><div class='userSongWrapperItem' data-toggle="modal" data-target="#uploadSongPopUp">Upload Song</button></div></li>
                    </ul>
                </div>
                
                <!-- Pop up for uploading Song -->
<div class="modal fade" id="uploadSongPopUp">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="upload-song-form" method="post" enctype="multipart/form-data">
  			<div class="form-group">
  				<label class="col-form-label" for="formGroupSongNameInput">Song Name</label>
    			<input type="text" class="form-control" id="formGroupSongNameInput" name="songName" placeholder="Song Name">
    			<label class="col-form-label" for="formGroupArtistNameInput">Artist Name</label>
    			<input type="text" class="form-control" id="formGroupArtistNameInput" name="artistName" placeholder="Artist Name">
    			<label class="col-form-label" for="formGroupDurationInput">Duration</label>
    			<input type="text" class="form-control" id="formGroupDurationInput" name="duration" placeholder="0:00">
    			<label class="col-form-label" for="formGroupAlbumNameInput" name="albumName">Album Name</label>
    			<input type="text" class="form-control" id="formGroupAlbumNameInput" name="albumName" placeholder="Album Name">
    			<label for="FormSongFile">Song File</label>
    			<input type="file" class="form-control-file" name="fileUp" id="FormSongFile" accept=".mp3">
  			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="submitSongFormButton">Upload Song</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

            
            </div>
        
        </div>
        <div id="center">
            <div id=centerTopBar>
                <div id=centerTopWrapper>
                    <div class=centerTopBarContent>
<!--                        <i class="material-icons">search</i>-->
                        <input class=centerTopBarContent type="text" name="firstname" value="Search">
                    </div>
                    <div class=centerTopBarContent>Overview</div>
                    <div class=centerTopBarContent>Charts</div>
                    <div class=centerTopBarContent>Genre</div>
                    <div class=centerTopBarContent>More</div>
                </div>
            </div>
            <div id="centerSideContent">
                <div id=centerSideContentWrapper>
                    <div id="contentTitle">
                        <div id="contentTitleWrapper" style="border-bottom:transparent;">
                            Sleep Well!
                        </div>
                    </div>
                    <div class="contentObject">
                        <div class="contentPicture" style="background-image: url('./images/albumCover1.jpg');"></div>
                        <div class="contentName"><p>Sons of Plunder</p></div>
                        <div class="contentDescription">
                            <p>As the countless numbers hunger For worldwide renown All the pimping sons of plunder Will roll up their sleeves</p>
                        </div>
                        <div class="contentFollowers">
                            <p>500,000 FOLLOWERS</p>
                        </div>
                    </div>
                    <div class="contentObject">
                        <div class="contentPicture" style="background-image: url('./images/albumCover2.jpg');"></div>
                        <div class="contentName"><p>Hand of Blood</p></div>
                        <div class="contentDescription">
                            <p>Oh. There goes my Valentine again. I'm soaked in red for what she said. And now she's gone. Oh my God have I done it again</p>
                        </div>
                        <div class="contentFollowers">
                            <p>1,000,000 FOLLOWERS</p>
                        </div>
                    </div>
                    <div class="contentObject">
                        <div class="contentPicture" style="background-image: url('./images/albumCover3.jpg');"></div>
                        <div class="contentName"><p>Wrong Side of Heaven</p></div>
                        <div class="contentDescription">
                            <p>I spoke to God today and she said that she's ashamed What have I become. What have I done</p>
                        </div>
                        <div class="contentFollowers">
                            <p>250,000 FOLLOWERS</p>
                        </div>
                    </div>
                </div>
                <div id=centerSideContentWrapper>
                    <div id="contentTitle">
                        <div id="contentTitleWrapper">
                            Genre & Mood
                        </div>
                        <div class="GMContent">
                            <div class=GMContentPicture style="background-image: url(./images/GMImage1.jpg);"></div>
                            <div class=GMContentLabel><div class=GMContentLabelWrapper>Workout</div></div>
                        </div>
                        <div class="GMContent">
                            <div class=GMContentPicture style="background-image: url(./images/GMImage2.jpg);"></div>
                            <div class=GMContentLabel><div class=GMContentLabelWrapper>Metal</div></div>
                        </div>
                        <div class="GMContent">
                            <div class=GMContentPicture style="background-image: url(./images/GMImage3.jpg);"></div>
                            <div class=GMContentLabel><div class=GMContentLabelWrapper>Pop</div></div>
                        </div>
                        <div class="GMContent">
                            <div class=GMContentPicture style="background-image: url(./images/GMImage4.jpg);"></div>
                            <div class=GMContentLabel><div class=GMContentLabelWrapper>Rock</div></div>
                        </div>
                        <div class="GMContent">
                            <div class=GMContentPicture style="background-image: url(./images/GMImage5.jpg);"></div>
                            <div class=GMContentLabel><div class=GMContentLabelWrapper>Gaming</div></div>
                        </div>
                    </div>
                </div>
                                    
            </div>
            <div id=right>
                <div id=rightTopWrapper>
                    <p id="findFriendsText">See what your friends are playing</p>
                    <button id="findFriends">Find Friends</button>
                </div>
                <div id=rightBottomWrapper>
                    <div class=FSContent>
                        <div class="FSImage"><div class=FSImageInner style="background-image: url(./images/FSProfile1.jpg);"></div></div>
                        <div class=FSSongDetails>
                            <div class="FSFriendName">Matt Yang</div>
                            <div class="FSInnerContent">Decadence</div>
                            <div class="FSInnerContent">Ten Thousand Fists</div>
                            <div class="FSInnerContent">Disturbed</div>
                            <div class="FSInnerContent">Alternative Metal</div>
                        </div>
                        <div class="FSPlaySong">
                            <i class="material-icons icons" style="font-size:50px;">play_circle_filled</i>   
                        </div>
                    </div>
                    <div class=FSContent>
                        <div class="FSImage"><div class=FSImageInner style="background-image: url(./images/FSProfile2.jpg);"></div></div>
                        <div class=FSSongDetails>
                            <div class="FSFriendName">Dan Choe</div>
                            <div class="FSInnerContent">Hand of Blood</div>
                            <div class="FSInnerContent">the Poison</div>
                            <div class="FSInnerContent">Bullet for my valentine</div>
                            <div class="FSInnerContent">Metal</div>
                        </div>
                        <div class="FSPlaySong">
                            <i class="material-icons icons" style="font-size:50px;">play_circle_filled</i>   
                        </div>
                    </div>
                    <div class=FSContent>
                        <div class="FSImage"><div class=FSImageInner style="background-image: url(./images/FSProfile3.jpg);"></div></div>
                        <div class=FSSongDetails>
                            <div class="FSFriendName">Limeng Ruan</div>
                            <div class="FSInnerContent">Wrong Side of Heaven</div>
                            <div class="FSInnerContent">The Wrong Side of Heaven</div>
                            <div class="FSInnerContent">Five Finger Death Punch</div>
                            <div class="FSInnerContent">Alternative Metal</div>
                        </div>
                        <div class="FSPlaySong">
                            <i class="material-icons icons" style="font-size:50px;">play_circle_filled</i>   
                        </div>
                    </div>
                </div>
    
            </div>
        </div>
    </div>
    
<div class="modal fade" id="editPopUp">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <div height="60" width="60" style="position:relative;">
        <img width="60" height="60" class ="profile-image-home" src="Profile-Image.html"></img>
    						<form id="uploadProfileImageForm" enctype="multipart/form-data">
    							<input type="file" id="profile-image-chooser" name="fileUp" accept=".png,.jpg,.jpeg"
    							style="position: absolute; top:0; width:60px;height:60px;opacity: 0;">
    						</form>
    						</div>

      </div>
      <div class="modal-body">
		<div class="input-group">
  			<span class="input-group-addon" id="basic-addon3">Username</span>
  			<input type="text" class="form-control" id="basic-url" name ="email" aria-describedby="basic-addon3" readonly>
  		</div>
  		<br>
  		<div class="input-group">
  			<span class="input-group-addon" id="basic-addon3">Email</span>
  			<input type="text" class="form-control" id="basic-url" name = "location" aria-describedby="basic-addon3" >
  		</div>
	
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">Save changes</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
    
    
    
    
    
    <!-- Play Bar -->
    <div id="jp_container_1" class="jp-audio" role="application" aria-label="media player">
    <div id="playBar">
		<div id="jquery_jplayer_1" class="jp-jplayer"></div>
		
        <div id="playBarLeft">
            <div id=playBarImage style="background-image: url(./images/albumCover1.jpg);"></div>
            <div id=playBarLeftAddSong>
                <div id=playBarLeftAddSongWrapper>
                    <i class="material-icons icons" style="font-size:35px;">playlist_add</i>
                </div>
            </div>
            <div id=albumName>Immortalized</div>
            <div id="artistName">Disturbed</div>

        </div>
        <div id="playBarCenter">
            <div id=playBarFunctionality>
                <i class="material-icons icons playBarFunctionalityButton">thumb_down</i>
                <i class="material-icons icons playBarFunctionalityButton" style="font-size:35px;">skip_previous</i>
				<button class="jp-play" role="button" tabindex="0">
					<i class="material-icons icons" style="font-size:35px;">play_circle_outline</i>
				</button>
                <i class="material-icons icons playBarFunctionalityButton" style="font-size:35px;">skip_next</i>
                <i class="material-icons icons playBarFunctionalityButton">thumb_up</i>
            </div>
			
            <div id="progressBar">
			<div class="jp-current-time" role="timer" aria-label="time">&nbsp;</div>
			<div class="jp-duration" role="timer" aria-label="duration">&nbsp;</div>
			<div>&nbsp;</div>
                <div id="progressBarWrapper">
                    <div class="jp-seek-bar">
						<div class="jp-play-bar"></div>
					</div>
                </div>
			
            </div>
        </div>
        <div id="playBarRight">
            <div id="volumeController">
				<div class="jp-volume-controls">
					<button class="jp-mute" role="button" tabindex="0">
						<i class="material-icons icons">volume_up</i>
					</button>
					<div class="jp-volume-bar">
						<div class="jp-volume-bar-value"></div>
					</div>
				</div>
            </div>
        </div>
		<div class="CurrentPlaylist" style="display: none;">
			<div class="jp-playlist">
				<ul>
					<li>&nbsp;</li>
				</ul>
			</div>
		</div>
		
	</div>
    
	</div>

</body>
</html>