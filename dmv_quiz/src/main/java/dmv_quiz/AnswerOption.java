package dmv_quiz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class AnswerOption {

	private JButton button;
	private boolean correct;
	private boolean clicked;
	private ActionListener myActionListener;
	
	public AnswerOption(String s, boolean correct) {
		this.button = new JButton(s);
		this.correct = correct;
		clicked = false;
		button.setFont(new Font("Arial", Font.PLAIN, 22));
		button.setFocusable(false);
		button.setMargin(new Insets(10, 30, 10, 30));
		button.setBackground(Color.WHITE);
		
		setListener();
		
		
	}
	
	public boolean isClicked() {
		return clicked;
	}
	
	public void setClicked(boolean c) {
		clicked = c;
	}
	
	public void setCorrect(boolean c) {
		correct = c;
		button.removeActionListener(myActionListener);
		setListener();
	}
	
	public JButton getButton() {
		return button;
	}
	
	
	
	private void setListener() {
		if (this.correct) {
			myActionListener = new ActionListener() {

				public void actionPerformed(ActionEvent e) {
						button.setBackground(Color.GREEN);
						clicked = true;
				}
				
			};
			
		}
		else {
			myActionListener = new ActionListener() {

				public void actionPerformed(ActionEvent e) {
						button.setBackground(Color.RED);
						clicked = true;
				}
				
			};
		}
		button.addActionListener(myActionListener);
	}
}
