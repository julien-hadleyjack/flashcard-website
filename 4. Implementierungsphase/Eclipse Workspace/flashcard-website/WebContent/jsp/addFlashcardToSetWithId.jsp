<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="database" class="database.MySqlDatabase" scope="session"></jsp:useBean>
<jsp:useBean id="helper" class="helper.FlashcardHelper" scope="session"></jsp:useBean>
<jsp:useBean id="loginBean" class="user.UserBean" scope="session"></jsp:useBean>

<c:set target="${loginBean}" property="username" value="${sessionScope.userid}"/>
<c:set target="${helper}" property="setId" value="${param.setId}"/>
<c:set target="${helper}" property="question" value="${param.question}"/>
<c:set target="${helper}" property="answer" value="${param.answer}"/>

${helper.setFlashcardId(database.addFlashcard(database.getFlashcardSet(helper.getSetId(),database.getUser(loginBean)),helper.getQuestion(), helper.getAnswer()))}
<c:out value="${helper.getFlashcardId()}"></c:out>
