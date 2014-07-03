<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="database" class="database.MySqlDatabase" scope="session"></jsp:useBean>
<jsp:useBean id="helper" class="helper.FlashcardHelper" scope="session"></jsp:useBean>
<jsp:useBean id="loginBean" class="user.UserBean" scope="session"></jsp:useBean>

<c:set target="${loginBean}" property="username" value="${sessionScope.userid}"/>
<c:set target="${helper}" property="title" value="${param.title}"/>
${database.addFlashcardSet(database.getUser(loginBean),helper.getTitle())}