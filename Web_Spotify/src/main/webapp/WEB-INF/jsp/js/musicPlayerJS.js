$( document ).ready(function() {
	player = $('#jquery_jplayer_1').jPlayer({
		ended: function() { // The $.jPlayer.event.ended event
		    $(this).jPlayer("play"); // Repeat the media
		},
		timeupdate: function(event) { // 4Hz
			// Restrict playback to first 60 seconds.
			console.log(event.jPlayer.status.currentTime);
		}
	});
	$("#next-button").click(function(event){
		playNextSong();
	});
	$("#prev-button").click(function(event){
		playPreviousSong();
	});
	$("#repeat-toggle-button").click(function(event){
		currentRepeatType++;
		if(currentRepeatType > 2){
			currentRepeatType = 0;
		}
		if(currentRepeatType == 0){
			$(this).html("arrow_forward");
		}else if(currentRepeatType == 1){
			$(this).html("repeat_one");
		}else{
			$(this).html("repeat");
		}
	});
	$("#shuffle-button").click(function(event){
		if(isShuffleOn == true){
			$(this).css("color", "white");
			isShuffleOn = false;
		}else{
			$(this).css("color", "blue");
			isShuffleOn = true;
		}
	});
//	player.on($.jPlayer.event.ended + ".jp-repeat", function(event) { // Using ".jp-repeat" namespace so we can easily remove this event
//	    $(this).jPlayer("play"); // Add a repeat behaviour so media replays when it ends. (Loops)
//	  });
	player.on($.jPlayer.event.play, function(e){
		$("#play-button").hide();
		$("#pause-button").show();
		var tempArr = myPlaylist[currentPlayingIndex].mp3.split("/");
		var songId = tempArr[tempArr.length-1].substring(0,tempArr[tempArr.length-1].length-5);
		getLyrics(songId);
	});
	player.on($.jPlayer.event.pause, function(e){
		$("#play-button").show();
		$("#pause-button").hide();
	});
	player.on($.jPlayer.event.stop, function(e){
		$("#play-button").show();
		$("#pause-button").hide();
	});
	player.on($.jPlayer.event.ended, function(e){
		if(currentRepeatType == 0){
			if(currentPlayingIndex != playlistLength - 1){
				playNextSong();
			}else{
				player.jPlayer("stop");
			}
		}else if(currentRepeatType == 1){
			$(this).jPlayer("play");
		}else{
			playNextSong();
		}
	});
});

function playNextSong(){
	if(isShuffleOn){
		currentPlayingIndex = Math.floor(Math.random() * playlistLength);
	}else{
		currentPlayingIndex++;
		if(currentPlayingIndex >= playlistLength){
			currentPlayingIndex = 0;
		}
	}
	player.jPlayer("setMedia", myPlaylist[currentPlayingIndex]);
	player.jPlayer("play");
}

function playPreviousSong(){
	if(isShuffleOn){
		currentPlayingIndex = Math.floor(Math.random() * playlistLength);
	}else{
		currentPlayingIndex--;
		if(currentPlayingIndex < 0){
			currentPlayingIndex = playlistLength - 1;
		}
	}
	player.jPlayer("setMedia", myPlaylist[currentPlayingIndex]);
	player.jPlayer("play");
}

function getLyrics(songId){
	$.ajax({
		type : "GET",
		url : "requestLyricsFile/" + songId + ".html",
		success : function(data) {
			currentLyrics = data;
			renderLyrics();
		}
	});
}

function renderLyrics(){
	$(".lyrics-body").html(currentLyrics);
}