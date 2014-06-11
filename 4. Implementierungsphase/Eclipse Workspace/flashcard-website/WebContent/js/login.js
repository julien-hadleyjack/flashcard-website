function toRegister() {
    if (document.getElementById("loginId").value === "Login") {
        document.getElementById("loginId").value = "Registrieren";

        document.getElementById("register").innerHTML = "Zurueck zum Login...";
        document.getElementById("formHeadline").innerHTML = "Signupscreen";
        
        document.loginForm.action = "jsp/registerDB.jsp";

    } else {

        document.getElementById("loginId").value = "Login";

        document.getElementById("register").innerHTML = "Noch keinen Account ?";
        document.getElementById("formHeadline").innerHTML = "Loginscreen";
        
        document.loginForm.action = "jsp/loginDB.jsp";

    }
}