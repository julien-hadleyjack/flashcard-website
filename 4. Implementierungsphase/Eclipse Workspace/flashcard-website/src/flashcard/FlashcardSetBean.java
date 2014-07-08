package flashcard;

import java.util.Collection;

import user.UserBean;

/**
 * FlashcardBean
 * hält alle relevanten Daten eines FlashcardSets
 */

public class FlashcardSetBean {

	private int setId;
	
	private String title;
	
	private UserBean owner;
	
	private Collection<FlashcardBean> flashcards;
	
	
	public int getSetId() {
		return setId;
	}
	public String getTitle() {
		return title;
	}
	public UserBean getOwner() {
		return owner;
	}
	public void setOwner(UserBean user) {
		this.owner = user;
	}
	public Collection<FlashcardBean> getFlashcards() {
		return flashcards;
	}
	public void setFlashcards(Collection<FlashcardBean> flashcards) {
		this.flashcards = flashcards;
	}
	public void setSetId(int setId) {
		this.setId = setId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}