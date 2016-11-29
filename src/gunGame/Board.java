package gunGame;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Board extends JPanel{
	private ArrayList<Target> targets; 
	private ChallengePlayer challengePlayer;
	public QuizPlayer quizPlayer;
	
	private int score; 
	public final static Point ORIGIN = new Point(300, 300);
	private static Board theInstance = new Board();
	
	private Board() {
		challengePlayer = new ChallengePlayer(); 
		quizPlayer = new QuizPlayer();
		targets = new ArrayList<>();
	}
	
	public static Board getInstance() 
	{
		return theInstance; 
	}
	
	public void update() {
		
	}
	
	public Target checkCollision() {
		return new Target(null, 0, 0); 
	}

	public void initialize() {
		
	}
	
	//GETTERS AND SETTERS 
	
	public ChallengePlayer getChallengePlayer() {
		return challengePlayer; 
	}
	
	public void addTarget(Target target) {
		targets.add(target);
	}

	public ArrayList<Target> getTargets() {
		return targets;
		}

	public int getScore() {
		return score;
	}
}
