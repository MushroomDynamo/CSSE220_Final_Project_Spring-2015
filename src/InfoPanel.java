import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel{
	
	public JLabel heroLives = new JLabel();
	public JLabel gameScore = new JLabel();

	public InfoPanel(JFrame gp) {
		setBackground(Color.blue);
		setLayout(new FlowLayout(5, 5, 5));

		
		
		heroLives.setOpaque(true);
	    heroLives.setBackground(Color.GRAY);
	    heroLives.setForeground(Color.WHITE);
	    add(heroLives);
	    setLifeLabel(Digger.getHeroLives());
	      
	      

	      gameScore.setOpaque(true);
	      gameScore.setBackground(Color.GRAY);
	      gameScore.setForeground(Color.WHITE);
	      add(gameScore);
	      setgameScore(0);
	      
	}
	
	public void setLifeLabel(int lives){

		this.heroLives.setText("# Lives: " + lives);
	}
	
	public void setgameScore(int score){
		
		this.gameScore.setText("Score: " + score);
	}
	
	
	
}