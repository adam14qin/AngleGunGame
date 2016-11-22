package gunGame;

import javax.swing.JFrame;

public class GunGame extends JFrame{

	private Board board; 
	private int score;
	
	public GunGame() {
		this.board = Board.getInstance(); 
	}
	
	public static void main(String[] args) {
		
	}

}
