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
  <link href="https://fonts.googleapis.com/css?family=Raleway | Asap | Work+Sans | Ubuntu | Oxygen | Archivo+Black | Rokkitt | Passion+One" rel="stylesheet">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
    
    <script src="jsp/js/jquery-1.7.2.min.js"></script>
    <script src="jsp/js/jquery-ui-1.8.21.custom.min.js"></script>



    
</head>
<body>
    <div id="main">
        <div id=left>
            <div id="leftWrapper">
                <div id=userInfoWrapper>
                    <div id="userInfoInnerWrapper">
                        <div id=profilePic>
                        	<img width="80" src="Profile-Image.html"></img>
                        </div>
                        <div id=userInfo>
                            <div id=username>Jay Bird</div>
                            <div id=usertype>Premium User</div>
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
                        <li class=userSongWrapperList><div class='userSongWrapperItem'>My Play List</div></li>
                    </ul>
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
    <div id="playBar">
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
                <i class="material-icons icons playBarFunctionalityButton" style="font-size:35px;">play_circle_outline</i>
                <i class="material-icons icons playBarFunctionalityButton" style="font-size:35px;">skip_next</i>
                <i class="material-icons icons playBarFunctionalityButton">thumb_up</i>
            </div>
            <div id="progressBar">
                <div id="progressBarWrapper">
                    <input type="range" id='progressBar'>
                </div>
            </div>
        </div>
        <div id="playBarRight">
            <div id="volumeController">
                <i class="material-icons icons">volume_up</i>
                <input type="range" id='volumeBar'>
                <i class="material-icons icons">volume_down</i>
                <i class="material-icons icons">playlist_play</i>
            </div>
        </div>
    
    </div>

</body>
</html>