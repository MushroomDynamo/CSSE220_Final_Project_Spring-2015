import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {
	
	//Some basic stuff to display the hero's lives and score at the top of the screen.
	
	public JLabel heroLives = new JLabel();
	public JLabel gameScore = new JLabel();

	public InfoPanel() {
		setBackground(Color.blue);
		setLayout(new FlowLayout(5, 5, 5));

		this.heroLives.setOpaque(true);
		this.heroLives.setBackground(Color.GRAY);
		this.heroLives.setForeground(Color.WHITE);
		add(this.heroLives);
		setLifeLabel(Digger.getHeroLives());
		
		this.gameScore.setOpaque(true);
		this.gameScore.setBackground(Color.GRAY);
		this.gameScore.setForeground(Color.WHITE);
		add(this.gameScore);
		setgameScore(0); 
	}
	
	public void setLifeLabel(int lives) {
		this.heroLives.setText("# Lives: " + lives);
	}
	
	public void setgameScore(int score) {
		this.gameScore.setText("Score: " + score);
	}
	
}