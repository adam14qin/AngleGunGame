package gunGame;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class QuizQuestion extends JPanel{
	
	public JRadioButton numberAnswers = new JRadioButton(); 
	public JLabel question = new JLabel(); 
	public JLabel picture = new JLabel(); 
	private int angle; 
	private int answer; 
	
	public QuizQuestion(int angle, int answer) {
		question.setText("Enter the corresponding number for " + this.angle);
		picture.setIcon(new ImageIcon(TargetDirection.EAST.getImage()));
		
	}
	
	
}
