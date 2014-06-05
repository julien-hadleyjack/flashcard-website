import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;

public class Login extends HttpServlet{ 
 public void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServletException,IOException{
  response.setContentType("text/html");
  PrintWriter out = response.getWriter();
  System.out.println("MySQL Connect Example.");
  Connection conn = null;
  String url = "jdbc:mysql://https://hadleyjack.com:3306/web_engineering?user=david&password=7t*Tf##q#dgCT4^07i*#mwb52261snK@";
  String dbName = "web_engineering";
  String driver = "com.mysql.jdbc.Driver";
  String userName = "david"; 
  String password = "7t*Tf##q#dgCT4^07i*#mwb52261snK@";
 String username="";
 String userpass="";
 String strQuery= ""; 
  Statement st=null;
  ResultSet rs=null;
  HttpSession session = request.getSession(true);
  try {
  Class.forName(driver).newInstance();
  conn = DriverManager.getConnection(url+dbName,userName,password);
  if(request.getParameter("username")!=null &&
     request.getParameter("username")!="" && request.getParameter("password")!=null &&
     request.getParameter("password")!="")
  {
  username = request.getParameter("username").toString();
  userpass = request.getParameter("password").toString();
      //Hier noch hashen und vorher salt holen
  strQuery="select * from Users where email='"+username+"' and salted_hash='"+userpass+"'";
 System.out.println(strQuery);
  st = conn.createStatement();
  rs = st.executeQuery(strQuery);
  int count=0;
  while(rs.next())
  {
  session.setAttribute("username",rs.getString(2));
  count++;
  }
  if(count>0)
  {
  response.sendRedirect("welcome.jsp");
  }
  else
  {
 response.sendRedirect("login.html");
  }
  }
  else
  {
 response.sendRedirect("login.html");
  }
  System.out.println("Connected to the database"); 
  conn.close();
  System.out.println("Disconnected from database");
  } catch (Exception e) {
  e.printStackTrace();
  }
  }
}