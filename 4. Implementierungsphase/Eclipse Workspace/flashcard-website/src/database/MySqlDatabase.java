package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import user.UserBean;
import flashcard.FlashcardBean;
import flashcard.FlashcardSetBean;

/* Kommentare zu einzelnen Methoden siehe FlashcardDatabase */

public class MySqlDatabase implements FlashcardDatabase {

private static DataSource dataSource = null;
	
	private static String LOCAL_DATABASE_URL = "jdbc:mysql://localhost:3306/web_engineering";
	
	private final static Logger LOGGER = Logger.getLogger(MySqlDatabase.class.getName());
	
	private static boolean USE_LOCALHOST = false;

	private Connection getConnection() throws SQLException {
		if (dataSource == null) {
			PoolProperties p = new PoolProperties();
			p.setDriverClassName("com.mysql.jdbc.Driver");
			p.setUrl(USE_LOCALHOST ? LOCAL_DATABASE_URL : System.getProperty("JDBC_CONNECTION_STRING"));
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

			LOGGER.info("Database url: " +  p.getUrl());
			dataSource = new DataSource();
			dataSource.setPoolProperties(p); 
		}

		return dataSource.getConnection();
	}

	@Override
	public boolean addUser(UserBean user) {
		PreparedStatement prepStatement;
		Connection connection = null;
		
		try {
			connection = getConnection(); 
		String queryString = "INSERT INTO Users(email,salt,pwhash) VALUES (?, ?, ?)";
		int updateQuery = 0;

		prepStatement = connection.prepareStatement(queryString);
		prepStatement.setString(1, user.getUsername());
		prepStatement.setString(2, user.getSalt());
		prepStatement.setString(3, user.getPass());
		updateQuery = prepStatement.executeUpdate();
		if (updateQuery != 0) {
			return true;
		}
		
	}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			if (connection!=null) try {connection.close();}catch (Exception ignore) {}
		}
			System.out.println("Hinzufügen nicht erfolgreich");
			return false;
	}
	
	@Override
	public void modifyUser(UserBean user) {
		// TODO Auto-generated method stub

	}
	
	public boolean checkPW(UserBean user) {
		PreparedStatement prepStatement;
		Connection connection = null;
		
		try {
			connection = getConnection(); 
			prepStatement = connection.prepareStatement("select * from Users where email= (?)");
			prepStatement.setString(1,user.getUsername());
			ResultSet rs = prepStatement.executeQuery();
			if (rs.next()) {
				if(user.getPass().equals(rs.getString("pwhash"))) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection!=null) try {connection.close();}catch (Exception ignore) {}
		}
		return false;
		
	}

	@Override
	public UserBean getUser(UserBean user) {
		PreparedStatement prepStatement;
		Connection connection = null;
		
		try {
			connection = getConnection(); 
			prepStatement = connection.prepareStatement("select * from Users where email= (?)");
			prepStatement.setString(1,user.getUsername());
			ResultSet rs = prepStatement.executeQuery();
			if (rs.next()) {
				return new UserBean(rs.getString("email"), rs.getString("pwhash"), rs.getString("salt"),rs.getInt("userId"));
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
	public boolean deleteUser(UserBean user) {
		PreparedStatement prepStatement;
		Connection connection = null;
		
		try {
			connection = getConnection(); 
		String queryString = "DELETE FROM Users WHERE email= (?)";
		int updateQuery = 0;

		prepStatement = connection.prepareStatement(queryString);
		prepStatement.setString(1, user.getUsername());
		updateQuery = prepStatement.executeUpdate();
		if (updateQuery != 0) {
			return true;
		}
		else {
			System.out.println("Lšschen nicht erfolgreich");
			return false;
		} 
	}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			if (connection!=null) try {connection.close();}catch (Exception ignore) {}
		}
		return false;
	}

	@Override
	public int addFlashcardSet(UserBean user, String title) {
		PreparedStatement prepStatement;
		Connection connection = null;
		
		try {
			connection = getConnection(); 
		String queryString = "INSERT INTO FlashcardSets(ownerId,title) VALUES (?, ?)";
		int updateQuery = 0;

		prepStatement = connection.prepareStatement(queryString,Statement.RETURN_GENERATED_KEYS);
		prepStatement.setInt(1, user.getId());
		prepStatement.setString(2, title);
		updateQuery = prepStatement.executeUpdate();
		 ResultSet keyResultSet = prepStatement.getGeneratedKeys();
	        int newCustomerId = 0;
	        if (keyResultSet.next()) {
	            newCustomerId = (int) keyResultSet.getInt(1);
	        }
         
		return newCustomerId;
	}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			if (connection!=null) try {connection.close();}catch (Exception ignore) {}
		}
		return 0;
	}

	@Override
	public void modifyFlashcardSet(FlashcardSetBean flashcardSet,String title) {
		PreparedStatement prepStatement;
		Connection connection = null;
		
		try {
			connection = getConnection(); 
		String queryString = "UPDATE FlashcardSets SET title= (?) WHERE flashcardSetId= (?)";
		int updateQuery = 0;

		prepStatement = connection.prepareStatement(queryString);
		prepStatement.setString(1,title);
		prepStatement.setInt(2, flashcardSet.getSetId());
		updateQuery = prepStatement.executeUpdate();
		if (updateQuery != 0) {
			return;
		}
		else {
			System.out.println("Titeländerungen nicht erfolgreich");
			return;
		}
	}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			if (connection!=null) try {connection.close();}catch (Exception ignore) {}
		}

	}

	@Override
	public Collection<FlashcardSetBean> getFlashcardSetsForUser(UserBean user) {
		PreparedStatement prepStatement;
		Connection connection = null;
		Collection<FlashcardSetBean> sets = new ArrayList<FlashcardSetBean>();

		
		try {
			connection = getConnection(); 
			prepStatement = connection.prepareStatement("select * from FlashcardSets where ownerId= (?)");
			prepStatement.setInt(1,user.getId());
			ResultSet rs = prepStatement.executeQuery();
			while (rs.next()) {
				FlashcardSetBean set = new FlashcardSetBean();
				set.setTitle(rs.getString("title"));
				set.setSetId(rs.getInt("flashcardSetId"));
				set.setOwner(user);
				set.setFlashcards(this.getFlashcardForSetWithId(rs.getInt("flashcardSetId")));
				sets.add(set);
			}
			return sets;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection!=null) try {connection.close();}catch (Exception ignore) {}
		}
		return null;
	}

	@Override
	public Collection<FlashcardBean> getFlashcardForSetWithId(int setId) {
		PreparedStatement prepStatement;
		Connection connection = null;
		Collection<FlashcardBean> flashcards = new ArrayList<FlashcardBean>();

		
		try {
			connection = getConnection(); 
			prepStatement = connection.prepareStatement("select * from Flashcards where flashcardSetId= (?)");
			prepStatement.setInt(1,setId);
			ResultSet rs = prepStatement.executeQuery();
			while (rs.next()) {
				FlashcardBean flashcard = new FlashcardBean();
				flashcard.setAnswer(rs.getString("answer"));
				flashcard.setQuestion(rs.getString("question"));
				flashcard.setFlashcardId(rs.getInt("flashcardId"));
				flashcard.setCorrectAnswerTime(rs.getTimestamp("correctAnswerDate"));
				flashcard.setCorrectAnswerTimes(rs.getInt("correctAnswerTimes"));
				flashcard.setFalseAnswerTimes(rs.getInt("falseAnswerTimes"));
				flashcards.add(flashcard);
			}
			return flashcards;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection!=null) try {connection.close();}catch (Exception ignore) {}
		}
		return null;
	}
	
	@Override
	public FlashcardSetBean getFlashcardSet(int flashcardSetId, UserBean user) {
		PreparedStatement prepStatement;
		Connection connection = null;
		try {
			connection = getConnection(); 
			prepStatement = connection.prepareStatement("select * from FlashcardSets where flashcardSetId= (?) and ownerId= (?)");
			prepStatement.setInt(1,flashcardSetId);
			prepStatement.setInt(2,user.getId());
			ResultSet rs = prepStatement.executeQuery();
			if (rs.next()) {
				FlashcardSetBean set = new FlashcardSetBean();
				set.setTitle(rs.getString("title"));
				set.setSetId(rs.getInt("flashcardSetId"));
				set.setOwner(user);
				set.setFlashcards(this.getFlashcardForSetWithId(rs.getInt("flashcardSetId")));
				return set;
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
	public void deleteFlashcardSet(int setId) {
		PreparedStatement prepStatement;
		Connection connection = null;
		
		try {
			connection = getConnection(); 
		String queryString = "DELETE FROM FlashcardSets WHERE flashcardSetId= (?)";
		int updateQuery = 0;

		prepStatement = connection.prepareStatement(queryString);
		prepStatement.setInt(1, setId);
		updateQuery = prepStatement.executeUpdate();
		if (updateQuery != 0) {
			return;
		}
		else {
			System.out.println("Lšschen nicht erfolgreich");
			return;
		}
	}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			if (connection!=null) try {connection.close();}catch (Exception ignore) {}
		}

	}

	@Override
	public int addFlashcard(FlashcardSetBean flashcardset,
			String question, String answer) {
		PreparedStatement prepStatement;
		Connection connection = null;
		
		try {
			connection = getConnection(); 
		String queryString = "INSERT INTO Flashcards(flashcardSetId,question,answer) VALUES (?, ?, ?)";
		int updateQuery = 0;

		prepStatement = connection.prepareStatement(queryString,Statement.RETURN_GENERATED_KEYS);
		prepStatement.setInt(1, flashcardset.getSetId());
		prepStatement.setString(2, question);
		prepStatement.setString(3,answer);
	   
		updateQuery = prepStatement.executeUpdate();
		if (updateQuery != 0) {
			 ResultSet keyResultSet = prepStatement.getGeneratedKeys();
		        int newCustomerId = 0;
		        if (keyResultSet.next()) {
		            newCustomerId = (int) keyResultSet.getInt(1);
		        }
	         
			return newCustomerId;
		}
		else {
			System.out.println("HinzufŸgen nicht erfolgreich");
			return -1;
		}
	}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			if (connection!=null) try {connection.close();}catch (Exception ignore) {}
		}
		
		return -1;

	}
	
	@Override
	public void modifyFlashcard(String question, String answer, int flashcardId) {
		PreparedStatement prepStatement;
		Connection connection = null;
		
		try {
			connection = getConnection(); 
		String queryString = "UPDATE Flashcards SET question= (?), answer=(?) WHERE flashcardId= (?)";
		int updateQuery = 0;

		prepStatement = connection.prepareStatement(queryString);
		prepStatement.setString(1,question);
		prepStatement.setString(2,answer);
		prepStatement.setInt(3,flashcardId);
		updateQuery = prepStatement.executeUpdate();
		if (updateQuery != 0) {
			return;
		}
		else {
			System.out.println("Änderung nicht erfolgreich");
			return;
		}
	}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			if (connection!=null) try {connection.close();}catch (Exception ignore) {}
		}

	}	
		

	@Override
	public FlashcardBean getFlashcard(int flashcardId) {
		PreparedStatement prepStatement;
		Connection connection = null;		
		try {
			connection = getConnection(); 
			prepStatement = connection.prepareStatement("select * from Flashcards where flashcardId= (?)");
			prepStatement.setInt(1,flashcardId);
			ResultSet rs = prepStatement.executeQuery();
			if (rs.next()) {
				FlashcardBean flashcard = new FlashcardBean();
				flashcard.setAnswer(rs.getString("answer"));
				flashcard.setQuestion(rs.getString("question"));
				flashcard.setFlashcardId(rs.getInt("flashcardId"));
				flashcard.setCorrectAnswerTime(rs.getTimestamp("correctAnswerDate"));
				flashcard.setCorrectAnswerTimes(rs.getInt("correctAnswerTimes"));
				flashcard.setFalseAnswerTimes(rs.getInt("falseAnswerTimes"));
				return flashcard;
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
	public void deleteFlashcard(int flashcardId) {
		PreparedStatement prepStatement;
		Connection connection = null;
		
		try {
			connection = getConnection(); 
		String queryString = "DELETE FROM Flashcards WHERE flashcardId= (?)";
		int updateQuery = 0;

		prepStatement = connection.prepareStatement(queryString);
		prepStatement.setInt(1, flashcardId);
		updateQuery = prepStatement.executeUpdate();
		if (updateQuery != 0) {
			return;
		}
		else {
			System.out.println("Lšschen nicht erfolgreich");
			return;
		}
	}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			if (connection!=null) try {connection.close();}catch (Exception ignore) {}
		}

	}


}
