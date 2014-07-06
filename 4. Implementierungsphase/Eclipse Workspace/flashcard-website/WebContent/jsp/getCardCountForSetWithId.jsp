<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="database" class="database.MySqlDatabase" scope="session"></jsp:useBean>
<jsp:useBean id="helper" class="helper.FlashcardHelper" scope="session"></jsp:useBean>
<c:set target="${helper}" property="setId" value="${param.setId}"/>
<c:set target="${helper}" property="flashcardCollection" value="${database.getFlashcardForSetWithId(helper.getSetId())}"/>
<c:out value="${helper.getCardCount()}"></c:out>