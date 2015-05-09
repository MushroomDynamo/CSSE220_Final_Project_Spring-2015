import java.util.ArrayList;

public class gameClock implements Runnable {
	
	private int measuredTickClock = 0;

	@Override
	public void run() {
		while (true) {
			
			this.measuredTickClock = this.measuredTickClock + 125;
			if (this.measuredTickClock > 1000) {
				this.measuredTickClock = 0;
			}
			
			
			for (int i=0;i>Digger.tickableRegistrySize();i++) {
				//Monster logic in here
			}
			
			try {
				Thread.sleep(125);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
