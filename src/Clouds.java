import java.util.Random;

//Name: Shachaf Smith, Mana Nagampalli
//Date:05/03/2020
//Ver:1
//Notes: This class is a subclass of sprite and extends all methods in sprite and contains the additional code for 
//the clouds in game

public class Clouds extends Sprite {
  // need to set original positions

  // This method will cause the clouds move right across the screen. Once it goes
  // off of the window, it will come back to the beginning.


		private int orgX;
		private int orgY;
		private double curX;
		
		// CONSTRUCTOR
		public Clouds(int x, int y) {
			super("cloud.png", x, y, 200, 50);
			orgX = x;
			curX = x;
			orgY = y;
		
			
		}
		
		// Moves the clouds to the left
		public void circularLeftShift() {
		  Random rand = new Random();
			if (curX >= 0) {
				moveByAmount(rand.nextInt(2) - 2, 0);
				curX -= 1;
			}
			else{
				moveToLocation(orgX, orgY);
				curX = orgX;
			}
		}
}
 
