$( document ).ready(function() {
	$("#genre-button").click(function(event){
		$("#centerSideContent").empty();
		addGenreBoxesToCenter();
	});
	
});


function addGenreBoxesToCenter(){
    $("#centerSideContent").append('<div class="genre-box" id="Rock"\
    		style="background-image:url(./images/Rock.png);"></div>');
    $("#centerSideContent").append('<div class="genre-box" id="Indie-Rock"\
			style="background-image:url(./images/Indie-Rock.png);"></div>');
    $("#centerSideContent").append('<div class="genre-box" id="Audio-Collage"\
			style="background-image:url(./images/Audio-Collage.png);"></div>');
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
    $("#centerSideContent").append('<div class="genre-box" id="Drone"\
			style="background-image:url(./images/Drone.png);"></div>');
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

function getTopSongsOfGenre(genre){
	$.ajax({
		type : "GET",
		url : "getSongsOfGenre/" + genre + ".html",
        success: function (data) {
        	$('#centerSideContent').empty();
        	addSongsToCenterContent(data);
        }
	});
}