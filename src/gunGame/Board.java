package gunGame;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sun.awt.Mutex;

public class Board extends JPanel{
	private static final long serialVersionUID = 2914353251387918799L;
	private ArrayList<Target> targets; 
	private ChallengePlayer challengePlayer;
	private QuizPlayer quizPlayer;
	private Player activePlayer; 
	
	
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
		
		for (Target target : targets)
			target.update();
		
		repaint();
	}
	
	public Target checkCollision() {
		Bullet bullet = activePlayer.getBullet();
		
		if (bullet != null) {
			// Check Target Collision
			for (Target target : targets) {
				
				// Target Hit!
				if (target.intersects(bullet.getBounds2D())) {
					activePlayer.removeBullet();
					score++;
					
					targets.remove(target);
					return target;
				}
			}
				
			// Check Out of Bounds Condition
			if (bullet.getX() < 0 || bullet.getY() < 0 || bullet.getX() > 2 * ORIGIN.getX() - Bullet.RADIUS 
					|| bullet.getY() > 2 * ORIGIN.getY() - Bullet.RADIUS) {
				activePlayer.removeBullet();
				return new Target(-1, -1 ,-1);
			}
		}
		
		// Check Intersection with Player
		for (int i = 0; i < targets.size(); i++) {
			Target t = targets.get(i);
			if (t.intersects(activePlayer.getBounds2D())) {
				targets.remove(t);
				score = 0;
			}
		}
		
		return null;
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		// Clear the Screen
		g.clearRect(0, 0, getWidth(), getHeight());
		Graphics2D g2 = (Graphics2D) g;
		
		// Draw Targets
		g2.setStroke(new BasicStroke(5));
		for (Target t : targets)
			g2.draw(t);
		
		// Draw Bullets
		g2.setStroke(new BasicStroke(1));
		if (activePlayer.getBullet() != null)
			g2.draw(activePlayer.getBullet());
		
		// Draw Player
		activePlayer.draw(g);
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
