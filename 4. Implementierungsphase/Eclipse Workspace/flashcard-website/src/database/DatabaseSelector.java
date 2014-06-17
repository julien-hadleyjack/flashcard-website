package database;

public class DatabaseSelector {

	public static FlashcardDatabase getCurrentDatabase() {
		return new MySqlDatabase();
	}
}
