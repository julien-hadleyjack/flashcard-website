<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="UTF-8">
	<title>FlashCard Website - Login</title>

    <link rel="stylesheet" href="layout/css/layout.css">
    <link href='http://fonts.googleapis.com/css?family=Megrim' rel='stylesheet' type='text/css'>
	<link href="layout/css/jquery.growl.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
	<script src="js/jquery.growl.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/login.js"></script>
 
</head>

<body>
<header>
		<div class="menubar">
			<div class="menubar-container">
				<ul class="menubar-nav">
					<li class="active"><a href="javascript:void(0);">Home</a></li>
				
					<li><a href="help.html">Home</a></li>
					<li><a href="impressum.html">Ausloggen</a></li>
				</ul>
			</div>
		</div>    
	</header>
<c:if test="${not empty userid}">
<% response.sendRedirect("dashboard.jsp");
%>
</c:if>
	<div class="login-holder">
	    <h1>StudyFlash</h1>
	    <form id="loginForm" name="loginForm" class="loginForm" method="post" action="javascript:void(0);" onsubmit="return checkForm()">
	        <label id="formHeadline" class="loginLabel">Loginscreen</label>
	            <input type="text" id="login" name="uname" placeholder="Username" spellcheck="false">
	            <input type="password" name="password" id="password" placeholder="Password" spellcheck="false">
	            <input type="submit" id="loginId" class="loginButton" name="Login" value="Login">
	    </form>
	    <a href="#" id="register" class="register" onclick="toRegister();return false;">Noch keinen Account ?</a>
  	</div>
</body>

</html>