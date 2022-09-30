package dmv_quiz;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Model {

	
	//change this to adjust which file questions come from
	private static String filename = "q.json";
		
		
	private int correct;
	private int answered;
	private Question[] questions;
	private List<Question> missed;
	
	

	public Model() {
		this.answered = 0;
		this.correct = 0;
		this.questions = Parser.parse(filename);
		this.missed = new ArrayList<Question>();
	}
	
	public Model(Model m) {
		this.answered = 0;
		this.correct = 0;
		this.questions = m.missed.toArray(new Question[m.missed.size()]);
		this.missed = new ArrayList<Question>();
	}

	//returns a shuffled array containing integers 0 to l
	public static int[] shuffledArray(int l) {
		Random r = new Random();
		int[] shuffled = new int[l];
		for (int i = 0; i < l; i++) {
			shuffled[i] = i;
		}
		for (int i = 0; i < l; i++) {
			int newIndex = r.nextInt(l);
			int v = shuffled[i];
			shuffled[i] = shuffled[newIndex];
			shuffled[newIndex] = v;
		}
		return shuffled;
	}
	
	public int getCorrect() {
		return correct;
	}
	
	public int getAnswered() {
		return answered;
	}
	
	public int length() {
		return questions.length;
	}
	
	public Question[] shuffledQuestions() {
		List<Question> q = Arrays.asList(questions);
		Collections.shuffle(q);
		Question[] shuffled = new Question[questions.length];
		q.toArray(shuffled);
		return shuffled;
	}
	
	public int getPercentCorrect() {
		if (answered == 0) {
			return 100;
		}
		else
			return 100*correct/answered;
	}

	public void guess(boolean attempted, boolean isRight) {
		if (!attempted) {
			if (isRight)
				correct++;
			answered++;
		}
	}
	
	public void guess(boolean attempted, boolean isRight, Question q) {
		if (!attempted) {
			if (isRight)
				correct++;
			else
				missed.add(q);
			answered++;
		}
	}
}








