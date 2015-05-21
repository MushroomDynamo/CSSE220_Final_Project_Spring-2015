import java.util.ArrayList;
import java.util.Random;

public class gameClock implements Runnable {
	
	private int measuredTickClock = 0;
	private int tickLoopCounter = 1;
	public boolean doGameTicks = true;
	public Random randomGenerator = new Random();
	private int points = 0;
	private boolean allEmeraldsCollected = true;
	private boolean changedMonsterThisStep = false;
	
	@Override
	public void run() {
		while (!Thread.interrupted()) {
			this.measuredTickClock = this.measuredTickClock + Digger.frameInterval;
			if (this.measuredTickClock == 1000) {
				this.measuredTickClock = 0;
				this.tickLoopCounter = this.tickLoopCounter + 1;
			}
			if (doGameTicks == true) {
				this.changedMonsterThisStep = false;
				
				if (this.tickLoopCounter % 60 == 0) {
					levelManager.refreshNoHeroes(Digger.returnLevelList()[Digger.returnLevelPosition()]);
					this.tickLoopCounter = 1;
				}
				Digger.menu.setLifeLabel(Digger.getHeroLives());
				Digger.menu.setgameScore(this.points);
				
				this.allEmeraldsCollected = true;
				for (int b=0;b<gameGrid.yGrid.size();b++) {
					ArrayList<objectDrawable> xGrid = gameGrid.yGrid.get(b);
					for (int j=0;j<xGrid.size();j++) {
						String objectType = xGrid.get(j).getObjectType();
						if (objectType == "emerald") {
							this.allEmeraldsCollected = false;
						}}} 
				if (this.allEmeraldsCollected == true) { 
						int levelPosition = Digger.advanceLevelPosition();
						String levelList[] = Digger.returnLevelList();
						if (levelPosition >= levelList.length) {
							//
						} else {
						System.out.println("level "+levelPosition);
						Digger.clearTickableRegistry();
						levelManager.readLevelFile(levelList[levelPosition]);
						}
					}

				for (int i=0;i<Digger.dumpTickableRegistry().size();i++) {
					//Monster logic in here
					Object objectToTick = Digger.dumpTickableRegistry().get(i);
					
					randomGenerator.setSeed(System.currentTimeMillis());
					int newInt = randomGenerator.nextInt(1999);
					if (newInt == 56 && this.changedMonsterThisStep == false) {
						if (objectToTick instanceof objectMonster) {
							if (objectToTick instanceof objectMonsterNonDigging) {
								this.changedMonsterThisStep = true;
								objectMonsterNonDigging objectMonsterNonDigging = (objectMonsterNonDigging) objectToTick;
								int[] monsterCoordinates = objectMonsterNonDigging.returnCoordinates();
								Digger.dumpTickableRegistry().remove(i);
								objectMonster objectMonster = new objectMonster(monsterCoordinates[0],monsterCoordinates[1]);
							} else {
								this.changedMonsterThisStep = true;
								objectMonster objectMonster = (objectMonster) objectToTick;
								int[] monsterCoordinates = objectMonster.returnCoordinates();
								Digger.dumpTickableRegistry().remove(i);
								objectMonsterNonDigging objectMonsterNonDigging = new objectMonsterNonDigging(monsterCoordinates[0],monsterCoordinates[1]);
							}
						}
					}
					
					if ((objectToTick instanceof objectMonster) & (!(objectToTick instanceof objectMonsterNonDigging))) {
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
									Digger.setHeroDead(true);
									Digger.removeHeroLives();
									if(Digger.getHeroLives()<=0){
										Digger.closeGame();
									}
								
								}
							}
								}
					} else if (objectToTick instanceof objectMonsterNonDigging) {
								int[] heroCoordinates = Digger.returnHeroCoordinates();
								int[] monsterCoordinates = ((objectMonster) objectToTick).returnCoordinates();
								objectMonsterNonDigging monsterNonDigging = ((objectMonsterNonDigging) objectToTick);
								int tickInterval = ((objectMonster) objectToTick).returnTickActionInterval();
								if (this.measuredTickClock % tickInterval == 0) {
									
									if ((heroCoordinates[0] == monsterCoordinates[0]) && (heroCoordinates[1] == monsterCoordinates[1])) {
										Digger.setHeroDead(true);
										Digger.removeHeroLives();
										if(Digger.getHeroLives()<=0){
										Digger.closeGame();
										}
									}
								
								while (true) {
									if (monsterNonDigging.movementDirection == 4) {
										monsterNonDigging.movementDirection = 0;
									}
									if (monsterNonDigging.movementDirection == 0) {
										if (monsterCoordinates[1]+1 != Digger.gameHeight) {
											if (monsterNonDigging.checkCoordinate(monsterCoordinates[0],monsterCoordinates[1]+1) == "null" || monsterNonDigging.checkCoordinate(monsterCoordinates[0],monsterCoordinates[1]+1) == "hero") {
												monsterNonDigging.shiftToCoordinate(monsterCoordinates[0],monsterCoordinates[1]+1,"monster2");
												break;
											} else {
												monsterNonDigging.movementDirection = monsterNonDigging.movementDirection + 1;
												break;
											} 
										} else {
											monsterNonDigging.movementDirection = monsterNonDigging.movementDirection + 1;
											break;
										}
									} else if (monsterNonDigging.movementDirection == 1) {
										if (monsterCoordinates[0]+1 != Digger.gameWidth) {
											if (monsterNonDigging.checkCoordinate(monsterCoordinates[0]+1,monsterCoordinates[1]) == "null" || monsterNonDigging.checkCoordinate(monsterCoordinates[0]+1,monsterCoordinates[1]) == "hero") {
												monsterNonDigging.shiftToCoordinate(monsterCoordinates[0]+1,monsterCoordinates[1],"monster2");
												break;
											} else {
												monsterNonDigging.movementDirection = monsterNonDigging.movementDirection + 1;
												break;
											}
										} else {
											monsterNonDigging.movementDirection = monsterNonDigging.movementDirection + 1;
											break;
										}
									} else if (monsterNonDigging.movementDirection == 2) {
										if (monsterCoordinates[1]-1 != -1) {
											if (monsterNonDigging.checkCoordinate(monsterCoordinates[0],monsterCoordinates[1]-1) == "null" || monsterNonDigging.checkCoordinate(monsterCoordinates[0],monsterCoordinates[1]-1) == "hero") {
												monsterNonDigging.shiftToCoordinate(monsterCoordinates[0],monsterCoordinates[1]-1,"monster2");
												break;
											} else {
												monsterNonDigging.movementDirection = monsterNonDigging.movementDirection + 1;
												break;
											}
										} else {
											monsterNonDigging.movementDirection = monsterNonDigging.movementDirection + 1;
											break;
										}
									} else {
										if (monsterCoordinates[0]-1 != -1) {
											if (monsterNonDigging.checkCoordinate(monsterCoordinates[0]-1,monsterCoordinates[1]) == "null" || monsterNonDigging.checkCoordinate(monsterCoordinates[0]-1,monsterCoordinates[1]) == "hero") {
												monsterNonDigging.shiftToCoordinate(monsterCoordinates[0]-1,monsterCoordinates[1],"monster2");
												break;
											} else {
												monsterNonDigging.movementDirection = monsterNonDigging.movementDirection + 1;
												break;
											}
										} else {
											monsterNonDigging.movementDirection = monsterNonDigging.movementDirection + 1;
											break;
										}
									}
								}
							}
					} else if (objectToTick instanceof objectHero) {
						int tickInterval = ((objectHero) objectToTick).returnTickActionInterval();
						if (this.measuredTickClock % tickInterval == 0) {
							String bufferedAction = Digger.bufferedAction;
							Digger.bufferedAction = "null";
							if (bufferedAction == "right") {
								if (Digger.Hero.xPos == Digger.gameWidth-1){
									if(gameGrid.yGrid.get(Digger.Hero.yPos).get(0).getObjectType() == "emerald"){
										this.points = this.points + 50;
									}
									else if(gameGrid.yGrid.get(Digger.Hero.yPos).get(0).getObjectType() == "gold"){
										this.points = this.points + 200;
									}
								}
									else if (gameGrid.yGrid.get(Digger.Hero.yPos).get(Digger.Hero.xPos+1).getObjectType() == "emerald"){
									this.points = this.points + 50;
									}
									else if (gameGrid.yGrid.get(Digger.Hero.yPos).get(Digger.Hero.xPos+1).getObjectType() == "gold"){
										this.points = this.points + 200;
								}
								Digger.Hero.shiftToCoordinate(Digger.Hero.xPos+1,Digger.Hero.yPos,"hero");
							} else if (bufferedAction == "left") {
								if (Digger.Hero.xPos == 0){
									if(gameGrid.yGrid.get(Digger.Hero.yPos).get(Digger.gameWidth-1).getObjectType() == "emerald"){
										this.points = this.points + 50;
									}
									if(gameGrid.yGrid.get(Digger.Hero.yPos).get(Digger.gameWidth-1).getObjectType() == "gold"){
										this.points = this.points + 200;
									}
								}
								else if (gameGrid.yGrid.get(Digger.Hero.yPos).get(Digger.Hero.xPos-1).getObjectType() == "emerald"){
									this.points = this.points + 50;
								}
								else if (gameGrid.yGrid.get(Digger.Hero.yPos).get(Digger.Hero.xPos-1).getObjectType() == "gold"){
									this.points = this.points + 200;
								}
								Digger.Hero.shiftToCoordinate(Digger.Hero.xPos-1,Digger.Hero.yPos,"hero");
							} else if (bufferedAction == "up") {
								if (Digger.Hero.yPos == 0){
									if(gameGrid.yGrid.get(Digger.gameHeight-1).get(Digger.Hero.xPos).getObjectType() == "emerald"){
										this.points = this.points + 50;
									}
									else if(gameGrid.yGrid.get(Digger.gameHeight-1).get(Digger.Hero.xPos).getObjectType() == "gold"){
										this.points = this.points + 200;
									}
								}
								else if (gameGrid.yGrid.get(Digger.Hero.yPos-1).get(Digger.Hero.xPos).getObjectType() == "emerald"){
									this.points = this.points + 50;
								}
								else if (gameGrid.yGrid.get(Digger.Hero.yPos-1).get(Digger.Hero.xPos).getObjectType() == "gold"){
									this.points = this.points + 200;
								}
								Digger.Hero.shiftToCoordinate(Digger.Hero.xPos,Digger.Hero.yPos-1,"hero");
							} else if (bufferedAction == "down") {
								if (Digger.Hero.yPos == Digger.gameHeight-1){
									if(gameGrid.yGrid.get(0).get(Digger.Hero.xPos).getObjectType() == "emerald"){
										this.points = this.points + 50;
									}
									if(gameGrid.yGrid.get(0).get(Digger.Hero.xPos).getObjectType() == "gold"){
										this.points = this.points + 200;
									}
								}
								else if (gameGrid.yGrid.get(Digger.Hero.yPos+1).get(Digger.Hero.xPos).getObjectType() == "emerald"){
									this.points = this.points + 50;
								}
								else if (gameGrid.yGrid.get(Digger.Hero.yPos+1).get(Digger.Hero.xPos).getObjectType() == "gold"){
									this.points = this.points + 200;
								}
								Digger.Hero.shiftToCoordinate(Digger.Hero.xPos,Digger.Hero.yPos+1,"hero");
							} else if (bufferedAction == "attack") {
								for (int j=0;j<Digger.dumpTickableRegistry().size();j++) {
									Object objectToTick1 = Digger.dumpTickableRegistry().get(j);
									if (objectToTick1 instanceof objectMonster) {
										//Changed some variable names here
										int[] heroCoordinates = Digger.returnHeroCoordinates();
										int[] monsterCoordinates = ((objectMonster) objectToTick1).returnCoordinates();
										System.out.println(Digger.facing);
										if(Math.abs(heroCoordinates[0]-monsterCoordinates[0])<= 1 && heroCoordinates[1] == monsterCoordinates[1]){
											gameGrid.yGrid.get(monsterCoordinates[1]).get(monsterCoordinates[0]).setObjectType("null");
											Digger.dumpTickableRegistry().remove(j);
										}else if(Math.abs(heroCoordinates[1]-monsterCoordinates[1])<= 1 && heroCoordinates[0] == monsterCoordinates[0]){
											gameGrid.yGrid.get(monsterCoordinates[1]).get(monsterCoordinates[0]).setObjectType("null");
											Digger.dumpTickableRegistry().remove(j);
										}
									}
								}
							}
						}
					} else if (objectToTick instanceof objectMoneyBag) {
						objectMoneyBag moneyBag = ((objectMoneyBag) objectToTick);
						int tickInterval = moneyBag.returnTickActionInterval();
						if (this.measuredTickClock % tickInterval == 0) {
							int[] bagCoordinates = moneyBag.returnCoordinates();
							if (bagCoordinates[1] != Digger.gameHeight - 1) {
								if (moneyBag.falling == false) {
									String objectType = gameGrid.yGrid.get(bagCoordinates[1]+1).get(bagCoordinates[0]).getObjectType();
									if (objectType != "ground" && objectType != "emerald" && objectType != "moneybag") {
										moneyBag.falling = true;
										gameGrid.yGrid.get(bagCoordinates[1]).get(bagCoordinates[0]).setObjectType("moneybag_lethal");
									}
								} else {
									
								}
							} else {
								
							}
							if (bagCoordinates[1] == Digger.gameHeight - 1) {
								gameGrid.yGrid.get(bagCoordinates[1]-1).get(bagCoordinates[0]).setObjectType("null");
								gameGrid.yGrid.get(bagCoordinates[1]).get(bagCoordinates[0]).setObjectType("gold");
								Digger.dumpTickableRegistry().remove(i);
							} else {
								String objectType = gameGrid.yGrid.get(bagCoordinates[1]+1).get(bagCoordinates[0]).getObjectType();
								if (objectType != "ground" && objectType != "emerald" && objectType != "moneybag") {
									if (moneyBag.falling == false) {
										//There is no obstruction under the moneybag, so set to a falling state/texture
										
										moneyBag.falling = true;
										gameGrid.yGrid.get(bagCoordinates[1]).get(bagCoordinates[0]).setObjectType("moneybag_lethal");
									} else {
										//Falling logic
										if (objectType == "monster" || objectType == "monster2") {
//											for (int j=0;j<Digger.dumpTickableRegistry().size();j++) {
//												int[] monsterCoordinates = ((objectMonster) objectToTick).returnCoordinates();
//												if ((bagCoordinates[0] == monsterCoordinates[0]) && (bagCoordinates[1] == monsterCoordinates[1])) {
//													gameGrid.yGrid.get(monsterCoordinates[1]).get(monsterCoordinates[0]).setObjectType("null");
//													Digger.dumpTickableRegistry().remove(j);
//												}
//											}
											System.out.println("shoobily doobily");
										} else if (objectType == "hero") {
											System.out.println("shoobily doobily doodily flanders");
											Digger.setHeroDead(true);
										} else {
											//Just fall downwards
											moneyBag.yPos = moneyBag.yPos + 1;
											if(gameGrid.yGrid.get(bagCoordinates[1]-1).get(bagCoordinates[0]).getObjectType()=="ground"){	//checks to ensure it wont dig one up
											}else{
											gameGrid.yGrid.get(bagCoordinates[1]-1).get(bagCoordinates[0]).setObjectType("null");
											gameGrid.yGrid.get(bagCoordinates[1]).get(bagCoordinates[0]).setObjectType("moneybag_lethal");
											}
										}
									}
								} else {
									if (moneyBag.falling == true) {
										//Oh no, it hit the ground! Kablammo!
										System.out.println("Kablammo!");
										gameGrid.yGrid.get(bagCoordinates[1]-1).get(bagCoordinates[0]).setObjectType("null");
										gameGrid.yGrid.get(bagCoordinates[1]).get(bagCoordinates[0]).setObjectType("gold");
										Digger.dumpTickableRegistry().remove(i);
									}
								}
							}
						}
					}
				}
			}
			
			if(Digger.getHeroDead()){
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
	
				Digger.setHeroDead(false);
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
