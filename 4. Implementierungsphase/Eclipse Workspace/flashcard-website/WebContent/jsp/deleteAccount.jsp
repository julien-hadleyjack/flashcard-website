<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="loginBean" class="user.UserBean" scope="session"></jsp:useBean>
<jsp:useBean id="database" class="database.MySqlDatabase" scope="session"></jsp:useBean>

<c:set target="${loginBean}" property="username" value="${sessionScope.userid}" />

<c:if test="${database.deleteUser(database.getUser(loginBean))}">
<c:redirect url="logout.jsp"></c:redirect>
</c:if>
