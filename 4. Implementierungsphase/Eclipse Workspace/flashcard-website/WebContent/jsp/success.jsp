<%
    if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
%>
Du bist nicht mehr eingeloggt !<br/>
<a href="../login.html">Einloggen</a>
<%} else {
%>
Wilkommen ! <%=session.getAttribute("userid")%>
<a href='logout.jsp'>Ausloggen</a>
<p>oder</p><a href='../dashboard.html'>Weiter zum Dashboard...</a>
<%
    }
%>