package flashcard;

import java.util.Date;

public class FlashcardBean {

	private int flashcardId;
	
	private String question;
	
	private String answer;

	private Date correctAnswerTime;
	
	

	private int correctAnswerTimes;
	
	private int falseAnswerTimes;
	
	public int getFlashcardId() {
		return flashcardId;
	}
	public void setFlashcardId(int flashcardId) {
		this.flashcardId = flashcardId;
	}
	public Date getCorrectAnswerTime() {
		return correctAnswerTime;
	}
	public void setCorrectAnswerTime(Date correctAnswerTime) {
		this.correctAnswerTime = correctAnswerTime;
	}
	public int getCorrectAnswerTimes() {
		return correctAnswerTimes;
	}
	public void setCorrectAnswerTimes(int correctAnswerTimes) {
		this.correctAnswerTimes = correctAnswerTimes;
	}
	public int getFalseAnswerTimes() {
		return falseAnswerTimes;
	}
	public void setFalseAnswerTimes(int falseAnswerTimes) {
		this.falseAnswerTimes = falseAnswerTimes;
	}
	
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
