<%@ page import ="java.sql.*" %>
<%
    String userid = request.getParameter("uname");    
    String pwd = request.getParameter("password");
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://aa7gk7un53jie1.cp8slgariplu.eu-west-1.rds.amazonaws.com:3306/web_engineering",
            "david", "7t*Tf##q#dgCT4^07i*#mwb52261snK@");
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