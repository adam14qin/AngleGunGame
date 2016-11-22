package gunGame;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Board extends JPanel{
	private ArrayList<Target> targets; 
	private Player player;
	private int score; 
	public final Point ORIGIN = new Point(300, 300);
	private static Board theInstance = new Board();
	
	private Board() {
		
	}
	
	public static Board getInstance() 
	{
		return theInstance; 
	}
	
	public void update() {
		
	}
	
	public boolean checkCollision() {
		return false; 
	}

	public void initialize() {
		
	}
}
