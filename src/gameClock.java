import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class gameClock implements Runnable {
	
	private int measuredTickClock = 0;
	public boolean doGameTicks = true;
	public Random randomGenerator = new Random();
	private int points = 0;
	private boolean done = true;
	@Override
	public void run() {
		while (!Thread.interrupted()) {
			this.measuredTickClock = this.measuredTickClock + Digger.frameInterval;
			if (this.measuredTickClock == 1000) {
				this.measuredTickClock = 0;
			}
			if (doGameTicks == true) {
				for (int k=0;k<Digger.dumpTickableRegistry().size();k++) {
					//
				}
				for (int i=0;i<Digger.dumpTickableRegistry().size();i++) {
					//Monster logic in here
					Object objectToTick = Digger.dumpTickableRegistry().get(i);
					if (objectToTick instanceof objectMonster) {
						int tickInterval = ((objectMonster) objectToTick).returnTickActionInterval();
						if (this.measuredTickClock % tickInterval == 0) {
							int[] heroCoordinates = Digger.returnHeroCoordinates();
							int[] monsterCoordinates = ((objectMonster) objectToTick).returnCoordinates();
							//int points = score.score(monsterCoordinates[0],monsterCoordinates[1]);
							//points = score.score(points, monsterCoordinates[0],monsterCoordinates[1]); //////fix the points variable that is to the right;
							//System.out.println(points);
							done = true;
							for (int b=0;b<gameGrid.yGrid.size();b++) {
								ArrayList<objectDrawable> xGrid = gameGrid.yGrid.get(b);
								for (int j=0;j<xGrid.size();j++) {
									String objectType = xGrid.get(j).getObjectType();
									if (objectType == "emerald") {
										done = false;
									}
								}
							}
							if (done) {int levelPosition = Digger.advanceLevelPosition();
							String levelList[] = Digger.returnLevelList();
							if (levelPosition >= levelList.length){
								System.out.println("YOU WIN!");
							} else {
								System.out.println("level "+levelPosition);
								Digger.clearTickableRegistry();
								levelManager.readLevelFile(levelList[levelPosition]);
								}
							}
								
							if (objectToTick instanceof objectMonsterNonDigging) {
								objectMonsterNonDigging monsterNonDigging = ((objectMonsterNonDigging) objectToTick);
								randomGenerator.setSeed((long) Digger.seed+monsterCoordinates[0]*monsterCoordinates[1]+heroCoordinates[0]*heroCoordinates[1]);
								if (heroCoordinates[0] == monsterCoordinates[0] && heroCoordinates[1] == monsterCoordinates[1]) {
									//System.out.println("You have been dingbatted");
									Digger.setHeroDead(true);
									Digger.removeHeroLives();
									System.out.println(Digger.getHeroLives());

									if(Digger.getHeroLives()<=0){
										System.out.println(Digger.getHeroLives());
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
							} else {
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
											System.out.println(Digger.getHeroLives());
											Digger.closeGame();
										}
									
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
								Digger.Hero.shiftToCoordinate(Digger.Hero.xPos+1,Digger.Hero.yPos,"hero");
							} else if (bufferedAction == "left") {
								Digger.Hero.shiftToCoordinate(Digger.Hero.xPos-1,Digger.Hero.yPos,"hero");
							} else if (bufferedAction == "up") {
								Digger.Hero.shiftToCoordinate(Digger.Hero.xPos,Digger.Hero.yPos-1,"hero");
							} else if (bufferedAction == "down") {
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
											System.out.println("shoobily doobily");
										} else if (objectType == "objectHero") {
											System.out.println("shoobily doobily doodily flanders");
										} else {
											//Just fall downwards
											moneyBag.yPos = moneyBag.yPos + 1;
											gameGrid.yGrid.get(bagCoordinates[1]-1).get(bagCoordinates[0]).setObjectType("null");
											gameGrid.yGrid.get(bagCoordinates[1]).get(bagCoordinates[0]).setObjectType("moneybag_lethal");
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
