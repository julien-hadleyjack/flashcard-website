<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%
    String userid = request.getParameter("uname");    
    String pwd = request.getParameter("password"); 
%>
<jsp:useBean id="loginBean" class="beans.User" scope="session"></jsp:useBean>
 <jsp:setProperty name="loginBean" property="username"  
                    value="${userid}"/>
 <jsp:setProperty name="loginBean" property="pass"  
                    value="${pwd}"/>
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

