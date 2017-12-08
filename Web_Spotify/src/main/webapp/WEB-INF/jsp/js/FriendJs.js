$( document ).ready(function() {
	getFriendList();
	
	$("#findFriendsButton").click(function(event){
		searchAndAddFriend();
	})
	
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
	var jsonArray = jQuery.parseJSON(jsonData);
	for(var i=0; i<jsonArray.length; i++){
		var friend = jsonArray[i];
		var contentToBeAdded = '<div class=FSContent>\
            <div class="FSImage"><div class=FSImageInner id="'+ friend.friendId+'"style="background-image: url(getProfileImageWithUsername/'+ friend.friendUserName +'.html);"></div></div>\
            <div class="FSFriendName">'+friend.friendUserName+'</div>\
        </div>';
		$('#rightBottomWrapper').append(contentToBeAdded);
	}
}

function searchAndAddFriend(){
	var searchUsername = $("#searchFriendInput").val();
	if(searchUsername == ""){
		return;
	}
	$.ajax({
		type : "POST",
		url : "addFriend/"+ searchUsername + ".html",
        success: function (data) {
        	if(data == "success"){
        		$('#rightBottomWrapper').empty();
        		getFriendList();
        	}
        }
	});
}
