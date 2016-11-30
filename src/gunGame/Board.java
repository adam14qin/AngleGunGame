package gunGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Board extends JPanel {
	private static final long serialVersionUID = 2914353251387918799L;
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
	
	public static Board getInstance() {
		return theInstance; 
	}
	
	public void update() {
		if (activePlayer.getBullet() != null)
			activePlayer.getBullet().update();
		
		for(Target target : targets)
			target.update();
		
		repaint();
	}
	
	public Target checkCollision() {
		Bullet bullet = activePlayer.getBullet();
		
		if (bullet != null) {
			// Check Target Collision
			for (Target target : targets) {
				
				// Target Hit!
				if (target.getBounds2D().intersects(bullet.getBounds2D())) {
					activePlayer.removeBullet();
					score++;
					
					return target;
				}
			}
				
			// Check Out of Bounds Condition
			if (bullet.getX() < 0 || bullet.getY() < 0 || bullet.getX() > 2 * ORIGIN.getX() - Bullet.RADIUS 
					|| bullet.getY() > 2 * ORIGIN.getY() - Bullet.RADIUS) {
				return new Target(TargetDirection.EAST, -1 ,-1);
			}
		}
		
		return null;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// Clear the Screen
		g.clearRect(0, 0, getWidth(), getHeight());
		
		// Draw 2D Objects
		Graphics2D g2 = (Graphics2D) g;
		for (Target t : targets)
			g2.draw(t);
		
		if (activePlayer.getBullet() != null)
			g2.draw(activePlayer.getBullet());
	}
	
	//GETTERS AND SETTERS 
	
	public Player getActivePlayer() {
		return activePlayer; 
	}
	
	public void addTarget(Target target) {
		targets.add(target);
	}
	
	public void clearTargets() {
		targets.clear();
	}

	public ArrayList<Target> getTargets() {
		return targets;
		}

	public int getScore() {
		return score;
	}
}
