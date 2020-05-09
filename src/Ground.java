//Name: Shachaf Smith, Mana Nagampalli
//Date:05/03/2020
//Ver:1
//Notes: This class is a subclass of sprite and extends all methods in sprite and conains the additional code for 
//obstacle in game

public class Ground extends Sprite {

  // This is the ground

  private int orgX;
  private int orgY;
  private double curX;

  // Constructor
  public Ground(int x, int y) {
    super("grass.png", x, y - 100, 780, 150);
    orgX = x;
    curX = x;
    orgY = y;

  }


		private int orgX;
		private int orgY;
		private double curX;
		
		// CONSTRUCTOR
		public Ground(int x, int y) {
			super("grass.png",x,y-100,780,200);
			orgX = x;
			curX = x;
			orgY = y;
		
			
		}
		
		// METHODS
		public void circularLeftShift() {
			if (curX >= 0) {
				moveByAmount(1, 0);
				curX -= 1;
			}
			else{
				moveToLocation(orgX, orgY);
				curX = orgX;
			}
		}


}
