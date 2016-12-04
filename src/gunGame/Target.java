package gunGame;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Target extends Line2D {
	private Point first, second; 
	private double firstX, firstY, secondX, secondY, pointX, pointY;
	private Point point;
	private double angle; 
	private double velocity;
	
	public static final int LENGTH = 100;
	public static final int WIDTH = 20;
	public static final int COLLISION = 10;
	
	public Target(double angle, double velocity, int distanceFromOrigin) {
		this.angle = angle; 
		this.velocity = velocity;
		int distanceX = (int) (distanceFromOrigin*cosDegrees(angle));
		int distanceY = (int) -(distanceFromOrigin*sinDegrees(angle));
		pointX = Board.ORIGIN.getX() + distanceX;
		pointY = Board.ORIGIN.getY() + distanceY;
		this.point = new Point((int) pointX, (int) pointY);
		recalibratePoints();
	}

	public void recalibratePoints() {
		firstX = (pointX + (LENGTH/2)*(sinDegrees(angle))); 
		secondX = (pointX - (LENGTH/2)*(sinDegrees(angle))); 
		firstY = (pointY + (LENGTH/2)*(cosDegrees(angle))); 
		secondY = (pointY - (LENGTH/2)*(cosDegrees(angle)));
		
		first = new Point((int) firstX, (int) firstY); 
		second = new Point((int) secondX, (int) secondY); 
	}
	
	public Point update() {
		double velocityX = -(velocity*cosDegrees(angle));
		double velocityY = (velocity*sinDegrees(angle));
		pointX += velocityX; pointY += velocityY;
		point.setLocation(pointX, pointY);
		recalibratePoints();
		return point;
	}
	
	private double sinDegrees(double angle) 
	{return Math.sin(angle/180*Math.PI);}
	
	private double cosDegrees(double angle) 
	{return Math.cos(angle/180*Math.PI);}

	@Override
	public Rectangle2D getBounds2D() {
		int x = (int) Math.min(first.getX(), second.getX());
		int y = (int) Math.min(first.getY(), second.getY()); 
		int height = (int) Math.abs(second.getY()-first.getY());
		int width = (int) Math.abs(second.getX()-first.getX());
		return new Rectangle(x, y, width+1, height+1);  
	}

	@Override
	public Point2D getP1() {
		return first; 
	}

	@Override
	public Point2D getP2() {
		return second;
	}

	@Override
	public double getX1() {
		return first.getX();
	}

	@Override
	public double getX2() {
		return second.getX();
	}

	@Override
	public double getY1() {
		return first.getY();
	}

	@Override
	public double getY2() {
		return second.getY();
	}

	@Override
	public void setLine(double x1, double y1, double x2, double y2) {}

	@Override
	public String toString() {
		return "Target [point=" + point + ", direction=" + angle + "]";
	}
	
	public boolean intersects(Rectangle2D bounds, int radius) {
		double x0 = (bounds.getX() + bounds.getWidth() / 2) ;
		double y0 = (bounds.getY() + bounds.getHeight() / 2) ;
		double x1=first.getX();
		double y1=first.getY();
		double x2=second.getX();
		double y2=second.getY();
		double distance=Math.abs((y2-y1)*x0-(x2-x1)*y0+x2*y1-y2*x1)/Math.sqrt(Math.pow((y2-y1), 2)+Math.pow(x2-x1, 2));
		
		double d1 = Math.sqrt(Math.pow(x0 - x1, 2) + Math.pow(y0 - y1, 2));
		double d2 = Math.sqrt(Math.pow(x0 - x2, 2) + Math.pow(y0 - y2, 2));
		double max = Math.max(d1, d2);
		double min = Math.min(d1, d2);
		
		double a = Math.sqrt(Math.pow(max, 2) - Math.pow(distance, 2));
		if (a < LENGTH) {
			if (distance - radius < COLLISION)
				return true;			
		} else {
			if (min - radius < COLLISION)
				return true;
		}
		
		return false;
	}
}
