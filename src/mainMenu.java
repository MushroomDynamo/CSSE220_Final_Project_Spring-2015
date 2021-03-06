import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;

public class mainMenu extends JFrame {

	private JButton startButton;
	
	BufferedImage myPicture = Digger.titlescreen1Image;
	TitlePanel titlePanel = new TitlePanel();
	
	public mainMenu() {
		this.getContentPane();
		this.setLayout(new BorderLayout());
		setBackground(Color.blue);
		this.startButton = new JButton("Start!");
		this.startButton.setPreferredSize(new Dimension(50, 50));
		add(this.startButton,BorderLayout.SOUTH);
		this.startButton.addActionListener(new gameStartListener());
		add(this.titlePanel,BorderLayout.CENTER);
		this.titlePanel.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
