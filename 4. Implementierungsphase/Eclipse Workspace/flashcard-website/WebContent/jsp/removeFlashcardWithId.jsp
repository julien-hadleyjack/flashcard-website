<%-- JSP, um eine Flashcard aus der Datenbank zu entfernen --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="database" class="database.MySqlDatabase" scope="session"></jsp:useBean>
<jsp:useBean id="helper" class="helper.FlashcardHelper" scope="session"></jsp:useBean>

<c:set target="${helper}" property="flashcardId" value="${param.flashcardId}"/>

${database.deleteFlashcard(helper.getFlashcardId())}