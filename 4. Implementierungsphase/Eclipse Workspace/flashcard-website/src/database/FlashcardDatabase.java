package database;

import java.util.Collection;

import flashcard.FlashcardBean;
import flashcard.FlashcardSetBean;
import user.UserBean;

public interface FlashcardDatabase {

	public void addModifyUser(UserBean user);
	
	public UserBean getUser(String username, String email);
	
	public void deleteUser(UserBean user);
	
	public void addModifyFlashcardSet(UserBean user);
	
	public Collection<FlashcardSetBean> getFlashcardSetsForUser(UserBean user);
	
	public FlashcardSetBean getFlashcardSet(String flashcardSetId);
	
	public void deleteFlashcardSet(FlashcardSetBean flashcardSet);
	
	public void addModifyFlashcard(FlashcardSetBean flashcardset, FlashcardBean flashcard);
	
	public FlashcardBean getFlashcard(String flashcardId);
	
	public void deleteFlashcard(FlashcardBean flashcard);
}
