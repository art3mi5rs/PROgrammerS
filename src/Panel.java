
//Name: Shachaf Smith, Mana Nagampalli
// Date:05/03/2020
// Ver:1
// Notes: The main class which has all the components to print the backgrounds
// and run the project along with any additional
// features that we may add in the future

import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Panel extends JPanel implements KeyListener {

	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 600;

	private Player mahaf;
	private Obstacle virus;
	private Clouds cloud;
	private Clouds cloud1;
	private Ground grass;

	private boolean upKeyPressed;
	private int runsToSkip;
	private int cloudsRunsToSkip;
	private boolean collision;
	private int groundRunsToSkip;

	public Panel() {
		super();
		upKeyPressed = false;

		runsToSkip = 0xFFB;
		cloudsRunsToSkip = 0xFFFF;
		collision = false;
		groundRunsToSkip= 0xFFFF;

		mahaf = new Player(40, 480);
		virus = new Obstacle(740, 480);
		cloud = new Clouds(400, 20);
		cloud1= new Clouds(200, 20);
		grass= new Ground (0,520);
		mahaf = new Player(230, 480);
		virus = new Obstacle(780,480);
		setBackground(Color.CYAN);
	}

	public void run() {
		while (true) {

			if (runsToSkip == 0) {
				virus.circularleftShift();
				runsToSkip = 0xFFB;
			}
			if (cloudsRunsToSkip == 0) {
				cloud.circularLeftShift();
				cloud1.circularLeftShift();

				cloudsRunsToSkip = 0xFFFF;
			}

			if (runsToSkip == 0) {
				grass.circularLeftShift();
				groundRunsToSkip = 0xFFFF;
			}

			runsToSkip--;
			cloudsRunsToSkip--;
			groundRunsToSkip--;

			if (upKeyPressed) {
				mahaf.jump();

			} else {
				mahaf.comeToSurface();
			}
			repaint();
	    checkCollision();

		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g); // Call JPanel's paintComponent method to paint
									// the background

		int width = getWidth();
		int height = getHeight();

		double ratioX = (double) width / DRAWING_WIDTH;
		double ratioY = (double) height / DRAWING_HEIGHT;

		Graphics2D g2 = (Graphics2D) g;
		AffineTransform at = g2.getTransform();
		g2.scale(ratioX, ratioY);

		virus.draw(g, this);
		mahaf.draw(g, this);
		cloud.draw(g, this);
		cloud1.draw(g, this);
		grass.draw(g, this);

		g2.setTransform(at);
		
		if (collision) {
      g.setColor(Color.BLACK);
      g.setFont(new Font("SansSerif", Font.BOLD, 30));
      FontMetrics fm = g.getFontMetrics();
      String s = "collision!";
      g.drawString(s, width / 2 - fm.stringWidth(s) / 2, height / 2);
    }

	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE) {
			upKeyPressed = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE) {
			upKeyPressed = false;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void checkmahaf() {
		int x = mahaf.getX() + mahaf.getWidth() / 2;
		int y = mahaf.getY() + mahaf.getHeight() / 2;
		if (x < 0 || x > DRAWING_WIDTH || y < 0 || y > DRAWING_HEIGHT)
			mahaf = new Player(380, 0);
	}

	public void checkCollision() {
    if(mahaf.getX() + mahaf.getWidth() >= virus.getX() && 
        mahaf.getX() <= virus.getX() &&
        mahaf.getY() + mahaf.getHeight() >= virus.getY() && 
        mahaf.getY() <= virus.getY()) {
      
      collision = true;
    } else {
      collision = false;
    }
      
  }
	
	
	
	
	public static void main(String[] args) {
		JFrame w = new JFrame("PROgrammerS");
		w.setBounds(100, 100, 640, 480);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Panel panel = new Panel();
		w.addKeyListener(panel);
		w.add(panel);
		w.setResizable(false);
		w.setVisible(true);
		panel.run();
	}

}
