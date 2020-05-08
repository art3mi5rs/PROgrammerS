//Name: Shachaf Smith, Mana Nagampalli
//Date:05/03/2020//Ver:1
//Notes: This class is the superclass of player and obstacle class and it provides the methods that lets them print
//themselves and collision detection

	import java.awt.*;
	import java.awt.image.*;
	import javax.swing.*;

	/*
	 * by: Shelby
	 * on: 5/3/13
	 */
	 
	public class Sprite {
		
		//Fields
		private int x, y;
		private int width, height;
		private Image image;
		private boolean isVisible;
		
		//Constructors
		public Sprite (String image, int x, int y, int w, int h) {
			this((new ImageIcon(image)).getImage(),x,y,w,h);
		}
		

		public Sprite(Image img, int x, int y, int w, int h) {
			image = img;
			this.x = x;
			this.y = y;
			width = w;
			height = h;
			isVisible = true;
		}
		
		//Methods		
		public void moveToLocation(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public void moveByAmount(int x, int y) {
			this.x += x;
			this.y += y;
		}
		
		public void applyWindowLimits(int windowWidth, int windowHeight) {
			x = Math.min(x,windowWidth-this.width);
			y = Math.min(y,windowHeight-this.height);
			x = Math.max(0,x);
			y = Math.max(0,y);
		}
		
		public boolean isPointInImage(int mouseX, int mouseY) {
			if (mouseX >= x && mouseY >= y && mouseX <= x + width && mouseY <= y + height)
				return true;
			return false;
		}
		
		
		public void draw(Graphics g, ImageObserver io) {
			if (isVisible)
				g.drawImage(image,x,y,width,height,io);
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public int getWidth() {
			return width;
		}
		
		public int getHeight() {
			return height;
		}
		
		public boolean isVisible() {
			return isVisible;
		}
		
	}

