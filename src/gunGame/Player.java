package gunGame;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public abstract class Player extends Ellipse2D {
	public static final int CENTER_RADIUS = 15;
	public static final int CANNON_LENGTH = 30;
	public static final int CANNON_WIDTH = 2 * Bullet.RADIUS;
	public static final int OUTER_RADIUS = CANNON_LENGTH + 200;
	
	private Bullet bullet;
	private int guessDegrees;

	public abstract int guess();
	
	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}
	
	public Bullet getBullet() {
		return bullet;
	}

	public void removeBullet() {
		this.bullet = null;
	}
	
	public void guess(int guess) {
		this.guessDegrees = guess;
	}
	
	public void fire(int speed) {
		setBullet(new Bullet(10, guessDegrees));
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		// Draw Trajectory (only if bullet is moving)
		if (bullet != null) {
			Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
	        g2.setStroke(dashed);
	        int fx = (int) (Board.ORIGIN.getX() * 2 * Math.cos(-guessDegrees * Math.PI / 180.0) + Board.ORIGIN.getX());
	        int fy = (int) (Board.ORIGIN.getY() * 2 * Math.sin(-guessDegrees * Math.PI / 180.0) + Board.ORIGIN.getY());
	        g2.drawLine((int) Board.ORIGIN.getX(), (int) Board.ORIGIN.getY(), fx, fy);
		}
        
		// Draw Center
		g.fillArc((int) Board.ORIGIN.getX() - CENTER_RADIUS, (int) Board.ORIGIN.getY() - CENTER_RADIUS, 
				2 * CENTER_RADIUS, 2 * CENTER_RADIUS, 0, 360);
		
		// Draw Cannon
		g2.rotate((-guessDegrees * Math.PI) / 180.0 - Math.PI / 2.0, (int) Board.ORIGIN.getX(), (int) Board.ORIGIN.getY());
		g.fillRect((int) Board.ORIGIN.getX() - CANNON_WIDTH / 2, (int) Board.ORIGIN.getY(), CANNON_WIDTH, CANNON_LENGTH);
	}

	@Override
	public Rectangle2D getBounds2D() {
		return new Rectangle((int) Board.ORIGIN.getX() - OUTER_RADIUS, (int) Board.ORIGIN.getY() - OUTER_RADIUS,
				OUTER_RADIUS * 2, OUTER_RADIUS * 2);
	}

	@Override
	public double getX() {
		return Board.ORIGIN.getX() - OUTER_RADIUS;
	}

	@Override
	public double getY() {
		return Board.ORIGIN.getY() - OUTER_RADIUS;
	}

	@Override
	public double getWidth() {
		return 2 * OUTER_RADIUS;
	}

	@Override
	public double getHeight() {
		return 2 * OUTER_RADIUS;
	}

	@Override public boolean isEmpty() {return false;}
	@Override public void setFrame(double x, double y, double w, double h) {}
}
