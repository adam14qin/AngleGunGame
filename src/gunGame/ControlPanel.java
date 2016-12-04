package gunGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlPanel extends JPanel implements ActionListener, KeyListener {
	private static final long serialVersionUID = -8802015293029611173L;
	private Board board = Board.getInstance();

	private JLabel scoreLabel, angleLabel;
	private static JLabel highQuizScoreLabel;
	private JTextField scoreTextField, angleTextField;
	private JButton fire, quiz;
	private int highQuizScore = 0;
	private static QuizWindow quizWindow;
	
	private static int currentAngle = 0;
	private boolean appendAngle = false;
	private boolean negativeAngle = false;
	
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
		angleTextField.setEditable(false);
		angleTextField.setText("0");
		
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
		
		// Update Angle
		angleTextField.setText(String.valueOf(currentAngle));
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
		if(e.getSource() == quiz) {
			// Pause the Game
			board.pause();

			// Open the Quiz (blocks because it is modal)
			ArrayList<Question> quiz = generateQuiz(5);
			quizWindow = new QuizWindow(quiz.size(), quiz);
			quizWindow.setModal(true);
			quizWindow.setVisible(true);
			
			// Resume the Game and Update the High Score
			board.resume();	
			if(highQuizScore < QuizWindow.correct){
				highQuizScore=QuizWindow.correct;
				highQuizScoreLabel.setText("High Score: " + highQuizScore + "/" + 5);
			}
		} else if (e.getSource() == fire) {
			// Fire the Bullet
			if (board.getActivePlayer().getBullet() == null)
				board.getActivePlayer().fire(10);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
			// Update the Current Angle
			currentAngle = Math.abs(currentAngle);
			int newAngle = Integer.parseInt(Character.toString(e.getKeyChar()));
			if (appendAngle && currentAngle < Integer.MAX_VALUE / 11 
					&& currentAngle > Integer.MIN_VALUE / 11) 
				newAngle = currentAngle * 10 + newAngle;
			currentAngle = (negativeAngle ? -newAngle : newAngle);
			appendAngle = true;
			
			// Update the Player to Reflect the Guess
			board.getActivePlayer().guess(currentAngle);
			board.update();
			
		} else if (e.getKeyChar() == '-') { // Minus
			// Update Angle to Reflect Minus
			int newAngle = currentAngle;
			if (appendAngle) {
				newAngle = -1 * currentAngle; 
				negativeAngle = !negativeAngle;
			} else {
				negativeAngle = true;
			}			
			currentAngle = newAngle;
			
			// Update the Player to Reflect the Guess
			board.getActivePlayer().guess(currentAngle);
			board.update();
		} else if (e.getKeyChar() == '\n') { // Enter
			appendAngle = false;
			negativeAngle = false;
			fire.doClick();
		}
	}

	@Override public void keyPressed(KeyEvent e) {}
	@Override public void keyReleased(KeyEvent e) {}
}
