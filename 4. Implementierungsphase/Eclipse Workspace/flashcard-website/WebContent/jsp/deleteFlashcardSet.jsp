<%-- JSP, um ein Set aus der Datenbank zu löschen --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="database" class="database.MySqlDatabase" scope="session"></jsp:useBean>
<jsp:useBean id="helper" class="helper.FlashcardHelper" scope="session"></jsp:useBean>

<c:set target="${helper}" property="setId" value="${param.setId}"/>

${database.deleteFlashcardSet(helper.getSetId())}