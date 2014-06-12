package beans.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserBean {

	private String username;
	private String pass;
	private String salt;

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		String str = null;
		String salt = existingSalt();
			if(salt != null && this.username != null) {
			str = PasswordBean.hash(pass, salt);
			}
			else {
			
			salt = PasswordBean.getNextSalt();
			str = PasswordBean.hash(pass,salt);
			}
		
		
		
			this.pass = str;
			this.salt = salt;

			
		
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public boolean isLoggedIn() {
		    Connection con;
		    ResultSet rs;
			try {
			    Class.forName("com.mysql.jdbc.Driver");

				con = DriverManager.getConnection("jdbc:mysql://aa14f3lqw8l60up.cp8slgariplu.eu-west-1.rds.amazonaws.com:3306/web_engineering",
				        "david", "7t*Tf##q#dgCT4^07i*#mwb52261snK@");
				 Statement st = con.createStatement();
				    rs = st.executeQuery("select * from Users where email='" + username+"'");
				    if (rs.next()) {
					     String pwHash = rs.getNString("pwhash");
					     if(pwHash.equals(this.pass)) {
					    	 return true;
					     }
					} 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return false;
		   
	}
	
	public String existingSalt() {
	    Connection con;
	    ResultSet rs;
		try {
		    Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://aa14f3lqw8l60up.cp8slgariplu.eu-west-1.rds.amazonaws.com:3306/web_engineering",
			        "david", "7t*Tf##q#dgCT4^07i*#mwb52261snK@");
			 Statement st = con.createStatement();
			    rs = st.executeQuery("select * from Users where email='" + username +"'");
			    if (rs.next()) {
				      return rs.getNString("salt");
				} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return null;
	   
}
	
	public boolean registerUser() {
		 Connection con;
		try {
		   Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://aa14f3lqw8l60up.cp8slgariplu.eu-west-1.rds.amazonaws.com:3306/web_engineering",
			            "david", "7t*Tf##q#dgCT4^07i*#mwb52261snK@");
		
		   
		    String queryString = "INSERT INTO Users(email,salt,pwhash) VALUES (?, ?, ?)";
		                  PreparedStatement pstatement = null;
		          int updateQuery = 0;

		              pstatement = con.prepareStatement(queryString);
		              pstatement.setString(1, this.username);
		                          pstatement.setString(2, this.salt);
		                          pstatement.setString(3, this.pass);
		              updateQuery = pstatement.executeUpdate();
		                            if (updateQuery != 0) {
		      return true;
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    return false;
	}
	
}