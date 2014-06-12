<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<jsp:useBean id="loginBean" class="user.UserBean" scope="session"></jsp:useBean>
<%
    String userid = request.getParameter("uname");   
%>

 <c:set target="${loginBean}" property="username" value="${param.uname}"/>    
 <c:set target="${loginBean}" property="pass" value="${param.password}"/>    
 
<c:choose>
<c:when test="${loginBean.registerUser()}">
<% session.setAttribute("userid",userid);        
response.sendRedirect("success.jsp");
%>
</c:when>
<c:otherwise>
      <%  out.println("Bei der Registrierung gab es einen Fehler. <a href='../index.jsp'>Nochmal versuchen</a>");   
      %>
 </c:otherwise>
</c:choose> 
