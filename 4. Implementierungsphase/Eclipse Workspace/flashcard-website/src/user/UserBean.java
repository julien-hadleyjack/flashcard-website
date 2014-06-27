package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserBean {

	private String username;
	private String pass;
	private String salt;
	private int id;
	
	public UserBean() {}

	public UserBean(String username, String pass, String salt, int id) {
		this.username = username;
		this.pass = pass;
		this.salt = salt;
		this.id = id;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		if (salt != null && this.username != null) {
			str = PasswordBean.hash(pass, salt);
		} else {

			salt = PasswordBean.getNextSalt();
			str = PasswordBean.hash(pass, salt);
		}

		this.pass = str;
		this.salt = salt;

	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String existingSalt() {
		String dbSalt = null;

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prepStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager
					.getConnection(
							"jdbc:mysql://aa14f3lqw8l60up.cp8slgariplu.eu-west-1.rds.amazonaws.com:3306/web_engineering",
							"david", "7t*Tf##q#dgCT4^07i*#mwb52261snK@");
			prepStatement = con
					.prepareStatement("select * from Users where email= (?)");
			prepStatement.setString(1, username);
			rs = prepStatement.executeQuery();
			while (rs.next()) {
				dbSalt = rs.getString("salt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
		finally {
		    if (rs != null) try { rs.close(); } catch (SQLException ignore) {}  
		    if (prepStatement != null) try { prepStatement.close(); } catch (SQLException ignore) {}  
		    if (con != null) try { con.close(); } catch (SQLException ignore) {}  
		}
		return dbSalt;

	}
	
	public UserBean getThis() {
		return this;
	}
}
