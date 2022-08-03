package dmv_quiz;

public class Question {
	//a question should always list the correct answer first
	private String text;
	private String[] answers;
	
	public Question(String text, String[] answers) {
		this.text = text;
		this.answers = answers;
	}
	
	public String getText() {
		return text;
	}
	
	public String[] getAnswers() {
		return answers;
	}
	
}
