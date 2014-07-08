$(document).ready(function() {    		
	var highestId = 0;
 		initEditors();
    		
	/* Edit Button Click Event */
	/* Change Edit to Save Button and show Editor */
	$(document).on("click", ".edit", function(e){
		e.preventDefault();
		var element = $(this).parents().eq(1).children(".paper2");

		$(element).children(".redBorder").hide();
		tinymce.EditorManager.execCommand('mceAddEditor', true, "editor-" + $(this).parents().eq(1).attr("data-id"));
						
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
			console.log($(this).parents().eq(1).attr("data-id"));
			console.log($(this).parents().eq(1));
			tinymce.EditorManager.execCommand('mceRemoveEditor', false, "editor-" + $(this).parents().eq(1).attr("data-id"));
			$(element).children(".redBorder").show();
			
			// change before request ended
			$(element).children(".redBorder").html(value);

			if(!$(this).parents().eq(1).hasClass("adding")){
				$.post( "/jsp/editFlashcardSet.jsp", { setId: $(this).parents().eq(1).attr("data-id"), title: value }, function( data ) {
					$(element).children(".redBorder").html('<a href="/learningscreen.html?setId=' + $(element).parents().eq(1).attr("data-id") + '">' + value + '</a>');
				});
			} else {
				$.post( "/jsp/addFlashcardSet.jsp", { title: value } , function( data ) {
					
					$(element).parents().eq(1).attr("data-id", data.replace("\n", ""));
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
					
		if(confirm('Sollen die Karteikarten wirklich gelöscht werden?')){
			if(($(element).find("add") != undefined) & (!$(element).hasClass("adding")){
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

		$(container).after($(container).clone());
		
		highestId++;
		$(element).append('<textarea id="editor-' + highestId + '" class="paper-textarea"></textarea>');

		$(element).children(".redBorder").hide();
		$(element).parents().eq(1).attr("data-id", highestId).addClass("adding");
		
		$(container).find(".icon").removeClass("hidden");
		tinymce.EditorManager.execCommand('mceAddEditor', true, "editor-" + highestId);
	});
	
	/* Init FlashCard Sets */
	$.getJSON("/jsp/getSetsFromUser.jsp", function(data){
		$.each(data, function (index, value) {
			$("#wrapper").prepend('<div class="paper paper-small paper-dashboard" data-id="' + value.setId + '"><div class="paper-buttons"><a href="#" class="icon remove"><i class="fa fa-minus fa-fw"></i></a><a href="#" class="icon edit"><i class="fa fa-pencil fa-fw"></i></a></div><div class="paper2"><div class="redBorder"><a href="learningscreen.html?setId=' + value.setId + '">' + value.title + '</a></div> <textarea id="editor-' + value.setId + '" class="paper-textarea">' + value.title + '</textarea></div></div>');
		});
		initEditors();
		highestId = getHighestID();
	});

});

/* get highest ID */
function getHighestID(){
	var tempId = 0;
	$(".paper-dashboard").each(function(){
		if(tempId < $(this).attr("data-id")){
			tempId = $(this).attr("data-id");
		}
	});
	return tempId;
}