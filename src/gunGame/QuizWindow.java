package gunGame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javafx.scene.layout.Border;

public class QuizWindow extends JDialog implements ActionListener {
	private int questionIndex = 1;
	public static int correct = 0;
	private int numQuestions; 
	private ArrayList<Question> questions; 
	private QuizQuestion question;
	private JLabel scoreIndexLabel;
	private JLabel questionIndexLabel;
	private JButton nextButton;
	public static JButton finishButton = new JButton("Finish"); 
	private boolean completed = false;

	public QuizWindow(int numQuestions, ArrayList<Question> questions) {
		this.setVisible(true);
		this.setModal(true);
		this.setLayout(new BorderLayout());
		this.numQuestions = numQuestions;
		this.questions = questions;

		JPanel questionIndexWrapper = new JPanel(); 
		questionIndexLabel = new JLabel("Question " + questionIndex + "/" + numQuestions);
		questionIndexWrapper.add(questionIndexLabel);

		Question firstQuestion = questions.get(0); 
		question = new QuizQuestion(firstQuestion);

		JPanel scoreIndexWrapper = new JPanel(new BorderLayout());
		scoreIndexLabel = new JLabel("Correct: " + correct + "/" + (questionIndex-1));
		nextButton = new JButton("Next");
		nextButton.addActionListener(this);
		finishButton.setEnabled(false); 
		JPanel finishWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
		finishWrapper.add(finishButton);
		scoreIndexWrapper.add(scoreIndexLabel, BorderLayout.WEST);
		scoreIndexWrapper.add(finishWrapper, BorderLayout.CENTER); 
		scoreIndexWrapper.add(nextButton, BorderLayout.EAST);

		this.add(questionIndexWrapper, BorderLayout.NORTH);
		this.add(question, BorderLayout.CENTER);
		this.add(scoreIndexWrapper, BorderLayout.SOUTH);

		this.pack();
	}

	public void update() {
		if(questionIndex<numQuestions)
		{
			questionIndex++;
			this.remove(question);
			question = new QuizQuestion(questions.get(questionIndex-1));
			this.add(question, BorderLayout.CENTER);
			questionIndexLabel.setText("Question: " + questionIndex + "/" + numQuestions);
		}
		scoreIndexLabel.setText("Correct: " + correct + "/" + (questionIndex-1));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(question.correct != null)
		{
			if(question.correct)
			{
				correct++;
			}
			if(questionIndex < numQuestions)
			{
				update(); 
			}
			else {
				scoreIndexLabel.setText("Correct: " + correct + "/" + (questionIndex));
				completed = true;
				nextButton.setEnabled(false);
				finishButton.setEnabled(true);
				finishButton.addActionListener(ControlPanel.getInstance());
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "You need to make a guess!", "Not Finished!", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {

		ArrayList<Question> temp = new ArrayList<>();
		temp.add(new Question(45,2));
		temp.add(new Question(90,3));
		temp.add(new Question(180,5));
		temp.add(new Question(360,1));

		QuizWindow gui = new QuizWindow(temp.size(), temp);
		gui.setVisible(true);
		gui.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	}

}
