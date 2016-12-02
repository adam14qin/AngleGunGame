package gunGame;

import java.awt.Image;
import java.awt.Toolkit;

public enum TargetDirection {
	
	
	NORTH(90, "/images/CANNON_NORTH"), SOUTH(270,"/images/CANNON_SOUTH"), EAST(0,"/images/CANNON_EAST"), WEST(180,"/images/CANNON_WEST"), SOUTH_EAST(315,"/images/CANNON_SOUTH_EAST"), SOUTH_WEST(225,"/images/CANNON_SOUTH_WEST"), NORTH_EAST(45,"/images/CANNON_NORTH_EAST"), NORTH_WEST(135,"/images/CANNON_NORTH_WEST");
	
	private int angle;
	private Image image; 
    private TargetDirection(int value, String imagePath){
    this.angle = value;
    this.setImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/"+imagePath)));
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
