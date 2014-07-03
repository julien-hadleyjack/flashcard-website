/**
*	StudyFlash
*	learningscreen.js
*/

$(document).ready(function() {
    		
    // init classes
    $("#wrapper").find(".paper-big").eq(0).addClass("activeSlide").addClass("firstSlide").show();
    $(".paper-big").last().addClass("lastSlide");
    
    // prev/next
    $(document).on("click", "#left-button", function(e){
		e.preventDefault(); 
		slideLeft();
	});
	
	$(document).on("click", "#right-button", function(e){
		e.preventDefault(); 	
		slideRight();
	});
	
	// answer handling
	$(document).on("click", ".right", function(e){
		e.preventDefault(); 
		rightAnswer();
	});
	
	$(document).on("click", ".wrong", function(e){
		e.preventDefault(); 
		wrongAnswer();
	});

	
	// keyboard shortcus
	$(document.documentElement).keyup(function (event) {
		// left key
	  	if (event.keyCode == 37) {
	    	slideLeft();
	    // right key
	  	} else if (event.keyCode == 39) {
	    	slideRight();
	    // a key
	  	} else if (event.keyCode == 65) {
	  		$(".answerButton").click();
	    // r key
	  	} else if (event.keyCode == 82) {
			rightAnswer();
	    // f key
	  	} else if (event.keyCode == 70) {
			wrongAnswer();
	  	} else if (event.keyCode == 69) {
	  		// no flip -> flip and show editor
	  		if(!$(".activeSlide .paper2").hasClass("flipped")){
		  		$(".answerButton").click();
				$(".activeSlide").find(".edit").click();
				$(".activeSlide .paper2").addClass("autoFlip");
			} else {
				// flipped -> save and flip back if flipped 
				$(".activeSlide").find(".save").click();
				if($(".activeSlide .paper2").hasClass("autoFlip")){
					$(".activeSlide .paper2").removeClass("autoFlip");
					$(".answerButton").click();
				}
			}
	  	}

	});

	function wrongAnswer() {
		var wrongElem = $(".activeSlide").find(".wrong");
	  	if(!$(wrongElem).hasClass("checked")){
			$(wrongElem).addClass("checked");						
			var rightElem = $(".activeSlide").find(".right");
			if($(rightElem).hasClass("checked")){
				$(rightElem).removeClass("checked");
				rightAnswers--;
			}
		}
	}
	
	function rightAnswer() {
		var rightElem = $(".activeSlide").find(".right");
	  	if(!$(rightElem).hasClass("checked")){
			$(rightElem).addClass("checked");
			var wrongElem = $(".activeSlide").find(".wrong");
			if($(wrongElem).hasClass("checked")){
				$(wrongElem).removeClass("checked");
			}
		
			rightAnswers++;
		}
	}

	function slideLeft() {
		if(!$(".firstSlide").hasClass("activeSlide")){
			
			reset();				
			
			var prevElement = $(".activeSlide").prev();
			$(".activeSlide").toggle("slide", { direction: 'right' }, 700);	
			$(".activeSlide").removeClass("activeSlide");

			$(prevElement).toggle("slide", { direction: 'left' }, 700);	
			$(prevElement).addClass("activeSlide");
					
			if(!$(prevElement).hasClass("stats")){
				$(".answerButton").show().animate({opacity: 0.4}, 700);
			}
			
			// answer button check
			if(!$(".activeSlide .paper2").hasClass("flipped")){
				//$(".answerButton").html("Antwort anzeigen");
			} else {
				//$(".answerButton").html("Frage anzeigen");
			}
		}
	}
		
	function slideRight() {
		if(!$(".lastSlide").hasClass("activeSlide")){
			reset();
						
			var nextElement = $(".activeSlide").next();
			$(".activeSlide").toggle("slide", { direction: 'left' }, 700);	
			$(".activeSlide").removeClass("activeSlide");
	
			$(nextElement).toggle("slide", { direction: 'right' }, 700);	
			$(nextElement).addClass("activeSlide");
			
			if($(nextElement).hasClass("stats")){
				$(".answerButton").animate({opacity: 0}, 700, function() {
					$(".answerButton").hide();
				});
				
				var procent = (rightAnswers/cardCount) * 100;
				
			$({value: 0}).delay(200).animate({value: procent}, {
				    duration: 1000,
				    easing:'swing',
				    step: function() 
				    {
				        $(".cs_knob").val(Math.ceil(this.value)).trigger('change');
				    }
				});
				
				$(".stats .bigText").html(rightAnswers + "/" + cardCount + " Richtig");
			}
		}
		
		// answer button check
		if(!$(".activeSlide .paper2").hasClass("flipped")){
			//$(".answerButton").html("Antwort anzeigen");
		} else {
			//$(".answerButton").html("Frage anzeigen");
		}
	}
	$(document).on("click", ".answerButton", function(e){
		e.preventDefault(); 
		if(!$(".activeSlide .paper2").hasClass("flipped")){
			$(".activeSlide .front").fadeOut(500);
			$(".activeSlide .back").delay(500).fadeIn();
			$(".activeSlide .paper-buttons").delay(500).fadeIn();
			$(this).html("Frage anzeigen");
		} else {
			$(".activeSlide .back").fadeOut(500);
			$(".activeSlide .front").delay(500).fadeIn();
			$(".activeSlide .paper-buttons").fadeOut(500);
			$(this).html("Antwort anzeigen");
		}
		
		$(".activeSlide .paper2").removeClass("autoFlip");
		$(".activeSlide .paper2").toggleClass("flipped");
	});
	
	// reset view
	function reset(){
		if($(".activeSlide .paper2").hasClass("flipped")){
			$(".activeSlide .paper2").toggleClass("flipped");
		} 
		$(".answerButton").html("Antwort anzeigen");
		$(".activeSlide .paper-buttons").hide();
	}
	
	// Statistics Plugin
	$(".cs_knob").knob({
		min : 0, 
		max : 100, 
		step : 1, 
		angleOffset : 0, 
		angleArc : 360, 
		stopper : true, 
		readOnly : true, 
		cursor : false,  
		lineCap : 'butt', 
		thickness : '0.3', 
		width : 200, 
		displayInput : true, 
		displayPrevious : true, 
		fgColor : '#7AF576', 
		inputColor : '#000000', 
		font : 'Arial', 
		fontWeight : 'normal', 
		bgColor : '#F75457'
	});
});
