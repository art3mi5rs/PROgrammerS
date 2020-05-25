//Name: Shachaf Smith, Mana Nagampalli
//Date:05/03/2020
//Ver:1
//Notes: This class is a subclass of sprite and extends all methods in sprite and conains the additional code for 
//obstacle in game

public class Obstacle extends Sprite {

		private int orgX;
		private int orgY;
		
		//Constructor
		public Obstacle(int x, int y) {
			super("virus.png",x,y,39,39);
			orgX = x;
			orgY = y;
		}
		
		//Moves the obstacle left
		public void circularleftShift() {
			if (getX() >= 0) {
				moveByAmount(-1, 0);
			}
			else{
	       System.out.println("circular rotation");
				moveToLocation(orgX, orgY);
				}
		}
}
 
