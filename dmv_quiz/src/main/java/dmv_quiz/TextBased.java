package dmv_quiz;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TextBased {
	
	
	//i should maybe add an option to quit by typing "quit" 
	//but realistically the only way anyone is using the text version
	//is if they are already changing code in the main file so i'm sure 
	//anyone that can do that can end the program on their own
	
	private Model quiz;
	
	public TextBased() {
		this.quiz = new Model();
	}
	
	//I know this method is really beefy and chunky and should probably be broken up
	//but I only really intended the text based part to make sure the model is working and as im 
	//commenting this the gui version already has more functionality
	public void run() {
		Question[] shuffled = quiz.shuffledQuestions();
		boolean isCorrect = false;
		Scanner s = new Scanner(System.in);
		int guess;
		boolean attempted;
		
		for (int i = 0; i < shuffled.length; i++) {
			attempted = false;
			System.out.println(shuffled[i].getText() + "\t\t Score: " + quiz.getPercentCorrect() + "%");
			int[] order = Model.shuffledArray(shuffled[i].getAnswers().length);
			//print out answers in random order, user must pick item indexOf(0) in order + 1
			for (int j = 0; j < order.length; j++) {
				System.out.println("\t" + (j+1) + ") " + shuffled[i].getAnswers()[order[j]]);
			}
			while (!isCorrect) {
				guess = takeGuess(s, order.length);
				
				if (order[guess - 1] == 0) {
					isCorrect = true;
					System.out.println("Correct");
				}
				else 
					System.out.println("Incorrect, try again");
				quiz.guess(attempted, isCorrect);
				attempted = true;
			}
			isCorrect = false;
		}
		System.out.println("Final Score: " + quiz.getPercentCorrect() + "%");
		s.close();
	}
	
	
	
	private static int getPositiveInt(Scanner s) {
		int i = -1;
		while (true) {
			try {
				i = s.nextInt();
				if (i > 0)
					return i;
			}
			catch (InputMismatchException e) {
				s.nextLine();
			}
			System.out.println("Invalid guess, try again");
		}
	}
	private static int takeGuess(Scanner s, int max) {
		int guess = getPositiveInt(s);
		while (guess > max) {
			System.out.println("Invalid guess, try again");
			guess = getPositiveInt(s);
		}
		return guess;
	}
	
	
	
	
}








