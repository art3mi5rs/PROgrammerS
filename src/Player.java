import java.awt.Image;

//Name: Shachaf Smith, Mana Nagampalli
//Date:05/03/2020
//Ver:1
//Notes: This class is a subclass of sprite and extends all methods in sprite and contains the additional code for 
//player in game

public class Player extends Sprite {
 
	

	// need to add action listener for 'up' or space key to jump
  // need to set original positions
	private double y;
	private boolean onSurface;
	
	// CONSTRUCTOR
	public Player(int x, int y) {
		super("running.png",x,y,40,50);
		x = 0;
		y = 0;
		onSurface = false;
	}
	
	// METHODS
	
	
	public void jump() {
		if (onSurface) {
			y = -12;
			moveByAmount(0,(int)y);
		}
	}
}
