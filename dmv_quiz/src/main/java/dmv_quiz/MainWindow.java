package dmv_quiz;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class MainWindow {

	//answer options should probably be shown as a center panel
	//with multiple labels
	
	//alternatively, i could make the buttons a grid vertically stacked
	//and put the answer options in there but then i need to worry about length
	//and the buttons might be ugly
	static final int noButtons = 3;
	private JFrame window;
	private JPanel questionPanel;
	private JTextArea question;
	private JPanel buttonsPanel;
	private JPanel results;
	private JLabel resultsLabel;
	private JLabel discreteResults;
	private AnswerOption[] answerButtons;
	
	private JPanel endPanel;
	private JLabel endResults;
	private JButton restart;
	private JButton review;
	
	private Question[] shuffled;
	private Model quiz;
	private int index;
	
	public MainWindow() {
		//gui stuff
		window = new JFrame();
		window.setTitle("DMV Practice Quiz");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setSize(1000,700);
		window.setLocationRelativeTo(null);
		
		addGUIElements();		
		
		endPanel = new JPanel();
		endResults = new JLabel();
		restart = new JButton();
		review = new JButton();
		
		//model
		startModel(false);
	}
	
	public void show() {
		window.setVisible(true);
	}
	//gui stuff
	
	private void addGUIElements() {
		setQPanel();
		setBPanel();
		setRPanel();
	}
	
	//sets panel to display questions
	private void setQPanel() {
		questionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
		question = questionText("Press \"Next\" to start");
		questionPanel.add(question);
		window.add(questionPanel, BorderLayout.CENTER);
	}
	
	//sets panel with answer and next buttons
	private void setBPanel() {
		buttonsPanel = new JPanel(new GridLayout(noButtons + 1, 1, 1, 1));	
		window.add(buttonsPanel, BorderLayout.SOUTH);
		answerButtons = new AnswerOption[noButtons];
		
		//would need to be adjusted to accommodate more buttons
		AnswerOption button1 = new AnswerOption("1", false);
		AnswerOption button2 = new AnswerOption("2", false);
		AnswerOption button3 = new AnswerOption("3", false);
		answerButtons[0] = button1;
		answerButtons[1] = button2;
		answerButtons[2] = button3;
		//there is possibly going to be a problem with updating these now, maybe not
		for (int i = 0; i < noButtons; i++) {
			buttonsPanel.add(answerButtons[i].getButton());
		}
		buttonsPanel.add(nextButton());
	}
	
	//sets panel to display results
	private void setRPanel() {
		results = new JPanel();
		resultsLabel = new JLabel("DMV Practice Quiz");
		discreteResults = new JLabel("");
		results.add(resultsLabel);
		results.add(discreteResults);
		results.setBackground(Color.LIGHT_GRAY);
		discreteResults.setHorizontalAlignment(SwingConstants.LEFT);
		discreteResults.setFont(new Font("Arial", Font.PLAIN, 22));
		resultsLabel.setHorizontalAlignment(SwingConstants.LEFT);
		resultsLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		window.add(results, BorderLayout.EAST);
	}
	
	private JTextArea questionText(String q) {
		JTextArea textArea = new JTextArea(q, 10, 30);
		textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
		textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 22));
		return textArea;
	}
	
	
	//created in this class because it makes it easiest to interact with MainWindow
	//methods in the relevant object
	private JButton nextButton() {
		JButton nBut = new JButton("Next");
		nBut.setFont(new Font("Arial", Font.PLAIN, 22));
		nBut.setFocusable(false);
		nBut.setMargin(new Insets(10, 30, 10, 30));
		nBut.setBackground(Color.WHITE);
		
		nBut.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (index > 0) {
					updateScore();
				}
				nextQuestion();
			}
			
		});
		return nBut;
	}
	
	private void resetAnswerButtons() {
		for (int i = 0; i < noButtons; i++) {
			answerButtons[i].getButton().setBackground(Color.WHITE);
			answerButtons[i].setClicked(false);
		}
	}
	
	//model interaction and update gui
	private void startModel(boolean review) {
		index = 0;
		if (review)
			quiz = new Model(quiz);
		else
			quiz = new Model();
		shuffled = quiz.shuffledQuestions();
	}
	
	private void nextQuestion() {
		if (index >= quiz.length()) {
			endQuiz();
			return;
		}
		resetAnswerButtons();
		question.setText(shuffled[index].getText());
		int[] order = Model.shuffledArray(shuffled[index].getAnswers().length);
		
		for (int j = 0; j < order.length; j++) {
			answerButtons[j].getButton().setText(shuffled[index].getAnswers()[order[j]]);
			if(order[j] == 0) {
				answerButtons[j].setCorrect(true);
			}
			else {
				answerButtons[j].setCorrect(false);
			}
		}
		index++;
		
	}
	
	//update score when next button is clicked
	private void updateScore() {
		boolean correctGuessed = false;
		for (int i = 0; i < answerButtons.length; i++) {
			if (answerButtons[i].isClicked() && !answerButtons[i].isCorrect()) {
				correctGuessed = false;
				break;
			}
			else if (answerButtons[i].isClicked()) {
				correctGuessed = true;
			}
		}
		quiz.guess(false, correctGuessed, shuffled[index - 1]);
		resultsLabel.setText(Integer.toString(quiz.getPercentCorrect()) + "%");
		discreteResults.setText(Integer.toString(quiz.getCorrect()) + "/" + Integer.toString(quiz.getAnswered()));
	}
	
	//called after answering or skipping final question
	private void endQuiz() {
		window.remove(buttonsPanel);
		window.remove(questionPanel);
		window.remove(results);
		
		String endScreen = new String();
		endScreen += "Your score was " + Integer.toString(quiz.getPercentCorrect()) + "%";
		
		
		endPanel = new JPanel(new GridLayout(2, 1, 1, 1));
		
		endResults = new JLabel(endScreen);
		
		window.add(endResults, BorderLayout.CENTER);
		window.add(endPanel, BorderLayout.SOUTH);
		
		addRestartButton();
		addReviewButton();
	}
	
	// both of these buttons also interact with MainWindow directly
	private void  addRestartButton() {
		restart = new JButton("Click here to restart");
		
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restartQuiz(false);
			}
		});
		endPanel.add(restart);
	}
	private void  addReviewButton() {
		review = new JButton("Click to review missed questions");
		
		review.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restartQuiz(true);
			}
		});
		endPanel.add(review);
	}
	
	
	
	//to implement: 
	//what happens if you try to review with no missed questions?
	//should the review button even appear if no questions are missed?
	
	//resets quiz state
	//if review is true, only use questions on the Model's list of missed questions
	private void restartQuiz(boolean review) {
		window.remove(endResults);
		window.remove(endPanel);
		addGUIElements();
		if (review)
			startModel(true);
		else
			startModel(false);
		SwingUtilities.updateComponentTreeUI(window);
	}
	
	
}
