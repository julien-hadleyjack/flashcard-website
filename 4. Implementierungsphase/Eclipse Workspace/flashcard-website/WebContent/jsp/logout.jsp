<%-- JSP, um einen Benutzer auszuloggen, Session entfernen --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:remove var="userid" scope="session" />
<c:redirect url="../index.jsp"></c:redirect>