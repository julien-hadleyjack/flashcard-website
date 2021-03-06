package database;

import java.util.Collection;

import flashcard.FlashcardBean;
import flashcard.FlashcardSetBean;
import user.UserBean;

public interface FlashcardDatabase {

	/**
	 * Adds a user to the database. If a user with the email address from {@link UserBean#getUsername()} already
	 * exists then that user will be changed to match the {@link UserBean}.
	 * 
	 * @param user the user that is created or modified
	 * @return boolean if action was succesfull or not
	 */
	public boolean addUser(UserBean user);
	
	/**
	 * Method to modify a user. For example to edit their email adress. Not implemented in the current project.
	 * 
	 * @param user the user that is created or modified
	 */
	public void modifyUser(UserBean user);
	
	/**
	 * Returns the user if a user with that email address exists in the database.
	 * 
	 * @param user the user that is used to search. only the email address needs to be filled. 
	 * @return the {@link UserBean} if a user exists with that email address;
	 * <code>null</code> otherwise (also when no email address was provided)
	 */
	public UserBean getUser(UserBean user);
	
	
	/**
	 * Removes the user with the corresponding email address from the database. If no user exists nothing will be done.
	 * 
	 * Note: Only the email address is used. If the other information aren't identical, no error will be thrown.
	 * If no email address was provided, nothing will be done.
	 *  
	 * @param user the user that should be deleted
	 * @return boolean if the deletion was successful or not
	 */
	public boolean deleteUser(UserBean user);
	
	/**
	 * Adds an empty flash card set with the specified title to the user.
	 * 
	 * @param user the user for which the set will be created
	 * @param title the title of the newly created flash card set
	 * @return inserted id from flashcardSet
	 */
	public int addFlashcardSet(UserBean user, String title);
	
	/**
	 * Modifies an existing flash card set using the flashcardsSetId.
	 * 
	 * @param flashcardSet the flash card set that should be modified
	 * @param title the new title of the modified flashcardSet
	 */
	public void modifyFlashcardSet(FlashcardSetBean flashcardSet, String title);
	
	/**
	 * Returns all flash card sets for the user but without the flash cards.
	 *  
	 * @param user the user who owns the flash card sets
	 * @return all of the user's flash card sets; <code>null</code> if user doesn't exist or has no sets
	 */
	public Collection<FlashcardSetBean> getFlashcardSetsForUser(UserBean user);
	
	/**
	 * Returns a single flash card set with all of its flash cards.
	 * 
	 * @param flashcardSetId the id of the flash card set
	 * @param user the user who owns this flashcardSet
	 * @return the flash card set containing the flash cards; <code>null</code> if no corresponding flash card set exists
	 */
	public FlashcardSetBean getFlashcardSet(int flashcardSetId, UserBean user);
	
	/**
	 * Deletes the flash card set. If the set doesn't exist in the database then nothing will be done.
	 * 
	 * @param setId the set with the id setId that should be deleted
	 */
	public void deleteFlashcardSet(int setId);
	
	/**
	 * Creates or modifies a flash card in the flash car set. If a flash card already exists in the flash card set
	 * with flashcardId then it will be modified. To add a new flash card specify a flash card without a flashcardId.
	 * If a flash card is specified with a flashcardId that can't be found in the flash card set then nothing will be
	 * done.
	 * 
	 * @param flashcardset the flash card set to which the flash card will either be added to or modified in
	 * @param flashcard the new or modified flash card
	 */
	public int addFlashcard(FlashcardSetBean flashcardset, String question, String answer);
	
	/**
	 * Modifies a flash card in the flash card set.
	 * 
	 * @param flashcardId the id of the flashcard which should be modified
	 * @param question the new question of the flashcard
	 * @param answer the new answer of the flashcard
	 */
	public void modifyFlashcard(String question, String answer, int flashcardId);
	
	/**
	 * Returns a flash card. Because the flashcardId is unique the flash card can only be owned by one flash card set.
	 * 
	 * @param flashcardId the id of the flash card
	 * @return the flash card if flashcardId exists in the database; <code>null</code> otherwise
	 */
	public FlashcardBean getFlashcard(int flashcardId);
	
	/**
	 * Deletes the flash card. If no flash card with the flashcardId exists in the database then nothing will be done.
	 * @param flashcard the flash card that should be deleted
	 */
	public void deleteFlashcard(int flashcardId);
	
	/**
	 * Returns all Flashcards for a FlashcardSet
	 * @param setId the id of the flashcard set from which you want to retrieve all the flashcards
	 * @return a collection of all flashcards from this set; <code>null</code> otherwise
	 */
	public Collection<FlashcardBean> getFlashcardForSetWithId(int setId);
	
	/**
	 * Checks if the given password matches with the encrypted one in the database
	 * @param user the user with the login information
	 * @return boolean if password matched or not
	 */
	public boolean checkPW(UserBean user);
}
