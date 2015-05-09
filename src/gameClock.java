import java.util.ArrayList;

public class gameClock implements Runnable {
	
	private int measuredTickClock = 0;

	@Override
	public void run() {
		while (true) {
			this.measuredTickClock = this.measuredTickClock + Digger.frameInterval;
			if (this.measuredTickClock > 1000) {
				this.measuredTickClock = 0;
			}
			
			for (int i=0;i<Digger.dumpTickableRegistry().size();i++) {
				//Monster logic in here
				Object objectToTick = Digger.dumpTickableRegistry().get(i);
				if (objectToTick instanceof objectMonster) {
					int tickInterval = ((objectMonster) objectToTick).returnTickActionInterval();
					if (this.measuredTickClock % tickInterval == 0) {
						int[] heroCoordinates = Digger.returnHeroCoordinates();
						int[] monsterCoordinates = ((objectMonster) objectToTick).returnCoordinates();
						if (heroCoordinates[0] > monsterCoordinates[0]) {
							((objectMonster) objectToTick).shiftToCoordinate(monsterCoordinates[0]+1,monsterCoordinates[1],"monster");
						} else if (heroCoordinates[0] < monsterCoordinates[0]) {
							((objectMonster) objectToTick).shiftToCoordinate(monsterCoordinates[0]-1,monsterCoordinates[1],"monster");
						} else {
							if (heroCoordinates[1] > monsterCoordinates[1]) {
								((objectMonster) objectToTick).shiftToCoordinate(monsterCoordinates[0],monsterCoordinates[1]+1,"monster");
							} else if (heroCoordinates[1] < monsterCoordinates[1]) {
								((objectMonster) objectToTick).shiftToCoordinate(monsterCoordinates[0],monsterCoordinates[1]-1,"monster");
							} else {
								System.out.println("You have been dingbatted");
							}
						}
					}
				}
			}
			
			try {
				Thread.sleep(125);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
