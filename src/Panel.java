
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
	private int groundRunsToSkip;
	// private boolean collision;

	public Panel() {
		super();
		upKeyPressed = false;

		runsToSkip = 0xFFB;
		cloudsRunsToSkip = 0xFFFF;
		groundRunsToSkip= 0xFFFF;

		mahaf = new Player(40, 480);
		virus = new Obstacle(740, 480);
		cloud = new Clouds(400, 20);
		cloud1= new Clouds(200, 20);
		grass= new Ground (0,740);
		mahaf = new Player(340, 300);
		virus = new Obstacle(740, 300);
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
