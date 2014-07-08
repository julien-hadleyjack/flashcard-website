<%-- JSP, um ein Set zur Datenbank hinzuzufügen, eine intiale Flashcard wird erstellt --%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="database" class="database.MySqlDatabase" scope="session"></jsp:useBean>
<jsp:useBean id="helper" class="helper.FlashcardHelper" scope="session"></jsp:useBean>
<jsp:useBean id="loginBean" class="user.UserBean" scope="session"></jsp:useBean>

<c:set target="${loginBean}" property="username" value="${sessionScope.userid}"/>
<c:set target="${helper}" property="title" value="${param.title}"/>
${helper.setSetId(database.addFlashcardSet(database.getUser(loginBean),helper.getTitle()))}
<c:out value="${helper.getSetId()}"></c:out>
<c:set target="${helper}" property="question" value="Klicke auf Antwort anzeigen und anschließend auf den Stift oder drücke E um die Karte zu editieren."/>
<c:set target="${helper}" property="answer" value="Klicke auf den Stift rechts oben um die Karte zu bearbeiten."/>
<c:set target="${helper}" property="flashcardId" value="${database.addFlashcard(database.getFlashcardSet(helper.getSetId(),database.getUser(loginBean)),helper.getQuestion(), helper.getAnswer())}"/>