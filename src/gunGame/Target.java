package gunGame;

import java.awt.Point;

public class Target {

	private int length;
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
	}
	
	public Point update() {
		double velocityX = -(velocity*cosDegrees((double)direction.getAngle()));
		double velocityY = (velocity*sinDegrees((double)direction.getAngle()));
		double oldY = point.getY();
		double oldX = point.getX();
		System.out.println(point.toString());
		point.setLocation(oldX+velocityX, oldY + velocityY);
		System.out.println("Angle: " + direction.getAngle() + "velX: " + velocityX + " velY: " + velocityY );
		System.out.println(point.toString());
		return point;
	}
	
	private double sinDegrees(double angle) 
	{return Math.sin(angle/180*Math.PI);}
	
	private double cosDegrees(double angle) 
	{return Math.cos(angle/180*Math.PI);}
	
}
