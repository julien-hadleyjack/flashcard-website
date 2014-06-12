package flashcard;

import java.util.Date;

public class FlashcardBean {

	private int flashcardId;
	
	private String question;
	
	private String answer;

	private Date correctAnswerTime;
	
	private int correctAnswerTimes;
	
	private int falseAnswerTimes;
	
	
	public String getQuestion() {
		return question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public void setAnswerChoice(boolean answerChoice) {
		
	}
	
}
