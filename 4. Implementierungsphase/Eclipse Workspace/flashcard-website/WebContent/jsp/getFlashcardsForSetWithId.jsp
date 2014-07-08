<%-- JSP, um alle Flashcards von einem Set zu bekommen, Ausgabe als JSON --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"
%><%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"
%><jsp:useBean id="database" class="database.MySqlDatabase" scope="session">
</jsp:useBean><jsp:useBean id="helper" class="helper.FlashcardHelper" scope="session">
</jsp:useBean><jsp:useBean id="jsonHelper" class="helper.JSONHelper" scope="session">
</jsp:useBean><c:set target="${helper}" property="setId" value="${param.setId}"
/><c:out value="${jsonHelper.getJsonFromCollection(database.getFlashcardForSetWithId(helper.getSetId()))}" escapeXml="false">
</c:out>
