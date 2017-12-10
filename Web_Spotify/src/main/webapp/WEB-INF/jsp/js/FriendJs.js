$( document ).ready(function() {
	getFriendList();
	
	$("#findFriendsButton").click(function(event){
		searchAndAddFriend();
	});
	
	$("#Artist-My-Followers-Button").click(function(event){
		getArtistFollowers(loggedInArtistId);
	});
	
});

function getFriendList(){
	$.ajax({
		type : "GET",
		url : "getFriendList.html",
        success: function (data) {
        	$('#rightBottomWrapper').empty();
        	renderFriendList(data);
        }
	});
}

function renderFriendList(jsonData){
	friendIds = [];
	var jsonArray = jQuery.parseJSON(jsonData);
	for(var i=0; i<jsonArray.length; i++){
		var friend = jsonArray[i];
		var contentToBeAdded = '<div class="FSContent rightFriendListItem" id="friend'+ friend.friendId+'">\
            <div class="FSImage"><div class=FSImageInner style="background-image: url(getProfileImageWithUsername/'+ friend.friendUserName +'.html);"></div></div>\
            <div class="FSFriendName rightFriendListFriendName">'+friend.friendUserName+'</div>\
        </div>';
		$('#rightBottomWrapper').append(contentToBeAdded);
		
		friendIds.push(friend.friendId);
	}
	$(".rightFriendListItem").each(function(index){
		$(this).click(function(event){
			var friendIdStr= $(this).attr("id");
			goToFriendPage(friendIdStr.substr(6, friendIdStr.length), $(".rightFriendListFriendName").eq(index).html());
		});
	});
}

function searchAndAddFriend(){
	var searchUsername = $("#searchFriendInput").val();
	if(searchUsername == ""){
		return;
	}
	$.ajax({
		type : "GET",
		url : "searchFriend/"+ searchUsername + ".html",
        success: function (data) {
        	$("#centerSideContent").empty();
        	addFriendsToCenterContent(data);
        }
	});
}

function getArtistFollowers(artistID){
	$.ajax({
		type : "GET",
		url : "getFollowers/"+ artistID + ".html",
        success: function (data) {
        	console.log(data);
        	$("#centerSideContent").empty();
        	addFriendsToCenterContent(data);
        }
	});
}

function addFriend(friendUsername){
	$.ajax({
		type : "POST",
		url : "addFriend/"+ friendUsername + ".html",
        success: function (data) {
        	if(data == "success"){
        		$('#rightBottomWrapper').empty();
        		getFriendList();
        	}
        }
	});
}

function goToFriendPage(friendId, friendName){
	$.ajax({
		type : "GET",
		url : "getFriendsPlaylist/"+friendId+".html",
        success: function (data) {
        	$("#centerSideContent").empty();
        	addPlaylistsToCenterContent(data);
        	$(".playlist-table-title").html(friendName);
        	$('.playlist-table-title').parent().append('<div id="unfriendButton">Unfriend</div>'); 	
        	$('#unfriendButton').click(function(event){
        		removeFriend(friendId);
        	});
        	
        	loadFriendPlayHistory(friendId);
        }
	});
	
}

function loadFriendPlayHistory(friendId){
	$.ajax({
		type : "GET",
		url : "getFriendPlayHistorySongs/" + friendId + ".html",
		success : function(data) {
			addSongsToCenterContent(data);
			$('.song-table-title').html("His/Her Play History");
		}
	});
}

function removeFriend(friendId){
	$.ajax({
		type : "POST",
		url : "removeFriend/"+ friendId + ".html",
        success: function (data) {
        	$('#rightBottomWrapper').empty();
        	getFriendList();
        	getRecommendedPage();
        }
	});
}
