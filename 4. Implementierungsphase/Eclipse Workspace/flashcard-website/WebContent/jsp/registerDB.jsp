<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<jsp:useBean id="loginBean" class="beans.UserBean" scope="session"></jsp:useBean>
<%
    String userid = request.getParameter("uname");    
    String pwd = request.getParameter("password"); 
%>
 <jsp:setProperty name="loginBean" property="username"  
                    value="${userid}"/>
 <jsp:setProperty name="loginBean" property="pass"  
                    value="${pwd}"/>
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
