package helper;

import java.util.Collection;

import flashcard.FlashcardBean;

/* Helper Klasse, um Rückgabewerte der Datenbank zu speichern, und somit in der JSP per
 * Getter & Setter darauf zuzugreifen
 */
public class FlashcardHelper {
	String uname;
	String title;
	int setId;
	int flashcardId;
	int cardCount;
	Collection<FlashcardBean> flashcardCollection;
	String question;
	String answer;
	FlashcardBean flashcard;
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSetId() {
		return setId;
	}
	public void setSetId(int setId) {
		this.setId = setId;
	}
	public int getFlashcardId() {
		return flashcardId;
	}
	public void setFlashcardId(int flashcardId) {
		this.flashcardId = flashcardId;
	}
	public int getCardCount() {
		return flashcardCollection.size();
	}
	public void setCardCount(int cardCount) {
		this.cardCount = cardCount;
	}
	public void setFlashcardCollection(Collection<FlashcardBean> flashcardCollection) {
		this.flashcardCollection = flashcardCollection;
	}
	public Collection<FlashcardBean> getFlashcardCollection() {
		return flashcardCollection;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
