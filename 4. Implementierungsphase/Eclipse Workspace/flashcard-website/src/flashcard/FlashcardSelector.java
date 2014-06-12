package flashcard;

import java.util.Collection;

public interface FlashcardSelector {
	
	public Collection<FlashcardBean> selectCards(Collection<FlashcardBean> allCards);

}
