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
    var x = document.getElementById("login").value;
    var passField = document.getElementById("password").value;
    var regex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    setCorrectAction();

    if (regex.test(x) == false) {
        document.loginForm.action = "javascript: void(0);"
        $.growl.error({
            message: "Email Adresse ist nicht valide"
        });

    }
    if(!passField || passField.length == 0) {
       document.loginForm.action = "javascript: void(0);"
        $.growl.error({
            message: "Bitte ein Passwort eingeben"
        }); 
    }
}