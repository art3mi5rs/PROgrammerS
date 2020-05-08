import java.awt.Image;

//Name: Shachaf Smith, Mana Nagampalli
//Date:05/03/2020
//Ver:1
//Notes: This class is a subclass of sprite and extends all methods in sprite and contains the additional code for 
//player in game

public class Player extends Sprite {
 
	


	private boolean onSurface;
	
	// CONSTRUCTOR
	public Player(int x, int y) {
		super("running.png",x,y,40,50);
		onSurface = true;
	}
	
	// METHODS
	
	
	public void jump() {
		if (onSurface) {
			moveByAmount(0,-50);
			onSurface = false;
		}
	}
	
	public void comeToSurface () {
		if (!onSurface) {
			moveByAmount(0,+50);
			onSurface = true;
		}
	}
}
