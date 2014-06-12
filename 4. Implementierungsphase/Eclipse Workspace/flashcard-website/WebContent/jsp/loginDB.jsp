<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%
    String userid = request.getParameter("uname");    
%>
<jsp:useBean id="loginBean" class="beans.user.UserBean" scope="session"></jsp:useBean>

<c:set target="${loginBean}" property="username" value="${param.uname}"/>    
<c:set target="${loginBean}" property="pass" value="${param.password}"/> 

<c:choose>
<c:when test="${loginBean.isLoggedIn()}">
<%session.setAttribute("userid",userid);        
response.sendRedirect("success.jsp");
%>
</c:when>
<c:otherwise>
 <%out.println("Passwort war nicht korrekt! <a href='../index.jsp'>Nochmal versuchen</a>"); 
 %>
 </c:otherwise>
</c:choose> 

