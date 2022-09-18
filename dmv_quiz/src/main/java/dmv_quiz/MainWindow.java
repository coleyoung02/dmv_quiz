package dmv_quiz;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MainWindow {

	private JFrame window;
	private JPanel questionPanel;
	private JTextArea question;
	private JPanel buttonsPanel;
	private JButton[] answerButtons;
	
	public MainWindow() {
		window = new JFrame();
		window.setTitle("DMV Practice Quiz");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setSize(1000,700);
		window.setLocationRelativeTo(null);
		
		questionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
		question = questionText("Press any button to start");
		
		questionPanel.add(question);
		
		buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
		window.add(questionPanel, BorderLayout.NORTH);
		window.add(buttonsPanel, BorderLayout.SOUTH);
		answerButtons = new JButton[3];
		
		
		
		AnswerOption button1 = new AnswerOption("1", true);
		AnswerOption button2 = new AnswerOption("2", true);
		AnswerOption button3 = new AnswerOption("3", true);
		
		answerButtons[0] = button1.getButton();
		answerButtons[1] = button2.getButton();
		answerButtons[2] = button3.getButton();
		
		for (int i = 0; i < 3; i++) {
			buttonsPanel.add(answerButtons[i]);
		}
		buttonsPanel.add(nextButton());
		
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
	
	private void nextQuestion() {
		
	}
	
	
	public void show() {
		window.setVisible(true);
	}
}
