
public class Mario extends MovingImage {
	// FIELDS
	private double velX, velY;
	private boolean onSurface;
	
	// CONSTRUCTOR
	public Mario(int x, int y) {
		super("mario.png",x,y,40,50);
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
	
	public void fall(MovingImage platform) {
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
	}
	
	
}