$( document ).ready(function() {
	player = $('#jquery_jplayer_1').jPlayer({
		ended: function() { // The $.jPlayer.event.ended event
		    $(this).jPlayer("play"); // Repeat the media
		},
		timeupdate: function(event) { // 4Hz
			if(event.jPlayer.status.currentTime > 1){
				renderLyrics(event.jPlayer.status.currentTime);
			}
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
		if(currentRepeatType > 3){
			isShuffleOn = false;
			currentRepeatType = 0;
		}
		if(currentRepeatType == 0){
			$(this).html("arrow_forward");
		}else if(currentRepeatType == 1){
			$(this).html("repeat_one");
		}else if(currentRepeatType == 2){
			$(this).html("repeat");
		}else{
			isShuffleOn = true;
			$(this).html("shuffle");
		}
	});
	player.on($.jPlayer.event.play, function(e){
		$("#play-button").hide();
		$("#pause-button").show();
		var tempArr = myPlaylist[currentPlayingIndex].mp3.split("/");
		var songId = tempArr[tempArr.length-1].substring(0,tempArr[tempArr.length-1].length-5);
		$(".song-content-play-button").eq(currentPlayingIndex).html("pause");
		$("#playBarImage").css("background-image", "url(" + songAlbumImageUrls[currentPlayingIndex] +")");
		$("#playBarAlbumName").html(songAlbumName[currentPlayingIndex]);
		$("#playBarArtistName").html(songArtistName[currentPlayingIndex]);
		$(".song-content-play-button").each(function(index){
			if(index != currentPlayingIndex){
				console.log(index +","+ currentPlayingIndex);
				$(this).html("play_circle_outline");
			}
		});
		getLyrics(songId);
		addSongToPlayHistory(songId);
		e.preventDefault();
	});
	player.on($.jPlayer.event.pause, function(e){
		$(".song-content-play-button").eq(currentPlayingIndex).html("play_circle_outline");
		$("#play-button").show();
		$("#pause-button").hide();
	});
	player.on($.jPlayer.event.stop, function(e){

		$(".song-content-play-button").eq(currentPlayingIndex).html("play_circle_outline");
		$("#play-button").show();
		$("#pause-button").hide();
	});
	player.on($.jPlayer.event.ended, function(e){

		$(".song-content-play-button").eq(currentPlayingIndex).html("play_circle_outline");
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
			var allLines = data.split('[');
			currentLyricsLine = [];
			currentLyricsTime = [];
			for(i=0; i<allLines.length; i++){
				var line = allLines[i];
				if(line.indexOf("]")>=0){
					var timeAndLine = line.split("]");
					var timeStringArr = timeAndLine[0].split(":");
					var minute = parseInt(timeStringArr[0]);
					var second = parseInt(timeStringArr[1]);
					var totalSeconds = minute*60 + second;
					currentLyricsTime.push(totalSeconds);
					currentLyricsLine.push(timeAndLine[1]);
				}
			}
		}
	});
}

function renderLyrics(currentTime){
	var indexOfNextLyricsLine = -1;
	for(i=0; i<currentLyricsTime.length; i++){
		if(currentTime<currentLyricsTime[i]){
			indexOfNextLyricsLine = i;
			break;
		}
	}
	var totalLyricsToDisplay = "";
	if(indexOfNextLyricsLine == -1){
		//END OF SONG
		//RENDER LAST THREE LINES
		indexOfNextLyricsLine = currentLyricsLine.length;
		totalLyricsToDisplay += '<p style="color:#fff;">' + currentLyricsLine[indexOfNextLyricsLine - 3] + '</p>';
		totalLyricsToDisplay += '<p style="color:#fff;">' + currentLyricsLine[indexOfNextLyricsLine - 2] + '</p>';
		totalLyricsToDisplay += '<p style="color:red;">' + currentLyricsLine[indexOfNextLyricsLine - 1] + '</p>';
		$(".lyrics-body").html(totalLyricsToDisplay);
		return;
	}
	//RENDER PREVIOUS LINE
	if(indexOfNextLyricsLine - 2 >= 0){
		totalLyricsToDisplay += '<p style="color:#fff;">' + currentLyricsLine[indexOfNextLyricsLine - 2] + '</p>';
	}
	//RENDER CURRENT LINE
	if(indexOfNextLyricsLine - 1 >= 0){
		totalLyricsToDisplay += '<p style="color:red;">' + currentLyricsLine[indexOfNextLyricsLine - 1] + '</p>';
	}
	//RENDER NEXT LINE
	totalLyricsToDisplay += '<p style="color:#fff;">' + currentLyricsLine[indexOfNextLyricsLine] + '</p>';
	
	$(".lyrics-body").html(totalLyricsToDisplay);
}

function addSongToPlayHistory(songId){
	$.ajax({
		type : "POST",
		url : "addSongToPlayHistory/" + songId + ".html",
		success : function(data) {
			
		}
	});
}
