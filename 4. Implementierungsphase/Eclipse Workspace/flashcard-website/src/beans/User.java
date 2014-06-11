package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class User {

	private String username;
	private String pass;
	
	public String getUsername() {
		return username;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean isLoggedIn() {
		    Connection con;
		    ResultSet rs;
			try {
				con = DriverManager.getConnection("jdbc:mysql://aa14f3lqw8l60up.cp8slgariplu.eu-west-1.rds.amazonaws.com:3306/web_engineering",
				        "david", "7t*Tf##q#dgCT4^07i*#mwb52261snK@");
				 Statement st = con.createStatement();
				    rs = st.executeQuery("select * from Users where email='" + username + "' and pwhash='" + pass + "'");
				    if (rs.next()) {
					      return true;
					} 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		   
	}
	
	public boolean registerUser() {
		 Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql:/aa14f3lqw8l60up.cp8slgariplu.eu-west-1.rds.amazonaws.com:3306/web_engineering",
			            "david", "7t*Tf##q#dgCT4^07i*#mwb52261snK@");
		
		   
		    String queryString = "INSERT INTO Users(email,pwhash,salt) VALUES (?, ?, ?)";
		                  PreparedStatement pstatement = null;
		          int updateQuery = 0;

		              pstatement = con.prepareStatement(queryString);
		              pstatement.setString(1, username);
		                          pstatement.setString(2, pass);
		                          pstatement.setString(3, "salz");
		              updateQuery = pstatement.executeUpdate();
		                            if (updateQuery != 0) {
		      return true;
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    return false;
	}
	
}
