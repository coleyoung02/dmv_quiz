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
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
	private AnswerOption[] answerButtons;
	
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
		
		questionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
		question = questionText("Press next to start");
		
		questionPanel.add(question);
		buttonsPanel = new JPanel(new GridLayout(noButtons + 1, 1, 1, 1));
		window.add(questionPanel, BorderLayout.NORTH);
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
		
		//model
		quiz = new Model();
		shuffled = quiz.shuffledQuestions();
	}
	//gui stuff
	private JTextArea questionText(String q) {
		JTextArea textArea = new JTextArea(q, 10, 30);
		textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
		textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 22));
		return textArea;
	}
	
	private JButton nextButton() {
		JButton nBut = new JButton("Next");
		nBut.setFont(new Font("Arial", Font.PLAIN, 22));
		nBut.setFocusable(false);
		nBut.setMargin(new Insets(10, 30, 10, 30));
		nBut.setBackground(Color.WHITE);
		
		nBut.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
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
	private void nextQuestion() {
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
	
	
	
	
	public void show() {
		window.setVisible(true);
	}
}
