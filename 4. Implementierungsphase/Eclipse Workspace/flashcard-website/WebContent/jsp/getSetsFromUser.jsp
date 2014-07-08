<%-- JSP, um alle Sets eines Benutzer zu bekommen (Ausgabe als JSON) --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"
%><%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"
%><jsp:useBean id="database" class="database.MySqlDatabase" scope="session">
</jsp:useBean><jsp:useBean id="loginBean" class="user.UserBean" scope="session">
</jsp:useBean><jsp:useBean id="jsonHelper" class="helper.JSONHelper" scope="session">
</jsp:useBean><c:set target="${loginBean}" property="username" value="${sessionScope.userid}"
/><c:out value="${jsonHelper.getJsonFromCollection(database.getFlashcardSetsForUser(database.getUser(loginBean)))}" escapeXml="false">
</c:out>
