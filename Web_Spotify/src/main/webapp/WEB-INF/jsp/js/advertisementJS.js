$( document ).ready(function() {
	getAdvertisements();
	
	
	$(".advertisement-close-button").click(function(event){
		$("#advertisementBlock").hide();
	});
});

function getAdvertisements(){
	$.ajax({
		type : "GET",
		url : "getAds.html",
		success : function(data) {
			console.log(data);
			$('#advertisementWrapper').empty()
			var jsonArray = jQuery.parseJSON(data);
			for(i=0; i< jsonArray.length; i++){
				var advertisement = jsonArray[i];
				$('#advertisementWrapper').append('<div class="adImage" \
						style="background-image:url(' + advertisement.ImageURL + ');"></div>');
			}
			
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