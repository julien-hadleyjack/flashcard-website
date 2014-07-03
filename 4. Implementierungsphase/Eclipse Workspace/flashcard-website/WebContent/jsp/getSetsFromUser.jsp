<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="database" class="database.MySqlDatabase" scope="session"></jsp:useBean>
<jsp:useBean id="loginBean" class="user.UserBean" scope="session"></jsp:useBean>
<jsp:useBean id="jsonHelper" class="helper.JSONHelper" scope="session"></jsp:useBean>

<c:set target="${loginBean}" property="username" value="${param.userid}" />
<c:out value="${jsonHelper.getJsonFromCollection(database.getFlashcardSetsForUser(database.getUser(loginBean)))}"></c:out>
