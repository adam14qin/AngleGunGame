package gunGame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class GunGame extends JFrame {
	private static final long serialVersionUID = -3742883917951454957L;
	private Board board = Board.getInstance();
	private ControlPanel controlpanel = new ControlPanel();
	
	public GunGame() {
		// Game Initialization
		board.addTarget(new Target(TargetDirection.EAST, 0, 200));
		board.addTarget(new Target(TargetDirection.NORTH_EAST, 0, 200));
		board.addTarget(new Target(TargetDirection.NORTH, 0, 200));
		board.addTarget(new Target(TargetDirection.NORTH_WEST, 0, 200));
		board.addTarget(new Target(TargetDirection.WEST, 0, 200));
		board.addTarget(new Target(TargetDirection.SOUTH_WEST, 0, 200));
		board.addTarget(new Target(TargetDirection.SOUTH, 0, 200));
		board.addTarget(new Target(TargetDirection.SOUTH_EAST, 0, 200));
		
		// Composition of Window
		setLayout(new BorderLayout());
		add(board, BorderLayout.CENTER);
		add(controlpanel, BorderLayout.EAST);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize((int) Board.ORIGIN.getX() * 2 + 100, (int) Board.ORIGIN.getY() * 2);
		setVisible(true);
	}
	
	public void run() {

	}
	
	public static void main(String[] args) {
		(new GunGame()).run();
	}
}
