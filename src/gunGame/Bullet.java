package gunGame;

import java.awt.Point;

public class Bullet {
	private int angle; 
	private Point point;
	private int velocity; 
	
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
		System.out.println("Angle: " + angle + "velX: " + velocityX + " velY: " + velocityY );
		System.out.println(point.toString());
		return point;
	}
	
	private double sinDegrees(double angle) 
	{return Math.sin(angle/180*Math.PI);}
	
	private double cosDegrees(double angle) 
	{return Math.cos(angle/180*Math.PI);}
}
