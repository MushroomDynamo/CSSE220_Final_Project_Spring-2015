
public class gameClock implements Runnable {
	
	private int measuredTickClock = 0;
	public boolean check = false;
	private boolean doGameTicks = true;

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			this.measuredTickClock = this.measuredTickClock + Digger.frameInterval;
			if (this.measuredTickClock > 1000) {
				this.measuredTickClock = 0;
			}
			
			if (doGameTicks == true) {
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
									Digger.Hero.setHeroDead(true);
									check = true;
									System.out.println(Digger.Hero.getHeroDead());
								}
							}
						}
					} else if (objectToTick instanceof objectHero) {
						int tickInterval = ((objectHero) objectToTick).returnTickActionInterval();
						if (this.measuredTickClock % tickInterval == 0) {
							String bufferedAction = Digger.bufferedAction;
							Digger.bufferedAction = "null";
							if (bufferedAction == "right") {
								Digger.Hero.shiftToCoordinate(Digger.Hero.xPos+1,Digger.Hero.yPos,"hero");
							} else if (bufferedAction == "left") {
								Digger.Hero.shiftToCoordinate(Digger.Hero.xPos-1,Digger.Hero.yPos,"hero");
							} else if (bufferedAction == "up") {
								Digger.Hero.shiftToCoordinate(Digger.Hero.xPos,Digger.Hero.yPos-1,"hero");
							} else if (bufferedAction == "down") {
								Digger.Hero.shiftToCoordinate(Digger.Hero.xPos,Digger.Hero.yPos+1,"hero");
							}
						}
					} else if (objectToTick instanceof objectMoneyBag) {
						int tickInterval = ((objectMoneyBag) objectToTick).returnTickActionInterval();
						if (this.measuredTickClock % tickInterval == 0) {
							
						}
					}
				}
			}
			
			if(Digger.Hero.getHeroDead()){
				this.doGameTicks = false;
				try {
					Thread.sleep(Digger.frameInterval);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Digger.clearTickableRegistry();
				//Kind of kludgey, but we'll do this for now
				gameGrid.yGrid.get(Digger.Hero.yPos).get(Digger.Hero.xPos).setObjectType("null");

				levelManager.refresh(Digger.returnLevelList()[Digger.returnLevelPosition()]);
	
				Digger.Hero.setHeroDead(false);
				this.doGameTicks = true;
				}
			
			try {
				Thread.sleep(Digger.frameInterval);
			} catch (InterruptedException e) {
				System.out.println("Thread sleep interrupted");
			}
		}
	}
	
}
