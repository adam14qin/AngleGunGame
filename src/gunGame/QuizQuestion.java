package gunGame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class QuizQuestion extends JPanel implements ActionListener{
	
	
	public JRadioButton numberAnswers = new JRadioButton(); 
	public JLabel question = new JLabel(); 
	public JLabel picture = new JLabel(); 
	public JButton answerButton = new JButton("Guess");
	public ButtonGroup buttonGroup = new ButtonGroup(); 
	private ArrayList<JRadioButton> radioButtons = new ArrayList<JRadioButton>(); 
	public int guess = 1;
	private int angle; 
	private int answer; 
	public Boolean correct = null;
	
	public QuizQuestion(int angle, int answer) {
		this.angle = angle; 
		this.answer = answer;
		this.setLayout(new BorderLayout());
		question.setText("Enter the corresponding number for " + this.angle);
		picture.setIcon(new ImageIcon(TargetDirection.EAST.getImage()));
		JPanel buttonPanel = new JPanel();
		answerButton.addActionListener(this);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		
		for(int i = 0; i<8; i++)
		{
			JRadioButton button = new JRadioButton("" + (i+1)); 
			if(i==0)
			{
			button.setSelected(true);
			}
			button.addActionListener(this);
			buttonGroup.add(button);
			buttonPanel.add(button);
			radioButtons.add(button);
		}
		
		JPanel rightPanel = new JPanel(new BorderLayout());
		JPanel wrapperPanel = new JPanel(new BorderLayout());
		JPanel wrapperPanel2 = new JPanel(new BorderLayout());
		
		wrapperPanel.add(question, BorderLayout.NORTH); 
		wrapperPanel2.add(buttonPanel, BorderLayout.CENTER); 
		wrapperPanel.add(wrapperPanel2, BorderLayout.CENTER);
		rightPanel.add(wrapperPanel, BorderLayout.CENTER);
		rightPanel.add(answerButton, BorderLayout.SOUTH);
		this.add(rightPanel, BorderLayout.EAST);
		this.add(picture, BorderLayout.WEST);
		}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(answerButton)) 
		{
			correct = answer==guess;
			answerButton.setText((correct) ? "Correct!" : "Incorrect");
			for(JRadioButton radioButton : radioButtons)
			{
				radioButton.setEnabled(false);
			}
			answerButton.setEnabled(false);
		}
		else {
			JRadioButton button = (JRadioButton) e.getSource();
			TargetDirection direction = TargetDirection.values()[Integer.parseInt(button.getText())-1]; 
			guess = Integer.parseInt(button.getText());
			picture.setIcon(new ImageIcon(direction.getImage()));
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		QuizQuestion gui = new QuizQuestion(45, 3);
		frame.add(gui); 
		gui.setVisible(true);
		frame.pack();
		frame.setVisible(true);
	}
}
