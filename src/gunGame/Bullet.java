package gunGame;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Bullet extends Ellipse2D{
	private int angle; 
	private Point point;
	private int velocity; 
	public static int RADIUS = 10;
	
	public Bullet(int velocity, int angle) {
		point = new Point(Board.ORIGIN);
		this.angle = angle;
		this.velocity = velocity; 
	}
	
	public Point update() {
		double velocityX = (velocity*cosDegrees((double)angle));
		double velocityY = -(velocity*sinDegrees((double)angle));
		double oldY = point.getY();
		double oldX = point.getX();
		point.setLocation(oldX+velocityX, oldY + velocityY);
		return point;
	}
	
	public Point getPoint()
	{
		return point;
	}
	
	private double sinDegrees(double angle) 
	{return Math.sin(angle/180*Math.PI);}
	
	private double cosDegrees(double angle) 
	{return Math.cos(angle/180*Math.PI);}

	@Override
	public Rectangle2D getBounds2D() {
		System.out.println();
		return new Rectangle((int)point.getX()-RADIUS,(int) point.getY()-RADIUS, RADIUS*2, RADIUS*2); 
	}

	@Override
	public double getHeight() {
		return RADIUS*2;
	}

	@Override
	public double getWidth() {
		return getHeight(); 
	}

	@Override
	public double getX() {
		return point.getX();
	}

	@Override
	public double getY() {
		return point.getY();
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public void setFrame(double arg0, double arg1, double arg2, double arg3) {
		
	}
}
