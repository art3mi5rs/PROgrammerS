
//Name: Shachaf Smith, Mana Nagampalli
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

	private boolean upKeyPressed;

	public Panel() {
		super();
		mahaf = new Player(40, 0);
		virus = new Obstacle(30, 0);
		setBackground(Color.ORANGE);
	}

	public void run() {
		while (true) {
			if (upKeyPressed) {
				mahaf.jump();
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

		g2.setTransform(at);
	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			mahaf.jump();
			upKeyPressed = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
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

	// As your program grows, you may want to...
	// 1) Move this main method into its own 'main' class
	// 2) Customize the JFrame by writing a class that extends it, then creating
	// that type of object in your main method instead
	public static void main(String[] args) {
		JFrame w = new JFrame("Simple Window");
		w.setBounds(100, 100, 640, 480);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Panel panel = new Panel();
		w.addKeyListener(panel);
		w.add(panel);
		w.setResizable(true);
		w.setVisible(true);
		panel.run();
	}

}

// Date:05/03/2020
// Ver:1
// Notes: The main class which has all the components to print the backgrounds
// and run the project along with any additional
// features that we may add in the future
