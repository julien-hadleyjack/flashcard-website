<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="loginBean" class="user.UserBean" scope="session"></jsp:useBean>
<jsp:useBean id="database" class="database.MySqlDatabase" scope="session"></jsp:useBean>

<c:set target="${loginBean}" property="username" value="${param.uname}" />
<c:set target="${loginBean}" property="pass" value="${param.password}" />

<c:choose>
	<c:when test="${database.getUser(loginBean.getThis()) != null && database.checkPW(loginBean.getThis())}">
	    <c:set var="userid" value="${param.uname}" scope="session"  />
		<c:redirect url="success.jsp"></c:redirect>
		
		<c:out value="correct"></c:out>
	</c:when>
	<c:otherwise>
		<c:out value="incorrect"></c:out>
	</c:otherwise>
</c:choose>

