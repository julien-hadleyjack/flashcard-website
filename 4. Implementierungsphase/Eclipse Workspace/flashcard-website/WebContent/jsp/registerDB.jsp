<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="loginBean" class="user.UserBean" scope="session"></jsp:useBean>

<c:set target="${loginBean}" property="username" value="${param.uname}" />
<c:set target="${loginBean}" property="pass" value="${param.password}" />

<c:choose>
	<c:when test="${loginBean.registerUser()}">
	<c:set var="userid" value="${param.uname}" scope="session"  />
	<c:redirect url="success.jsp"></c:redirect>
	</c:when>
	<c:otherwise>
	<c:out value="Bei der Registrierung gab es einen Fehler. <a href='../index.jsp'>Nochmal versuchen</a>"></c:out>
	</c:otherwise>
</c:choose>
