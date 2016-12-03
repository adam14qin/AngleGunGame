package gunGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class ControlPanel extends JPanel implements ActionListener, FocusListener {
	private static final long serialVersionUID = -8802015293029611173L;
	private Board board = Board.getInstance();

	private JLabel scoreLabel, angleLabel, highQuizScoreLabel;
	private JTextField scoreTextField, angleTextField;
	private JButton fire, quiz;
	private int highQuizScore = 0;
	private int numQuizQuestions=0;
	private QuizWindow quizWindow; 
	
	private static ControlPanel theInstance = new ControlPanel();
	
	public static ControlPanel getInstance() {
		return theInstance; 
	}
	
	public ControlPanel() {
		// Score Label
		scoreLabel = new JLabel("Score: ");
		
		// Score Display
		scoreTextField = new JTextField();
		scoreTextField.setEditable(false);
		scoreTextField.setText("0");
		
		// Angle Label
		angleLabel = new JLabel("Angle: ");
		
		// Angle Display
		angleTextField = new JTextField();
		angleTextField.setText("0");
		angleTextField.addFocusListener(this);
		angleTextField.addActionListener(this);
		
		// Fire Button
		fire = new JButton("Fire!");
		fire.addActionListener(this);
		
		//Quiz Button
		quiz = new JButton("Quiz!");
		quiz.addActionListener(this);
		
		//Quiz High Score Label
		highQuizScoreLabel = new JLabel("High Quiz Score: 0/0"); 
		
		// Composition
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(scoreLabel);
		add(scoreTextField);
		add(angleLabel);
		add(angleTextField);
		add(fire);
		add(quiz);
		add(highQuizScoreLabel);
		
		add(Box.createVerticalStrut(300));
	}
	
	public void update() {
		// Update Score
		scoreTextField.setText(String.valueOf(board.getScore()));
	}

	private ArrayList<Question> generateQuiz(int numQuestions)
	{
		Random random = new Random();
		ArrayList<Question> quiz = new ArrayList<Question>();  
		for(int i=0; i<numQuestions; i++)
		{
			int answer = random.nextInt(8)+1;
			int change = random.nextInt(46)-22; 
			int angle = (45 * (answer-1) + change + 360) % 360; 
			quiz.add(new Question(angle, answer)); 
		}
		return quiz; 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == quiz)
		{
			ArrayList<Question> quiz = generateQuiz(5);
			quizWindow = new QuizWindow(quiz.size(), quiz); 
		}
		if(e.getSource() == QuizWindow.finishButton)
		{
			if(highQuizScore < QuizWindow.correct){
				highQuizScore=QuizWindow.correct;
				highQuizScoreLabel.setText("High Quiz Score: " + highQuizScore + "/" + numQuizQuestions);
				quizWindow.setVisible(false); 
			}
		}
		else if (e.getSource() == angleTextField) {
			try {
				// Update the Player to Reflect the Guess
				board.getActivePlayer().guess(Integer.parseInt(angleTextField.getText()));
				board.update();
			} catch (NumberFormatException ignore) {
			}
			
		} else if (e.getSource() == fire) {
			// Fire the Bullet
			board.getActivePlayer().fire(10);
			
			// Start a Timer to Update the Board 
			Timer timer = new Timer(30, null);
			timer.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					board.update();
					Target hit = board.checkCollision();
					ControlPanel.this.update();
					
					// Iterate through some test cases
					if (hit != null) {
						timer.stop();
					}
				}
			});
			timer.start();
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == angleTextField) {
			try {
				// Update the Player to Reflect the Guess
				board.getActivePlayer().guess(Integer.parseInt(angleTextField.getText()));
				board.update();
			} catch (NumberFormatException ignore) {
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == angleTextField) {
			try {
				// Update the Player to Reflect the Guess
				board.getActivePlayer().guess(Integer.parseInt(angleTextField.getText()));
				board.update();
			} catch (NumberFormatException ignore) {
			}
		}	
	}
}
