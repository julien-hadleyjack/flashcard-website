package database;

import java.util.Collection;

import user.UserBean;
import flashcard.FlashcardBean;
import flashcard.FlashcardSetBean;

public class MySqlDatabase implements FlashcardDatabase {

	@Override
	public void addModifyUser(UserBean user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserBean getUser(String username, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(UserBean user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addModifyFlashcardSet(UserBean user) {
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
	public void addModifyFlashcard(FlashcardSetBean flashcardset,
			FlashcardBean flashcard) {
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
