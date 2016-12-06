package gunGame;

import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class GunGame extends JFrame {
	private static final long serialVersionUID = -3742883917951454957L;
	private Board board = Board.getInstance();
	private ControlPanel controlpanel = ControlPanel.getInstance();
	
	public GunGame() {
		// Component Dimensions
		board.setBounds(0, 0, (int) Board.ORIGIN.getX() * 2, (int) Board.ORIGIN.getY() * 2);
		controlpanel.setBounds(0, 0, 190, (int) Board.ORIGIN.getY() * 2);
		
		// Setup Layered Pane
		JLayeredPane pane = new JLayeredPane();
		pane.add(board, new Integer(2));
		pane.add(controlpanel, new Integer(3));
		pane.setPreferredSize(new Dimension((int) Board.ORIGIN.getX() * 2, (int) Board.ORIGIN.getY() * 2));
		
		// Composition of Window
		add(pane);
		setResizable(false);
		pack();
		setLocation(100, 100);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Key Listener for JFrame
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {			
			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				// Pass ALL key messages to the Control Panel
				if (e.getID() == KeyEvent.KEY_TYPED)
					ControlPanel.getInstance().keyTyped(e);
				return false;
			}
		});
		
		// Usage Instructions
		JOptionPane.showMessageDialog(this, "Welcome to Angle Gun Game!\n\nYour objective is to destroy the "
				+ "targets that are rapidly\napproaching your cannon. Enter an angle in degrees\nusing the keyboard,"
				+ "and press ENTER to fire.\n\nCheck out Quiz Mode to brush up on your angles.", 
				"Instructions", JOptionPane.INFORMATION_MESSAGE);
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
					double speed = (board.getScore() / 50.0) + 0.3;
					board.addTarget(new Target(Math.random() * 360, speed, (int) Board.ORIGIN.getX()));
				}
			}
		}).start();
	}
	
	public static void main(String[] args) {
		(new GunGame()).run();
	}
}
