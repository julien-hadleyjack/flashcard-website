$(document).ready(function() {    		
	var highestId = 0;
 		initEditors();
    		
	/* Edit Button Click Event */
	/* Change Edit to Save Button and show Editor */
	$(document).on("click", ".edit", function(e){
		e.preventDefault();
		var element = $(this).parents().eq(1).children(".paper2");

		$(element).children(".redBorder").hide();
		$(element).children(".mce-container").show();
						
		$(this).html('<i class="fa fa-save fa-fw"></i>');
		$(this).removeClass("edit");
		$(this).addClass("save");
	});
	
	/* Save Button Click Event */
	/* Change back to Edit Button, hide Editor, change content and show it */
	$(document).on("click", ".save", function(e) {
		e.preventDefault();
		var element = $(this).parents().eq(1).children(".paper2"),
			value = $(element).find("iframe").contents().find("body").text();
			
		// check if editor is empty
		if(!value || 0 === value.length) {
			alert("Bitte gebe einen Titel ein!");
		} else {
			$(element).children(".mce-container").hide();
			$(element).children(".redBorder").show();
			
			// change before request ended
			$(element).children(".redBorder").html(value);

			if($(this).parents().eq(1).attr("data-id") != undefined){
				$.post( "/jsp/editFlashcardSet.jsp", { setId: $(this).parents().eq(1).attr("data-id"), title: value }, function( data ) {
					$(element).children(".redBorder").html('<a href="/learningscreen.html?setId=' + $(this).parents().eq(1).attr("data-id") + '">' + value + '</a>');
				});
			} else {
				$.post( "/jsp/addFlashcardSet.jsp", { title: value } , function( data ) {
					
					$(this).parents().eq(1).attr("data-id", data.replace("\n", ""));
					// add link
					$(element).children(".redBorder").html('<a href="/learningscreen.html?setId=' + data.replace("\n", "") + '">' + value + '</a>');
				});
			}
			
			$(this).html('<i class="fa fa-pencil fa-fw"></i>');
			$(this).removeClass("save");
			$(this).addClass("edit");
		}
	});		
	
	/* Remove Button Event */
	/* confirm box to delete whole Flashcard set */
	$(document).on("click", ".remove", function(e) {
		e.preventDefault();
		var element = $(this).parents().eq(1);
					
		if(confirm('Sollen die Karteikarten wirklich gel�scht werden?')){
			if($(element).find("add") != undefined){
				$.post( "/jsp/deleteFlashcardSet.jsp", { setId: $(element).attr("data-id") } , function( data ) {
					$(element).remove();
				});
			} else {
				$(element).remove();
			}
		}
	});	
	
	/* Add Button Event */
	/* Add Flashcard Set */
	$(document).on("click", ".add", function(e){
		e.preventDefault();
		var element = $(this).parents().eq(2).children(".paper2"),
			container = $(element).parent();
		console.log(container);
		$(container).after($(container).clone());
		
		highestId+=5;
		$(element).append('<textarea id="editor-' + highestId + '" class="paper-textarea"></textarea>');

		$(element).children(".redBorder").hide();
						
		$(container).find(".icon").removeClass("hidden");
		initEditors();
		$(element).children(".mce-container").show();
	});
	
	/* Init FlashCard Sets */
	$.getJSON("/jsp/getSetsFromUser.jsp", function(data){
		$.each(data, function (index, value) {
			$("#wrapper").prepend('<div class="paper paper-small paper-dashboard" data-id="' + value.setId + '"><div class="paper-buttons"><a href="#" class="icon remove"><i class="fa fa-minus fa-fw"></i></a><a href="#" class="icon edit"><i class="fa fa-pencil fa-fw"></i></a></div><div class="paper2"><div class="redBorder"><a href="learningscreen.html?setId=' + value.setId + '">' + value.title + '</a></div> <textarea id="editor-' + value.setId + '" class="paper-textarea">' + value.title + '</textarea></div></div>');
		});
		initEditors();
		highestId = $(".paper-dashboard").eq(-2).attr("data-id");
	});

});