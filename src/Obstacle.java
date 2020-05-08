//Name: Shachaf Smith, Mana Nagampalli
//Date:05/03/2020
//Ver:1
//Notes: This class is a subclass of sprite and extends all methods in sprite and conains the additional code for 
//obstacle in game

public class Obstacle extends Sprite {

		private int orgX;
		private int orgY;
		private double curX;
		
		//Constructor
		public Obstacle(int x, int y) {
			super("virus.png",x,y,40,50);
			orgX = x;
			curX = x;
			orgY = y;
		}
		
		//Methods
		public void circularleftShift() {
			if (curX >= 0) {
				moveByAmount(-1, 0);
				curX -= 1;
			}
			else{
				moveToLocation(orgX, orgY);
				curX = orgX;
			}
		}
}
 
