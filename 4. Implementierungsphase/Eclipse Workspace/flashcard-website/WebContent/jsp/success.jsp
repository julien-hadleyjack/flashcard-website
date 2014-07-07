<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
<meta charset="UTF-8">
<link href='http://fonts.googleapis.com/css?family=Megrim'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="../layout/css/layout.css">
<link rel="stylesheet" href="../layout/css/font-awesome.min.css">
</head>


<body>
	<c:choose>
		<c:when test="${empty userid}">
			<div class="redirectHolder">
				<h1>Nicht eingeloggt !</h1>
				<%
					response.setHeader("Refresh", "3;url=../index.jsp");
				%>

				<div class="dots">
					<i class="fa fa-spinner fa-spin"></i>
				</div>
				<div class="redirectMsg">
					<p>Sie werden wieder zum Loginscreen geleitet. Falls die
						Weiterleitung nicht klappen sollte :</p>
					<a href="../index.jsp">klicken Sie hier</a>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="redirectHolder">
				<h1>Login erfolgreich</h1>
				<%
					response.setHeader("Refresh", "3;url=../dashboard.html");
				%>
				<div class="dots">
					<i class="fa fa-spinner fa-spin"></i>
				</div>
				<div class="redirectMsg">
					<p>Falls die Weiterleitung nicht klappen sollte :</p>
					<a href="../dashboard.html">klicken Sie hier</a>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>