package gunGame;

import java.awt.Image;
import java.awt.Toolkit;

public enum TargetDirection {
	
	
	EAST(0,"/images/CANNON_EAST.png"), NORTH_EAST(45,"/images/CANNON_NORTH_EAST.png"),  NORTH(90,"/images/CANNON_NORTH.png"), 
	NORTH_WEST(135,"/images/CANNON_NORTH_WEST.png"), WEST(180,"/images/CANNON_WEST.png"), SOUTH_WEST(225,"/images/CANNON_SOUTH_WEST.png"), 
	SOUTH(270,"/images/CANNON_SOUTH.png"), SOUTH_EAST(315,"/images/CANNON_SOUTH_EAST.png");
	
	private int angle;
	private Image image; 
    private TargetDirection(int value, String imagePath){
    this.angle = value;
    this.setImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(imagePath)));
    }
    
    public int getAngle() {
    	return angle;
    }

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	
}
