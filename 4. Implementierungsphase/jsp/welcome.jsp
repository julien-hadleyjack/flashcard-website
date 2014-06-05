<head><title>Welcome</title></head>  
<body>
<br><br><br><br>
<table align="center" style="border:1px solid #000000;">
<%
if(session.getAttribute("username")!=null && session.getAttribute("username")!="")
{
String user = session.getAttribute("username").toString();
%>
<tr><td align="center"><h1>Welcome <b><%= user%></b></h1></td></tr>
<%
}
%>
</table>
</body>
<html>