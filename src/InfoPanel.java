import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel{
	
	private static JLabel heroLives;
	private static JLabel gameScore;

	public InfoPanel(JFrame gp) {
		setBackground(Color.blue);
		setLayout(new FlowLayout(5, 5, 5));

		
		heroLives = new JLabel("# Lives: " + Digger.getHeroLives());

		  heroLives.setOpaque(true);
	      heroLives.setBackground(Color.GRAY);
	      heroLives.setForeground(Color.WHITE);
	      add(heroLives);
	      
	      gameScore = new JLabel("Score: " + Digger.getHeroLives());

	      gameScore.setOpaque(true);
	      gameScore.setBackground(Color.GRAY);
	      gameScore.setForeground(Color.WHITE);
	      add(gameScore);
	      
	}
	
	public static void setLifeLabel(int lives){

		heroLives.setText("# Lives: " + lives);
	}
	
	public static void setgameScore(int score){
		
		gameScore.setText("Score: " + score);
	}
	
	
	
}