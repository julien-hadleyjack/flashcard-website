<html>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="layout/css/layout.css">
    <link rel="stylesheet" href="layout/css/font-awesome.min.css">
    
    <script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="js/tinymce/tinymce.min.js"></script>
    
    <script type="text/javascript">
    	function initEditors(){
			tinymce.init({
			    selector: "textarea",
			    theme: "modern",
			    toolbar: false,
			    menubar: false,
			    statusbar: false,
			    setup: function(ed) {
	        		ed.on('init', function(e) {
	            		e.target.hide();
	        		});
	    		},
	    		height: 134,
	    		width: 309,
	       		force_p_newlines : false,
	       		content_css : "layout/css/tinymce.css"
			});
		}
	</script>


    <script type="text/javascript">
    
    	$(document).ready(function() {    		
    		var highestId = 5; // TODO: get ID from database!
    		initEditors();
    		
			$(document).on("click", ".edit", function(e){
				e.preventDefault();
				var element = $(this).parents().eq(1).children(".paper2");

				$(element).children(".redBorder").hide();
				$(element).children(".mce-container").show();
								
				$(this).html('<i class="fa fa-save fa-fw"></i>');
				$(this).removeClass("edit");
				$(this).addClass("save");
			});
			
			$(document).on("click", ".save", function(e) {
				e.preventDefault();
				var element = $(this).parents().eq(1).children(".paper2"),
					value = $(element).find("iframe").contents().find("body").text();
					
				if(!value || 0 === value.length) {
					alert("Bitte gebe einen Titel ein!");
				} else {
					$(element).children(".mce-container").hide();
					$(element).children(".redBorder").show();
					
					$(element).children(".redBorder").html(value);
					//TODO: send Ajax Request with new title (get id of database entry via data-id attribute)
					//TODO: if no id set -> add instead of edit 
					
					$(this).html('<i class="fa fa-pencil fa-fw"></i>');
					$(this).removeClass("save");
					$(this).addClass("edit");
				}
			});		
			
			$(document).on("click", ".remove", function(e) {
				e.preventDefault();
				var element = $(this).parents().eq(1);
							
				if(confirm('Sollen die Karteikarten wirklich gelöscht werden?')){
					//TODO: send Ajax Request to remove cards
					$(element).remove();
				}
			});	
			
			/* Add */
			$(document).on("click", ".add", function(e){
				e.preventDefault();
				var element = $(this).parents().eq(2).children(".paper2"),
					container = $(element).parent();
				console.log($(container));
				$(container).after($(container).clone());
				
				highestId++;
				$(element).append('<textarea id="editor-' + highestId + '" class="paper-textarea"></textarea>');
				initEditors();

				$(element).children(".redBorder").hide();
				$(element).children(".mce-container").show();
								
				$(container).find(".icon").removeClass("hidden");
			});
	
		});
    </script>
</head>

<body class="dashboard">
	<header>
		<div class="menubar">
			<div class="menubar-container">
				<ul class="menubar-nav">
					<li class="active"><a href="javaScript:void(0);">Home</a></li>
					<li><a href="javaScript:void(0);">Einstellungen</a></li>
					<li><a href="jsp/logout.jsp">Ausloggen</a></li>
				
				</ul>
			</div>
		</div>    
	</header>

    <div id="wrapper">
        <div class="paper paper-small paper-dashboard">
        	<div class="paper-buttons">
        		<a href="#" class="icon remove">
        			<i class="fa fa-minus fa-fw"></i>
        		</a>
        		<a href="#" class="icon edit">
        			<i class="fa fa-pencil fa-fw"></i>
        		</a>
        	</div>
            <div class="paper2">
                <div class="redBorder">
					Biologie Kurs Mustermann 2014   
				</div> 
				<textarea id="editor-0" class="paper-textarea">Biologie Kurs Mustermann 2014</textarea>         
			</div>
        </div>
        <div class="paper paper-small paper-dashboard">
        	<div class="paper-buttons">
        		<a href="#" class="icon remove">
        			<i class="fa fa-minus fa-fw"></i>
        		</a>
        		<a href="#" class="icon edit">
        			<i class="fa fa-pencil fa-fw"></i>
        		</a>
        	</div>
            <div class="paper2">
                <div class="redBorder">
					Biologie      
				</div>     
				<textarea id="editor-1" class="paper-textarea">Biologie</textarea>         
			</div>
        </div>
        <div class="paper paper-small paper-dashboard">
        	<div class="paper-buttons">
        		<a href="#" class="icon remove">
        			<i class="fa fa-minus fa-fw"></i>
        		</a>
        		<a href="#" class="icon edit">
        			<i class="fa fa-pencil fa-fw"></i>
        		</a>
        	</div>
            <div class="paper2">
                <div class="redBorder">
					Biologie      
				</div>     
				<textarea id="editor-2" class="paper-textarea">Biologie</textarea>         
			</div>
        </div>
        <div class="paper paper-small paper-dashboard">
        	<div class="paper-buttons">
        		<a href="#" class="icon remove">
        			<i class="fa fa-minus fa-fw"></i>
        		</a>
        		<a href="#" class="icon edit">
        			<i class="fa fa-pencil fa-fw"></i>
        		</a>
        	</div>
            <div class="paper2">
                <div class="redBorder">
					Biologie      
				</div>     
				<textarea id="editor-3" class="paper-textarea">Biologie</textarea>         
			</div>
        </div>
        <div class="paper paper-small paper-dashboard">
        	<div class="paper-buttons">
        		<a href="#" class="icon remove">
        			<i class="fa fa-minus fa-fw"></i>
        		</a>
        		<a href="#" class="icon edit">
        			<i class="fa fa-pencil fa-fw"></i>
        		</a>
        	</div>
            <div class="paper2">
                <div class="redBorder">
					Biologie      
				</div>     
				<textarea id="editor-4" class="paper-textarea">Biologie</textarea>         
			</div>
        </div>
        <div class="paper paper-small paper-dashboard">
        	<div class="paper-buttons">
        		<a href="#" class="icon remove">
        			<i class="fa fa-minus fa-fw"></i>
        		</a>
        		<a href="#" class="icon edit">
        			<i class="fa fa-pencil fa-fw"></i>
        		</a>
        	</div>
            <div class="paper2">
                <div class="redBorder">
					Biologie      
				</div>     
				<textarea id="editor-5" class="paper-textarea">Biologie</textarea>         
			</div>
        </div>
        
        <!-- Add -->
        <div class="paper paper-small paper-dashboard">
        	<div class="paper-buttons">
        		<a href="#" class="icon remove hidden">
        			<i class="fa fa-minus fa-fw"></i>
        		</a>
        		<a href="#" class="icon save hidden">
        			<i class="fa fa-save fa-fw"></i>
        		</a>
        	</div>
            <div class="paper2">
                <div class="redBorder">
                	<a href="#" class="icon add">
						<i class="fa fa-plus fa-5x"></i>
					</a>
				</div>     
				         
			</div>
        </div>

    </div>
    <div class="clear" style="clear:both;"></div>
</body>

</html>