<%@ page import ="java.sql.*" %>
<%
    String userid = request.getParameter("uname");    
    String pwd = request.getParameter("password");
    //Entscheiden ob Registrieren oder Login
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_engineering",
            "root", "tennis1506");
   
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