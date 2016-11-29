package gunGame;

public enum TargetDirection {
	
	
	NORTH(90), SOUTH(270), EAST(0), WEST(180), SOUTH_EAST(315), SOUTH_WEST(225), NORTH_EAST(45), NORTH_WEST(135);
	
	private int angle;
    private TargetDirection(int value){
        this.angle = value;
    }
    
    public int getAngle() {
    	return angle;
    }
	
	
}
