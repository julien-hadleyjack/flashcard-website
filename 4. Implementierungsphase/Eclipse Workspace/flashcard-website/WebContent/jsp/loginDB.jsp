<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String userid = request.getParameter("uname");
%>
<jsp:useBean id="loginBean" class="user.UserBean" scope="session"></jsp:useBean>

<c:set target="${loginBean}" property="username" value="${param.uname}" />
<c:set target="${loginBean}" property="pass" value="${param.password}" />

<c:choose>
	<c:when test="${loginBean.isLoggedIn()}">
		<%
			session.setAttribute("userid", userid);
			response.sendRedirect("success.jsp");
		%>
		<c:out value="correct"></c:out>
	</c:when>
	<c:otherwise>
		<c:out value="incorrect"></c:out>
	</c:otherwise>
</c:choose>

