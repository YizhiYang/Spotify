$( document ).ready(function() {
	getAdvertisements();
	
	
	$(".advertisement-close-button").click(function(event){
		$("#advertisementBlock").hide();
	});
	
	$("#submitAddNewAddButton").click(function(event){
		addAdvertisement();
	});
});

function getAdvertisements(){
	$.ajax({
		type : "GET",
		url : "getAds.html",
		success : function(data) {
			$('#advertisementWrapper').empty()
			var jsonArray = jQuery.parseJSON(data);
			for(i=0; i< jsonArray.length; i++){
				var advertisement = jsonArray[i];
				var contentToBeAdded = '<div class="adImage" \
						style="background-image:url(' + advertisement.ImageURL + ');">';
				if(userType == "ADMIN"){
					contentToBeAdded += '<i class="material-icons deleteAdButton" style ="position: absolute;" id="ad' + advertisement.Id + '">delete_forever</i>'
				}
				
				contentToBeAdded += '</div';
				
				$('#advertisementWrapper').append(contentToBeAdded);
			}
			
			$(".deleteAdButton").click(function(event){
				var adIdStr = $(this).attr("id");
				deleteAdvertisement(adIdStr.substr(2, adIdStr.length));
			});
			
			$('.autoplay').slick({
				slidesToShow: 1,
				slidesToScroll: 1,
				autoplay: true,
				arrows: false,
				dots: false,
				autoplaySpeed: 2000,
			});
		}
	});
}


function addAdvertisement(){
	var imageData = $("#addNewAdInput").serialize();;
	
	$.ajax({
		type : "POST",
		url : "addAds.html",
		data: imageData,
		success : function(data) {
			$("#addNewAdPopUp").modal('hide');
			$('.autoplay').slick('unslick');
			getAdvertisements();
		}
	});
}

function deleteAdvertisement(adId){
	$.ajax({
		type : "POST",
		url : "removeAds/" + adId + ".html",
		success : function(data) {
			$('.autoplay').slick('unslick');
			getAdvertisements();
		}
	});
}