function toRegister() {
    var login = document.getElementById("loginId");
    if (login.value === "Login") {
        login.value = "Registrieren";

        document.getElementById("register").innerHTML = "Zurueck zum Login...";
        document.getElementById("formHeadline").innerHTML = "Signupscreen";

        document.loginForm.action = "jsp/registerDB.jsp";

    } else {

        login.value = "Login";

        document.getElementById("register").innerHTML = "Noch keinen Account ?";
        document.getElementById("formHeadline").innerHTML = "Loginscreen";

        document.loginForm.action = "jsp/loginDB.jsp";

    }
}

function setCorrectAction() {
    var login = document.getElementById("loginId");
    if (login.value === "Login") {

        document.loginForm.action = "jsp/loginDB.jsp";

    } else {

        document.loginForm.action = "jsp/registerDB.jsp";

    }
}


function checkForm() {
    document.loginForm.action = "javascript: void(0);";

    var x = document.getElementById("login").value;
    var passField = document.getElementById("password").value;
    var regex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    if (regex.test(x) == false) {
        $.growl.error({
            message: "Email Adresse ist nicht valide"
        });

    }
    else if(!passField || passField.length == 0) {
        $.growl.error({
            message: "Bitte ein Passwort eingeben"
        }); 
    }
    
    /* Registrieren -> Email schon vorhanden ? */
    
    else if(document.getElementById("loginId").value == "Registrieren") {
    	var req = new XMLHttpRequest();
    	req.open("GET", "/jsp/userTaken.jsp?uname="+document.getElementById("login").value, true);
    	req.onreadystatechange = function receive() {
    		if (req.readyState==4) {
    			 if(req.responseText.trim() == "User already taken") {
    	            $.growl.error({
    	                message: "Email ist bereits registriert"
    	            });
    	            
    		}
    		else {
 	            	setCorrectAction();
 	            }
    		}
    	 
    	  };
     
    	  req.send();
      }
    else {
        setCorrectAction();
    }
 
	
    
    /* Login war nicht korrekt, kein Eintrag dazu gefunden */
    
}