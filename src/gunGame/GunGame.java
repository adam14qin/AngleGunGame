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
		double v = 0.2;
		/*board.addTarget(new Target(0, v, 200));
		board.addTarget(new Target(45, v, 200));
		board.addTarget(new Target(90, v, 200));
		board.addTarget(new Target(135, v, 200));
		board.addTarget(new Target(180, v, 200));
		board.addTarget(new Target(225, v, 200));
		board.addTarget(new Target(270, v, 200));
		board.addTarget(new Target(315, v, 200));*/
		
		// Composition of Window
		setLayout(new BorderLayout());
		add(board, BorderLayout.CENTER);
		add(controlpanel, BorderLayout.EAST);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize((int) Board.ORIGIN.getX() * 2 + 100, (int) Board.ORIGIN.getY() * 2);
		setVisible(true);
	}
	
	public void run() {
		// Start a Timer to Update the Board 
		new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				board.update();
				board.checkCollision();
				controlpanel.update();
				
				if (Math.random() < 0.005 || board.getTargets().size() == 0) {
					board.addTarget(new Target(Math.random() * 360, 0.3, (int) Board.ORIGIN.getX()));
				}
			}
		}).start();
	}
	
	public static void main(String[] args) {
		(new GunGame()).run();
	}
}
