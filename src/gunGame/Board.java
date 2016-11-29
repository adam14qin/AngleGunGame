package gunGame;

import java.awt.Point;
import java.awt.Shape;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Board extends JPanel{
	private ArrayList<Target> targets; 
	private ChallengePlayer challengePlayer;
	public QuizPlayer quizPlayer;
	public Player activePlayer; 
	
	private int score; 
	public final static Point ORIGIN = new Point(300, 300);
	private static Board theInstance = new Board();
	
	private Board() {
		challengePlayer = new ChallengePlayer(); 
		quizPlayer = new QuizPlayer();
		activePlayer = challengePlayer; 
		targets = new ArrayList<>();
	}
	
	public static Board getInstance() 
	{
		return theInstance; 
	}
	
	public void update() {
		activePlayer.getBullet().update();
		for(Target target : targets)
		{
			target.update();
		}
		checkCollision();
	}
	
	public Target checkCollision() {
		Bullet bullet = activePlayer.getBullet();
		for(Target target : targets)
		{
			if(collidesWith(target, bullet))
			{
				System.out.println("True");
				return target;
			}
		}
		return null;
	}

	public boolean collidesWith(Shape first, Shape second) {
	    boolean s = first.getBounds2D().intersects(second.getBounds2D());
	    return s;
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
