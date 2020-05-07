import java.awt.Image;

//Name: Shachaf Smith, Mana Nagampalli
//Date:05/03/2020
//Ver:1
//Notes: This class is a subclass of sprite and extends all methods in sprite and contains the additional code for 
//player in game

public class Player extends Sprite {
 
	

	// need to add action listener for 'up' or space key to jump
  // need to set original positions
	private double velX, velY;
	private boolean onSurface;
	
	// CONSTRUCTOR
	public Player(int x, int y) {
		super("running.png",x,y,40,50);
		velX = 0;
		velY = 0;
		onSurface = false;
	}
	
	// METHODS
	public void walk(int dir) {
		if (Math.abs(velX) < 10)
			velX += dir;
	}
	
	public void jump() {
		if (onSurface) {
			velY = -12;
			moveByAmount(0,(int)velY);
		}
	}
	
	public void fall(Sprite platform) {
		if (velY < 15) {
			velY += 0.5; // Gravity
		}
		velX *= 0.9; // Friction
		
		onSurface = false;
		
		if ( platform.isPointInImage( getX()+getWidth()/2 , getY() + getHeight() ) ) {
			velY = 0;
			onSurface = true;
		}
		
		moveByAmount((int)velX, (int)velY);
  // this method makes the player's character jump when the up/space key is
  // pressed. Needs to use action listener for this.
	}
}
