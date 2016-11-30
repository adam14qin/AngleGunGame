package gunGame;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlPanel extends JPanel {
	private static final long serialVersionUID = -8802015293029611173L;
	private Board board = Board.getInstance();

	private JLabel scoreLabel, angleLabel;
	private JTextField scoreTextField, angleTextField;
	
	public ControlPanel() {
		// Score Label
		scoreLabel = new JLabel("Score: ");
		
		// Score Display
		scoreTextField = new JTextField();
		scoreTextField.setEditable(false);
		scoreTextField.setText("0");
		
		// Angle Label
		angleLabel = new JLabel("Angle: ");
		
		// Angle Display
		angleTextField = new JTextField();
		angleTextField.setEditable(false);
		angleTextField.setText("");
		
		// Composition
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(scoreLabel);
		add(scoreTextField);
		add(angleLabel);
		add(angleTextField);
		add(Box.createVerticalStrut(300));
	}
	
	public void update() {
		// Update Score
		scoreTextField.setText(String.valueOf(board.getScore()));
		
		// Update Angle
		angleTextField.setText(String.valueOf(board.getActivePlayer().getBullet().getAngle()));
	}
}
