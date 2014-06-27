<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<jsp:useBean id="loginBean" class="user.UserBean" scope="session"></jsp:useBean>
<jsp:useBean id="database" class="database.MySqlDatabase" scope="session"></jsp:useBean>

<c:set target="${loginBean}" property="username" value="${param.uname}"/>    
<c:choose>
<c:when test="${database.getUser(loginBean.getThis()) != null}">
<c:out value="User already taken"></c:out>
</c:when>
<c:otherwise>
<c:out value="User not taken"></c:out>
</c:otherwise>
</c:choose> 

