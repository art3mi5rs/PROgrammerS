//Name: Shachaf Smith, Mana Nagampalli
//Date:05/03/2020
//Ver:1
//Notes: This class is a subclass of sprite and helps display the grass background 
//on the JPanel

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

}
