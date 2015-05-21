import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class gameAudio implements Runnable {
	
	//A whole bunch of boilerplate code to loop the game soundtrack over...and over...and over...and over...Yeesh.

	@Override
	public void run() {
		try {
			String audiopath = new java.io.File(".").getCanonicalPath();
			audiopath = audiopath + "\\audio\\";
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(audiopath + "Digger.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(inputStream);
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	        Thread.sleep(6000);
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
