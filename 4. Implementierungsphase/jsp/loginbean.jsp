<%@ page language="Java" import="java.sql.*" %>  
<html> 
<head><title>LoginBean</title></head>  
<body>
<jsp:useBean id="db" scope="request" class="LoginBean" >
  <jsp:setProperty name="db" property="userName" value="<%=request.getParameter("userName")%>"/>
  <jsp:setProperty name="db" property="password" value="<%=request.getParameter("password")%>"/>
 </jsp:useBean>
<jsp:forward page="hello">
  <jsp:param name="username" value="<%=db.getUserName()%>" />
  <jsp:param name="password" value="<%=db.getPassword()%>" />
</jsp:forward> 
</body>
</html>