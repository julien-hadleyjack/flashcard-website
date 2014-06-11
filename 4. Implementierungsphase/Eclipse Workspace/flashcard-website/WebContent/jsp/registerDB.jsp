<%@ page import ="java.sql.*" %>
<%
    String userid = request.getParameter("uname");    
    String pwd = request.getParameter("password");
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://aa7gk7un53jie1.cp8slgariplu.eu-west-1.rds.amazonaws.com:3306/web_engineering",
            "david", "7t*Tf##q#dgCT4^07i*#mwb52261snK@");
   
    String queryString = "INSERT INTO Users(email,pwhash,salt) VALUES (?, ?, ?)";
                  PreparedStatement pstatement = null;
          int updateQuery = 0;

              pstatement = con.prepareStatement(queryString);
              pstatement.setString(1, userid);
                          pstatement.setString(2, pwd);
                          pstatement.setString(3, "salz");
              updateQuery = pstatement.executeUpdate();
                            if (updateQuery != 0) {
        session.setAttribute("userid", userid);
        response.sendRedirect("success.jsp");
    } else {
        out.println("Bei der Registrierung gab es einen Fehler. <a href='login.jsp'>Nochmal versuchen</a>");
    }
%>