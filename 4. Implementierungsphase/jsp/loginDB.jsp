<%@ page import ="java.sql.*" %>
<%
    String userid = request.getParameter("uname");    
    String pwd = request.getParameter("password");
    //Entscheiden ob Registrieren oder Login
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_engineering",
            "root", "tennis1506");
    Statement st = con.createStatement();
    ResultSet rs;
    rs = st.executeQuery("select * from Users where email='" + userid + "' and pwhash='" + pwd + "'");
    if (rs.next()) {
        session.setAttribute("userid", userid);
       
        response.sendRedirect("success.jsp");
    } else {
        out.println("Passwort war nicht korrekt! <a href='../login.jsp'>Nochmal versuchen</a>");
    }
%>