import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
public class mainMenu extends JFrame{

	/**
	 * TODO Put here a description of this field.
	 */
	private static final long serialVersionUID = 1L;
	private JButton startButton;
	
	public mainMenu() {
		this.getContentPane();
		this.setLayout(new BorderLayout());
		setBackground(Color.blue);
		this.startButton = new JButton("Start!");
		this.startButton.setPreferredSize(new Dimension(50, 50));
		add(this.startButton,BorderLayout.CENTER);
		this.startButton.addActionListener(new gameStartListener());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		
	
	

}
