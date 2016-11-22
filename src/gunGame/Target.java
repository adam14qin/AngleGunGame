package gunGame;

import java.awt.Point;

public class Target {

	private int length;
	private Point point;
	private TargetDirection direction; 
	private int velocity; 
	
	public Target(TargetDirection direction, int velocity, int distanceFromOrigin) {
		
	}
	
	public Point update() {
		return new Point(0,0);
	}
	
}
