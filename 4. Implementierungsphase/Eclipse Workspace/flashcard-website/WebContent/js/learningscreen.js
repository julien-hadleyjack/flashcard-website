/**
*	StudyFlash
*	learningscreen.js
*/

$(document).ready(function() {
    	
	/* Init FlashCard/Slide Classes */
	init();
    
    /* Left Button Click Event */
	/* call slide to left function */
    $(document).on("click", "#left-button", function(e){
		e.preventDefault(); 
		slideLeft();
	});
	
	/* Right Button Click Event */
	/* call slide to right function */
	$(document).on("click", "#right-button", function(e){
		e.preventDefault(); 	
		slideRight();
	});
	
	/* Right Answer Button Click Event */
	/* call right answers function (rightanswers + 1) */
	$(document).on("click", ".right", function(e){
		e.preventDefault(); 
		rightAnswer();
	});
	
	/* Wrong Answer Button Click Event */
	/* call wrong answer function (rightanswers - 1) */
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
		// e key
	  	} else if (event.keyCode == 69) {
			$(".activeSlide .edit").click();
			$(".activeSlide").find(".paper-buttons").show();
	  	}

	});

	/* Answer Button Click Event */
	/* cancel editor, fade out question, fade in buttons and answer, or reverse */
	$(document).on("click", ".answerButton", function(e){
		e.preventDefault(); 
		cancelEdit();
		if(!$(".activeSlide .paper2").hasClass("flipped")){
			$(".activeSlide .paper-buttons").delay(500).fadeIn();
			$(".activeSlide .back").delay(500).fadeIn();
			$(".activeSlide .front").fadeOut();
			$(this).html("Frage anzeigen");
		} else {
			$(".activeSlide .paper-buttons").fadeOut(100);
			$(".activeSlide .front").delay(500).fadeIn();
			$(".activeSlide .back").fadeOut();
			$(this).html("Antwort anzeigen");
		}
		
		$(".activeSlide .paper2").toggleClass("flipped");
	});
	
	/* Edit Button Click Event */
	/* change to save button, insert editors */
	$(document).on("click", ".edit", function(e){
		e.preventDefault();
		var element = $(this).parents().eq(1).children(".paper2"),
			id = $(this).parents().eq(2).attr("data-id");

		$(element).children(".redBorder").hide();
		tinymce.EditorManager.execCommand('mceAddEditor', true, "editor-f-" + id);
		tinymce.EditorManager.execCommand('mceAddEditor', true, "editor-a-" + id);

						
		$(this).parents().eq(1).find(".undo").removeClass("hidden");
											
		$(this).html('<i class="fa fa-save fa-2x"></i>');
		$(this).removeClass("edit");
		$(this).addClass("save");
	});
		
	/* Add Button Click Event */
	/* insert flashcard HTML on the right side of activeSlide, slide to the new flashcard */
	$(document).on("click", ".add", function(e) {
		e.preventDefault();
		
		var container = $(this).parents().eq(2);
		console.log(container);
		highestId+=5;
		$(container).after('<div class="paper paper-big center paper-animate hidden adding" data-id="' + highestId + '"><div class="paper-holder"><div class="paper-buttons hidden"><a href="#" class="icon add"><i class="fa fa-plus fa-2x"></i></a><a href="#" class="icon edit"><i class="fa fa-pencil fa-2x"></i></a><a href="#" class="icon remove"><i class="fa fa-minus fa-2x"></i></a><a href="#" class="icon undo hidden"><i class="fa fa-undo fa-2x"></i></a><a href="#" class="icon wrong"><i class="fa fa-minus-circle fa-2x"></i></a><a href="#" class="icon right"><i class="fa fa-check fa-2x"></i></a></div><div class="paper2"><div class="redBorder"><figure class="front">Frage eingeben</figure><figure class="back hidden">Antwort eingeben</figure></div><textarea id="editor-f-' + highestId + '" class="paper-textarea-big">Frage eingeben</textarea><textarea id="editor-a-' + highestId + '" class="paper-textarea-big">Antwort eingeben</textarea></div></div></div>');

		slideRight();	
		$(".answerButton").click();
		$(".activeSlide .edit").click();	
	});

	/* Save Button Click Event */
	/* change content, submit to database, change back to edit button, remove editors */
	$(document).on("click", ".save", function(e) {
		e.preventDefault();
		var element = $(this).parents().eq(1).children(".paper2"),
			id = $(this).parents().eq(2).attr("data-id");
		
		var question = $(element).find("#editor-f-" + id + "_ifr").contents().find("body").html(),
			answer = $(element).find("#editor-a-" + id + "_ifr").contents().find("body").html();

		if((!question || 0 === question.length) | (!answer || 0 === answer.length)) {
			alert("Bitte gebe eine Frage und eine Antwort ein!");
		} else {
			$(element).children(".redBorder").show();
				
			$(element).find(".front").html(question);
			$(element).find(".back").html(answer);


			if(!$(this).parents().eq(2).hasClass("adding")){
				$.post( "/jsp/editFlashcardWithId.jsp", { flashcardId: $(this).parents().eq(2).attr("data-id"), question: question, answer: answer } );
			} else {
				$.post( "/jsp/addFlashcardToSetWithId.jsp", { question: question, answer: answer, setId: $.getUrlVar("setId") } , function( data ) {
					
					$(this).parents().eq(1).attr("data-id", data.replace("\n", ""));
					$(this).parents().eq(1).removeClass("adding");
				});
			}
									
			tinymce.EditorManager.execCommand('mceRemoveEditor', false, "editor-f-" + id);
			tinymce.EditorManager.execCommand('mceRemoveEditor', false, "editor-a-" + id);

			$(this).html('<i class="fa fa-pencil fa-2x"></i>');
			$(this).removeClass("save");
			$(this).addClass("edit");
			$(this).parents().eq(1).find(".undo").addClass("hidden");
			
			init();
			
			if(!$(".activeSlide .paper2").hasClass("flipped")){
				$(".activeSlide .paper-buttons").fadeOut(100);
			}
		}
	});	
	
	/* Undo Button Click Event */
	/* close edit mode and forget changes made in the editor */
	$(document).on("click", ".undo", function(e){
		e.preventDefault();
		var element = $(this).parents().eq(2),
			id = $(this).parents().eq(2).attr("data-id");

		cancelEdit();
		$(this).addClass("hidden");
		var saveButton = $(this).parents().eq(1).find(".save");
		$(saveButton).html('<i class="fa fa-pencil fa-2x"></i>');
		$(saveButton).removeClass("save");
		$(saveButton).addClass("edit");
		
		tinymce.EditorManager.execCommand('mceRemoveEditor', false, "editor-f-" + id);
		tinymce.EditorManager.execCommand('mceRemoveEditor', false, "editor-a-" + id);

		$(element).find("#editor-f-" + id).val($(element).find(".front").html());
		$(element).find("#editor-a-" + id).val($(element).find(".back").html());

		if(!$(".activeSlide .paper2").hasClass("flipped")){
			$(".activeSlide .paper-buttons").fadeOut(100);
		}

	});

	/* Remove Button Click Event */
	/* start removal process if more than 2 slides (1 slide + stats slide) are there, else delete set on dashboard */
	$(document).on("click", ".remove", function(e) {
		e.preventDefault();
		var element = $(this).parents().eq(2);
		
		if($(".paper-big").length < 3){
			alert("Es ist nur noch eine Karteikarte vorhanden, bitte lösche das Set im Dashboard!");
		} else {
			if(confirm('Soll die Karteikarte wirklich gelöscht werden?')){
				if(!$(element).hasClass("adding")){
					$.post( "/jsp/removeFlashcardWithId.jsp", { flashcardId: $(element).attr("data-id") } , function( data ) {
						$(element).remove();
					});
				} else {
					$(element).remove();
				}
				
				$("#right-button").click();
				
				// first or last slide deletion 
				init();
				
				$(".ui-effects-wrapper").remove();
			}
		}
	});	

	/* Init FlashCards */
	/* init editors, load flashcards, init classes and set highest id for new cards */
	$.getJSON("/jsp/getFlashcardsForSetWithId.jsp", {setId: $.getUrlVar("setId")}, function(data){
		initEditors();
		$.each(data, function (index, value) {
			$("#wrapper").prepend('<div class="paper paper-big center paper-animate hidden" data-id="' + value.flashcardId + '"><div class="paper-holder"><div class="paper-buttons hidden"><a href="#" class="icon add"><i class="fa fa-plus fa-2x"></i></a><a href="#" class="icon edit"><i class="fa fa-pencil fa-2x"></i></a><a href="#" class="icon remove"><i class="fa fa-minus fa-2x"></i></a><a href="#" class="icon undo hidden"><i class="fa fa-undo fa-2x"></i></a><a href="#" class="icon wrong"><i class="fa fa-minus-circle fa-2x"></i></a><a href="#" class="icon right"><i class="fa fa-check fa-2x"></i></a></div><div class="paper2"><div class="redBorder"><figure class="front">' + value.question + '</figure><figure class="back hidden">' + value.answer + '</figure></div><textarea id="editor-f-' + value.flashcardId + '" class="paper-textarea-big">' + value.question + '</textarea><textarea id="editor-a-' + value.flashcardId + '" class="paper-textarea-big">' + value.answer + '</textarea></div></div></div>');
		});
		init();
		$("#wrapper").find(".paper-big").eq(0).addClass("activeSlide");
		highestId = $(".paper-big").eq(-2).attr("data-id");
	});
		
	// Statistic Plugin Settings
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

	/* Init Flashcard Classes */
	function init(){
	    // init classes
	    if(!$("#wrapper").find(".paper-big").eq(0).hasClass("stats")){
	    	$("#wrapper").find(".paper-big").eq(0).addClass("firstSlide").show();
	    }
	    $(".paper-big").last().addClass("lastSlide");
	    
	    cardCount = $(".paper-big").length - 1;
	}
	
	/* reset view */
	function reset(){
		if($(".activeSlide .paper2").hasClass("flipped")){
			$(".activeSlide .paper2").toggleClass("flipped");
		} 
		$(".answerButton").html("Antwort anzeigen");
		$(".activeSlide .paper-buttons").hide();
		
		// Close Editor
		cancelEdit();
	}

	/* Statistics */
	/* rightanswers - 1 */
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
	
	/* rightanswers + 1 */
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

	/* Slide Functions */
	/* slide activeSlide and prev slide to the right side if we are not on the first slide */
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
		}
	}
		
	/* slide activeSlide and next slide to the left side if we are not on the last slide */
	function slideRight() {
		if(!$(".lastSlide").hasClass("activeSlide")){
			reset();
						
			var nextElement = $(".activeSlide").next();
			$(".activeSlide").toggle("slide", { direction: 'left' }, 700);	
			$(".activeSlide").removeClass("activeSlide");
	
			$(nextElement).toggle("slide", { direction: 'right' }, 700);	
			$(nextElement).addClass("activeSlide");
			
			/* if next slide is statistics > init statistics plugin */
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
	}

	/* Cancel Edit */
	/* remove editor, show content, change save button to edit button */
	function cancelEdit() {
		var element = $(".activeSlide").find(".paper2"),
			button = $(element).find(".save");
		
		tinymce.EditorManager.execCommand('mceRemoveEditor', false, "editor-f-" + $(".activeSlide").attr("data-id"));
		tinymce.EditorManager.execCommand('mceRemoveEditor', false, "editor-a-" + $(".activeSlide").attr("data-id"));

		$(element).children(".redBorder").show();
		if(button.length > 0){
			$(element).find(".undo").click();
		}
																	
		$(button).html('<i class="fa fa-pencil fa-2x"></i>');
		$(button).removeClass("save");
		$(button).addClass("edit");
		$(element).find(".undo").addClass("hidden");
	}

