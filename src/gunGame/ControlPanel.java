package gunGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
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

public class ControlPanel extends JPanel implements ActionListener, KeyListener {
	private static final long serialVersionUID = -8802015293029611173L;
	private Board board = Board.getInstance();

	private JLabel scoreLabel, angleLabel;
	private static JLabel highQuizScoreLabel;
	private JLabel scoreTextField, angleTextField;
	private JButton quiz;
	private int highQuizScore = 0;
	private static QuizWindow quizWindow;
	
	private static int currentAngle = 0;
	private boolean appendAngle = false;
	private boolean negativeAngle = false;
	
	private static ControlPanel theInstance = new ControlPanel();
	
	public static ControlPanel getInstance() {
		return theInstance; 
	}
	
	private ControlPanel() {
		// Score Label
		scoreLabel = new JLabel("Score: ");
		scoreLabel.setFont(new Font("Courier New", Font.BOLD, 22));
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setAlignmentX(RIGHT_ALIGNMENT);
		
		// Score Display
		scoreTextField = new JLabel("0");
		scoreTextField.setFont(new Font("Courier New", Font.PLAIN, 22));
		scoreTextField.setForeground(Color.WHITE);
		
		// Angle Label
		angleLabel = new JLabel("Angle: ");
		angleLabel.setFont(new Font("Courier New", Font.BOLD, 22));
		angleLabel.setForeground(Color.WHITE);
		
		// Angle Display
		angleTextField = new JLabel("0\u00b0");
		angleTextField.setFont(new Font("Courier New", Font.PLAIN, 22));
		angleTextField.setForeground(Color.WHITE);
		
		// Quiz Button
		quiz = new JButton("Quiz!");
		quiz.addActionListener(this);
		quiz.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Quiz High Score Label
		highQuizScoreLabel = new JLabel("(High Score = 0/5)");
		highQuizScoreLabel.setFont(new Font("Courier New", Font.PLAIN, 17));
		highQuizScoreLabel.setForeground(Color.WHITE);
		highQuizScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Game Mechanics Panel
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(2, 2));
		gamePanel.setOpaque(false);
		gamePanel.add(scoreLabel);
		gamePanel.add(scoreTextField);
		gamePanel.add(angleLabel);
		gamePanel.add(angleTextField);
		
		// Quiz Panel
		JPanel quizPanel = new JPanel();
		quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));
		quizPanel.add(quiz);
		quizPanel.add(Box.createVerticalStrut(10));
		quizPanel.add(highQuizScoreLabel);
		quizPanel.setOpaque(false);
		
		// Composition
		setLayout(new BorderLayout());
		setOpaque(false);
		add(gamePanel, BorderLayout.NORTH);
		add(quizPanel, BorderLayout.SOUTH);
		
		add(Box.createVerticalStrut(300));
	}
	
	public void update() {
		// Update Score
		scoreTextField.setText(String.valueOf(board.getScore()));
		
		// Update Angle
		angleTextField.setText(String.valueOf(currentAngle) + "\u00b0");
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
				highQuizScoreLabel.setText("(High Score = " + highQuizScore + "/5)");
			}
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
			
			// Fire the Bullet
			if (board.getActivePlayer().getBullet() == null)
				board.getActivePlayer().fire(10);
		}
	}

	@Override public void keyPressed(KeyEvent e) {}
	@Override public void keyReleased(KeyEvent e) {}
}
