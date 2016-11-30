package gunGame;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Target extends Line2D {

	private int length;
	private Point first, second; 
	private Point point;
	private TargetDirection direction; 
	private int velocity; 
	
	public Target(TargetDirection direction, int velocity, int distanceFromOrigin) {
		this.length = 10; 
		this.direction = direction; 
		this.velocity = velocity;
		int distanceX = (int) (distanceFromOrigin*cosDegrees(direction.getAngle()));
		int distanceY = (int) -(distanceFromOrigin*sinDegrees(direction.getAngle()));
		this.point = new Point((int)Board.ORIGIN.getX() + distanceX, (int)Board.ORIGIN.getY() + distanceY);
		recalibratePoints();
	}
	


	public void recalibratePoints() {
		int x1 = (int) (point.getX() + (length/2)*(sinDegrees(direction.getAngle()))); 
		int x2 = (int) (point.getX() - (length/2)*(sinDegrees(direction.getAngle()))); 
		int y1 = (int) (point.getY() + (length/2)*(cosDegrees(direction.getAngle()))); 
		int y2 = (int) (point.getY() - (length/2)*(cosDegrees(direction.getAngle())));
		first = new Point(x1, y1); 
		second = new Point(x2, y2); 
	}
	
	public Point update() {
		double velocityX = -(velocity*cosDegrees((double)direction.getAngle()));
		double velocityY = (velocity*sinDegrees((double)direction.getAngle()));
		double oldY = point.getY();
		double oldX = point.getX();
		point.setLocation(oldX+velocityX, oldY + velocityY);
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
	public void setLine(double x1, double y1, double x2, double y2) {
		
	}



	@Override
	public String toString() {
		return "Target [point=" + point + ", direction=" + direction + "]";
	}
}
