package gunGame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class QuizQuestion extends JPanel{
	
	public JRadioButton numberAnswers = new JRadioButton(); 
	public JLabel question = new JLabel(); 
	public JLabel picture = new JLabel(); 
	public ButtonGroup buttonGroup = new ButtonGroup(); 
	private int angle; 
	private int answer; 
	
	public QuizQuestion(int angle, int answer) {
		this.angle = angle; 
		this.answer = answer;
		this.setLayout(new BorderLayout());
		question.setText("Enter the corresponding number for " + this.angle);
		picture.setIcon(new ImageIcon(TargetDirection.EAST.getImage()));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		
		for(int i = 0; i<8; i++)
		{
			JRadioButton button = new JRadioButton("" + (i+1)); 
			if(i==0)
			button.setSelected(true);
			buttonGroup.add(button);
			buttonPanel.add(button);
		}
		
		JPanel rightPanel = new JPanel(new BorderLayout());
		JPanel wrapperPanel = new JPanel(new BorderLayout());
		JPanel wrapperPanel2 = new JPanel(new BorderLayout());
		
		wrapperPanel.add(question, BorderLayout.NORTH); 
		wrapperPanel2.add(buttonPanel, BorderLayout.CENTER); 
		wrapperPanel.add(wrapperPanel2, BorderLayout.CENTER);
		rightPanel.add(wrapperPanel);
		this.add(rightPanel, BorderLayout.EAST);
		this.add(picture, BorderLayout.WEST);
		}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		QuizQuestion gui = new QuizQuestion(45, 3);
		frame.add(gui); 
		gui.setVisible(true);
		frame.pack();
		frame.setVisible(true);
	}
}
