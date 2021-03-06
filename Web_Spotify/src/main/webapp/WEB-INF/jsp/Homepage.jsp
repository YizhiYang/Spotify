<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Spotify</title>
	<style type="text/css"><%@ include file="css/homepage/bodyStyle.css" %> </style>
	<style type="text/css"><%@ include file="css/homepage/playBar.css" %> </style>
	<style type="text/css"><%@ include file="css/homepage/left.css" %> </style>
	<style type="text/css"><%@ include file="css/homepage/center.css" %> </style>
	<style type="text/css"><%@ include file="css/homepage/right.css" %> </style>
	<style type="text/css"><%@ include file="css/homepage/playListPrototypePage.css" %> </style>
	<style type="text/css"><%@ include file="css/homepage/albumPage.css" %> </style>
	<style type="text/css"><%@ include file="css/homepage/artistPage.css" %> </style>
	<style type="text/css"><%@ include file="css/homepage/songsPage.css" %> </style>
	<style type="text/css"><%@ include file="js/slick/slick.css" %> </style>
 	<link href="https://fonts.googleapis.com/css?family=Raleway | Asap | Work+Sans | Ubuntu | Oxygen | Archivo+Black | Rokkitt | Passion+One" rel="stylesheet">
 	<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
  	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	<script type="application/javascript"><%@ include file="js/signInJS.js" %></script>
  	<script type="application/javascript"><%@ include file="js/signUpJS.js" %></script>
  	<script type="application/javascript"><%@ include file="js/homepageJS.js" %></script>
  	<script type="application/javascript"><%@ include file="js/jPlayer/jquery.jplayer.min.js" %></script>
  	<script type="application/javascript"><%@ include file="js/musicPlayerJS.js" %></script>
  	<script type="application/javascript"><%@ include file="js/CenterContentInsertionManager.js" %></script> 
  	<script type="application/javascript"><%@ include file="js/SongJS.js" %></script>
  	<script type="application/javascript"><%@ include file="js/AlbumJS.js" %></script>
  	<script type="application/javascript"><%@ include file="js/ArtistJS.js" %></script>
  	<script type="application/javascript"><%@ include file="js/albumPage.js" %></script>
  	<script type="application/javascript"><%@ include file="js/artistPage.js" %></script>
  	<script type="application/javascript"><%@ include file="js/songsPage.js" %></script>
  	<script type="application/javascript"><%@ include file="js/browsePage.js" %></script>
  	<script type="application/javascript"><%@ include file="js/searchJS.js" %></script>
  	<script type="application/javascript"><%@ include file="js/addToCollectionJS.js" %></script>
  	<script type="application/javascript"><%@ include file="js/playlistJS.js" %></script>
  	<script type="application/javascript"><%@ include file="js/FriendJs.js" %></script>
  	<script type="application/javascript"><%@ include file="js/slick/slick.min.js" %></script>
  	<script type="application/javascript"><%@ include file="js/advertisementJS.js" %></script>
  	<script type="application/javascript"><%@ include file="js/GenreJS.js" %></script>
</head>
<body>
	<div id="main">
        <div id=left>
            <div id="leftWrapper">
                             <div id=userInfoWrapper>
                    <div id="userInfoInnerWrapper">
                    	<div id=profilePic>
    						<img width="80" height="80" class ="profile-image-home" src="Profile-Image.html"></img>
						</div>
                        <div id=userInfo>
                        	<div class="dropdown" id="accoun-info-menu" >
    		    				<i class="material-icons dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" style="font-size: 30px;">more_horiz</i>
    		  					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton" style="min-width:0px;background-color:transparent;">
    		  						<div class="dropdown-item"><a onclick="upOrDowngradeAccountPopUp()"><i class="material-icons" style="font-size: 30px;" >info</i></a></div>
    		  						<div class="dropdown-item"><a onclick="editPopUp()"><i class="material-icons" style="font-size: 30px;" >settings</i></a></div>
    		  					</div>
    		  				</div>
                            <div id="username">Jay Bird</div>
                            <div id="usertype">Basic User</div>
                           
                        </div>
                    </div>
                </div>
                <div id="userSongWrapper">
                    <ul>
                        <li class=userSongWrapperList>
                        	<div class='userSongWrapperItem' id="Home-Browse-Button">Browse</div>
                        </li>
                        <li></li>
                        <li class="sideBarMenuHeader">YOUR LIBRARY</li>
                        <li class=userSongWrapperList>
                        	<div class='userSongWrapperItem' id="Home-Library-Overview-Button">Overview</div>
                        </li>
                        <li class=userSongWrapperList>
                        	<div class='userSongWrapperItem' id="Home-History-Button">Play History</div>
                        </li>
                        <li class=userSongWrapperList>
                        	<div class='userSongWrapperItem' id="Home-Song-Button">Songs</div>
                        </li>
                        <li class=userSongWrapperList>
                        	<div class='userSongWrapperItem' id="Home-Album-Button">Albums</div>
                        </li>
                        <li class=userSongWrapperList>
                        	<div class='userSongWrapperItem' id="Home-Artist-Button">Artists</div>
                        </li>
						<li></li>
                        <li class="sideBarMenuHeader">MY PLAYLISTS <i class="material-icons" data-toggle="modal" data-target="#addNewPlaylistPopUp">library_add</i>
                        	<ul id="My-Playlist-List-Wrapper">
                        		<li></li>
                        	</ul>
                        </li>
                        <li></li>
                        <li class="sideBarMenuHeader" id="admin-menu-header">Admin Menu</li>
                        <li class=userSongWrapperList>
                        	<div class='userSongWrapperItem' data-toggle="modal" id="Admin-UploadSong-Button" data-target="#uploadSongPopUp">Upload Song</div>
                        </li>
                        <li class=userSongWrapperList>
                        	<div class='userSongWrapperItem' data-toggle="modal" id="Admin-CreateAlbum-Button" data-target="#createAlbumPopUp">Create Album</div>
                        </li>
                        </li>
                        <li class=userSongWrapperList>
                        	<div class='userSongWrapperItem' data-toggle="modal" id="Admin-MakeUserArtist-Button" data-target="#makeUserArtistPopUp">Make User Artist</div>
                        </li>
                        <li class=userSongWrapperList>
                        	<div class='userSongWrapperItem' id="Admin-PendingSongs-Button">Songs Pending To Be Approved</div>
                        </li>
                        <li class=userSongWrapperList>
                        	<div class='userSongWrapperItem' id="Admin-AddAdvertisement-Button" data-toggle="modal" data-target="#addNewAdPopUp">Add Advertisement</div>
                        </li>
                        <li class=userSongWrapperList>
                        	<div class='userSongWrapperItem' id="Admin-AddAccount-Button" data-toggle="modal" data-target="#adminAddAccountPopUp">Add Account</div>
                        </li>
                        <li class=userSongWrapperList>
                        	<div class='userSongWrapperItem' id="Admin-AddArtist-Button" data-toggle="modal" data-target="#adminAddArtistPopUp">Add Artist</div>
                        </li>
                        
                        <li class="sideBarMenuHeader" id="artist-menu-header">Artist Menu</li>
                        <li class=userSongWrapperList>
                        	<div class='userSongWrapperItem' id="Artist-My-Albums-Button">My Albums</div>
                        </li>
                        <li class=userSongWrapperList>
                        	<div class='userSongWrapperItem' id="Artist-My-Followers-Button">My Followers</div>
                        </li>
                    </ul>
                </div>
                <br>
                <br>

                <div id=playBarImage style="background-image: url(./images/175x175bb.jpg);"></div>
            </div>
        
        </div>
        <div id="center">
            <div id="centerTopBar">
                <div id="centerTopWrapper">
                    <input class="centerTopBarContent" id="searchInput" type="text" name="searchContent">
                    <i class="material-icons centerTopBarContent" id="searchButton">search</i>
                    <div class="centerTopBarContent" id="recommended-button">Recommended</div>
                    <div class="centerTopBarContent" id="genre-button">Genre</div>
                    <div class="centerTopBarContent" id="editor-choice-button">Editor Choice</div>
                </div>
                <div style="float: right; margin-top: 13px; margin-right: 30px;">
                <div id="private-mode-text" style="position:relative; right: 100px; cursor: pointer;">Private Off</div>
            
                <i id="logout-button" class="material-icons" style="position: relative; left: 50px; bottom: 26px;">power_settings_new</i></div>
            </div>
            <div id="centerSideContent">                                   
            </div>
            <div id=right>
                <div id=rightTopWrapper>
                    <p id="friend-list-title">Friends</p>
                    <input id="searchFriendInput" type="text" name="friendUsername">
                    <i class="material-icons" id="findFriendsButton">search</i>
                </div>
                <div id=rightBottomWrapper>
                </div>
    
            </div>
        </div>
    </div>
    
    
    <!-- Advertisement -->
    <div id="advertisementBlock">
    <i class="material-icons advertisement-close-button">close</i>
    	<div id = "advertisementWrapper" class="autoplay">
    	</div>
    </div>
    
    
    
    
    
    <!-- Play Bar -->
    <div id="jp_container_1" class="jp-audio" role="application" aria-label="media player">
    	<div id="playBar">
			<div id="jquery_jplayer_1" class="jp-jplayer"></div>
        	<div id="playBarLeft">
            	<div id="playBarAlbumName"></div>
            	<div id="playBarArtistName"></div>

        	</div>
        	<div id="playBarCenter">
            	<div id=playBarFunctionality>
                	<i class="material-icons icons playBarFunctionalityButton" id="prev-button" style="font-size:35px;">skip_previous</i>
					<i class="material-icons icons playBarFunctionalityButton jp-play" id="play-button" style="font-size:35px;">play_circle_outline</i>
					<i class="material-icons icons playBarFunctionalityButton jp-pause" id="pause-button" style="font-size:35px;" style="display:none;">pause</i>
					<i class="material-icons icons playBarFunctionalityButton jp-stop" id="stop-button" style="font-size:35px;">stop</i>
                	<i class="material-icons icons playBarFunctionalityButton" id="next-button" style="font-size:35px;">skip_next</i>
                	<i class="material-icons icons playBarFunctionalityButton" id="repeat-toggle-button" style="font-size:35px;">arrow_forward</i>
                	<i class="material-icons icons playBarFunctionalityButton" data-toggle="modal" data-target="#lyricsPopup" id="lyrics-button" style="font-size:35px;">receipt</i>
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
	
	<!-- Pop up for add artist -->
<div class="modal fade" id="adminAddArtistPopUp">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Add New Artist</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="add-artist-form" method="post">
  			<div class="form-group">
    			<label class="col-form-label" for="addArtistImageURLInput">Image to be added for Artist</label>
    			<input type="text" class="form-control" id="addArtistImageURLInput" name="imageURL" placeholder="Image URL">
    			<label class="col-form-label" for="addArtistArtistNameInput">Artist Name</label>
    			<input type="text" class="form-control" id="addArtistArtistNameInput" name="artistName" placeholder="Artist Name">
    			<label class="col-form-label" for="addArtistArtistNameInput">Artist Bio</label>
    			<input type="text" class="form-control" id="addArtistArtistBioInput" name="artistBio" placeholder="Artist Bio">
  			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="submitAddArtistButton">Add Artist</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


<!-- Popup for admin add account -->
<div class="modal fade" id="adminAddAccountPopUp">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
      Add An Account
      </div>
      <div class="modal-body">
		<form id="signUpForm">
    			<input type="text" class="form-control" type="text" name="email" placeholder="Email">
                <input type="text" class="form-control" id="signupUsername" type="text" name="username" placeholder="Username">
                <div id="feedback" style="display:none; color:red">
                 Username is alraedy taken.
                 </div>
                 <div id="feedback-success" style="display:none; color:#000">
                  Username is available.
                  </div>
                  <input type="text" class="form-control" id="signupPassword" type="password" name="password" placeholder="Password">                      
                  <input type="text" class="form-control" id="signupPasswordRepeat" type="password" name="confirm-password" placeholder="Confirm Password">
                  <div id="password-feedback" style="display:none; color:red">
                  Passwords do not match.
                  </div>
    		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="admin-add-account-button">Add This Account</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


	
<!-- Pop up for Lyrics -->
<div class="modal fade" id="lyricsPopup">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-body lyrics-body">
      	No Lyrics Found
      </div>
    </div>
  </div>
</div>


<!-- Pop up for adding advertisement -->
<div class="modal fade" id="addNewAdPopUp">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Add Advertisement</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="add-new-ad-form" method="post">
  			<div class="form-group">
    			<label class="col-form-label" for="addNewAdInput">URL of the Advertisement</label>
    			<input type="text" class="form-control" id="addNewAdInput" name="adName" placeholder="URL">
  			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="submitAddNewAddButton">Add Advertisement</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div> 


	
<!-- Pop up for Account Upgrade -->
<div class="modal fade" id="accountUpgradePopUp">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">Add Credit Card Info And Upgrade to Premium</div>
      <div class="modal-body">
		<form id="credit-card-form" method="post">
  			<input class="form-control" id="cardNumber" type="text" name="cardNumber" placeholder="Card number">                      
            <input class="form-control" id="cardName" type="text" name="cardHolderName" placeholder="Card holder name">
            <input class="form-control" id="billingAddress" type="text" name="billingAddress" placeholder="Billing address">
           	<input class="form-control" id="expiredDate" type="text" name="expiredDate" placeholder="Expiration date">                      
            <input class="form-control" id="cvv" type="text" name="cvv" placeholder="CVV">
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="confirm-upgrade-button">Confirm Upgrade</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- Pop up for Account Downgrade -->
<div class="modal fade" id="accountDowngradePopUp">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">Downgrade from Premium Account to Basic Account</div>
      <div class="modal-body">
      	<div class="modal-footer">
        	<button type="button" class="btn btn-primary" id="confirm-downgrade-button">Confirm Downgrade</button>
        	<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      	</div>
      </div>
    </div>
  </div>
</div>

<!-- Pop up for changing playlist name -->
<div class="modal fade" id="changePlaylistNamePopUp">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Change Playlist Name</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="change-playlist-name-form" method="post">
  			<div class="form-group">
    			<label class="col-form-label" for="changePlaylistNameInput">Name of Playlist</label>
    			<input type="text" class="form-control" id="changePlaylistNameInput" name="playlistName" placeholder="Playlist Name">
  			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="submitChangePlaylistNameButton">Confirm</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>                        

<!-- Pop up for adding new playlist -->
<div class="modal fade" id="addNewPlaylistPopUp">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Add New Playlist</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="add-new-playlist-form" method="post">
  			<div class="form-group">
    			<label class="col-form-label" for="addNewPlaylistNameInput">Name of Playlist to be added</label>
    			<input type="text" class="form-control" id="addNewPlaylistNameInput" name="playlistName" placeholder="Playlist Name">
    			<input type="checkbox" id="addNewPlaylistPrivacyInput" name="private" value="YES" >Private
  			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="submitAddNewPlaylistButton">Make Playlist</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>    
                
<!-- Pop up for uploading Song -->
<div class="modal fade" id="uploadSongPopUp">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Upload Song</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <audio id="upload-temp-audio"></audio>
        <form id="upload-song-form" method="post" enctype="multipart/form-data">
  			<div class="form-group">
  				<label class="col-form-label" for="formGroupSongNameInput">Song Name</label>
    			<input type="text" class="form-control" id="formGroupSongNameInput" name="songName" placeholder="Song Name">
    			<label class="col-form-label" for="formGroupDurationInput">Duration</label>
    			<input type="text" class="form-control" id="formGroupDurationInput" name="duration" placeholder="0:00">
    			<label class="col-form-label" for="formGroupAlbumIDInput" name="albumID">Album ID</label>
    			<input type="text" class="form-control" id="formGroupAlbumIDInput" name="albumID" placeholder="Album ID">
    			<label for="FormSongFile">Song File</label>
    			<input type="file" class="form-control-file" name="fileUp" id="FormSongFile" accept=".mp3">
    			<label for="FormLyricsFile">Lyrics File</label>
    			<input type="file" class="form-control-file" name="lyricsFileUp" id="FormLyricsFile" accept=".lrc">
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

<!-- Pop up for creating Album -->
<div class="modal fade" id="createAlbumPopUp">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="create-album-form" method="post" enctype="multipart/form-data">
  			<div class="form-group">
  				<label class="col-form-label" for="createAlbumAlbumNameInput">Album Name</label>
    			<input type="text" class="form-control" id="createAlbumAlbumNameInput" name="albumName" placeholder="Album Name">
    			<label class="col-form-label" for="createAlbumArtistIDInput">Artist ID</label>
    			<input type="text" class="form-control" id="createAlbumArtistIDInput" name="artistID" placeholder="Artist ID">
    			<label for="createAlbumImageFile">Album Image File</label>
    			<input type="file" class="form-control-file" name="fileUp" id="createAlbumImageFile" accept=".jpg">
  			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="submitCreateAlbumButton">Create Album</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


<!-- Pop up for make user artist -->
<div class="modal fade" id="makeUserArtistPopUp">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="make-user-artist-form" method="post">
  			<div class="form-group">
    			<label class="col-form-label" for="makeUserArtistUserIDInput">User ID to be added as Artist</label>
    			<input type="text" class="form-control" id="makeUserArtistUserIDInput" name="userID" placeholder="User ID">
    			<label class="col-form-label" for="makeUserArtistArtistNameInput">Artist Name</label>
    			<input type="text" class="form-control" id="makeUserArtistArtistNameInput" name="artistName" placeholder="Artist Name">
  			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="submitMakeUserArtistButton">Make User Artist</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- Displays Success Message -->
<div class="modal fade" id="successPopUp">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body" id="puopUpMessage">
			Place Holder
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- Popup for Confirm user info -->
<div class="modal fade" id="confirmationPopUp">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body" id="confirmationMessage">
			Place Holder
      </div>
      <div class="modal-footer">
      	<button type="button" class="btn btn-danger" id="confirm-button">Yes!</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
      </div>
    </div>
  </div>
</div>
	
<!-- Popup for editing user info -->
<div class="modal fade" id="editUserInfoPopUp">
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
		<form id="update-user-info-form" method="post">
			<div class="form-group">
  				<label class="col-form-label" for="userinfo-username-input">UserName</label>
  				<input type="text" class="form-control" id="userinfo-username-input" name ="username" aria-describedby="basic-addon3" readonly>
  				<label class="col-form-label" for="userinfo-email-input">Email</label>
  				<input type="text" class="form-control" id="userinfo-email-input" name ="email" aria-describedby="basic-addon3">
  				<label class="col-form-label" for="userinfo-location-input">Location</label>
  				<input type="text" class="form-control" id="userinfo-location-input" name = "location" aria-describedby="basic-addon3" >
			<div class="form-group">
		</form>
      </div>
      <div class="modal-footer">
      	<button type="button" class="btn btn-danger" id="remove-loggedin-user-button">Remove This Account</button>
        <button type="button" class="btn btn-primary" id="save-user-info-update-changes-button">Save changes</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

</body>
</html>