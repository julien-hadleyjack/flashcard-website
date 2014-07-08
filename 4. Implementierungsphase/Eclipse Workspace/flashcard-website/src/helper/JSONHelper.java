package helper;

import java.util.Collection;

import com.google.gson.Gson;

import flashcard.FlashcardSetBean;


/* Helper Klasse, um Collections als JSON auszugeben */
public class JSONHelper {

	public String getJsonFromCollection(Collection<FlashcardSetBean> flashcardSet) {
		 Gson gson = new Gson();
	     String json = gson.toJson(flashcardSet);
	     return json;
	}
}
