//Name: Shachaf Smith, Mana Nagampalli
//Date:05/03/2020
//Ver:1
//Notes: This class is a subclass of sprite and extends all methods in sprite and conains the additional code for 
//obstacle in game

public class Obstacle extends Sprite {
  // need to set original positions

  // This method will cause the obstacle move left across the screen. Once it goes
  // off of the window, it will come back to the beginning.


		private double velX, velY;
		private boolean onSurface;
		
		// CONSTRUCTOR
		public Obstacle(int x, int y) {
			super("virus.png",x,y,40,50);
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

  
 
