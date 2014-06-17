package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import user.UserBean;
import flashcard.FlashcardBean;
import flashcard.FlashcardSetBean;

public class MySqlDatabase implements FlashcardDatabase {

private static DataSource dataSource = null;
	
	private String databaseUrl = "jdbc:mysql://aa14f3lqw8l60up.cp8slgariplu.eu-west-1.rds.amazonaws.com:3306/web_engineering";
	private String localDatabaseUrl = "jdbc:mysql://localhost:3306/web_engineering";
	
	private boolean useLocalhost = true;

	private Connection getConnection() throws SQLException {
		if (dataSource == null) {
			PoolProperties p = new PoolProperties();
			p.setUrl(useLocalhost ? localDatabaseUrl : databaseUrl);
			p.setDriverClassName("com.mysql.jdbc.Driver");
			p.setUsername("david");
			p.setPassword("7t*Tf##q#dgCT4^07i*#mwb52261snK@");
			p.setJmxEnabled(true);
			p.setTestWhileIdle(false);
			p.setTestOnBorrow(true);
			p.setValidationQuery("SELECT 1");
			p.setTestOnReturn(false);
			p.setValidationInterval(30000);
			p.setTimeBetweenEvictionRunsMillis(30000);
			p.setMaxActive(100);
			p.setInitialSize(10);
			p.setMaxWait(10000);
			p.setRemoveAbandonedTimeout(60);
			p.setMinEvictableIdleTimeMillis(30000);
			p.setMinIdle(10);
			p.setLogAbandoned(true);
			p.setRemoveAbandoned(true);
			p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
					"org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
			dataSource = new DataSource();
			dataSource.setPoolProperties(p); 
		}

		return dataSource.getConnection();
	}

	@Override
	public void addUser(UserBean user) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void modifyUser(UserBean user) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserBean getUser(UserBean user) {
		PreparedStatement prepStatement;
		Connection connection = null;
		
		try {
			connection = getConnection(); 
			prepStatement = connection.prepareStatement("select * from Users");
			ResultSet rs = prepStatement.executeQuery();
			if (rs.next()) {
				return new UserBean("", rs.getString("pwhash"), "");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection!=null) try {connection.close();}catch (Exception ignore) {}
		}
		return null;

	}

	@Override
	public void deleteUser(UserBean user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addFlashcardSet(UserBean user, String title) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyFlashcardSet(FlashcardSetBean flashcardSet) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<FlashcardSetBean> getFlashcardSetsForUser(UserBean user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlashcardSetBean getFlashcardSet(String flashcardSetId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteFlashcardSet(FlashcardSetBean flashcardSet) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addFlashcard(FlashcardSetBean flashcardset,
			FlashcardBean flashcard) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void modifyFlashcard(FlashcardBean flashcard) {
		// TODO Auto-generated method stub
	}	
		

	@Override
	public FlashcardBean getFlashcard(String flashcardId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteFlashcard(FlashcardBean flashcard) {
		// TODO Auto-generated method stub

	}


}
