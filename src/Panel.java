
//Name: Shachaf Smith, Mana Nagampalli
// Date:05/03/2020
// Ver:1
// Notes: The main class which has all the components to print the backgrounds
// and run the project along with any additional
// features that we may add in the future

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Panel extends JPanel implements KeyListener {

	// Fields
	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 600;

	private Player mahaf;
	private Obstacle virus;
	private Mask mask;
	private Clouds cloud;
	private Clouds cloud1;
	private Clouds cloud2;
	private Clouds cloud3;
	private Ground grass;
	private Timer virusTimer;
	private Timer maskTimer;
	private Timer cloudsTimer;
	private Timer pointsTimer;
	private Timer backgroundTimer;
	private Music background;
	private Music gameOver;

	private boolean collision;
	private boolean hasMask;
	private boolean prevMask;
	private int points;

	// Constructor
	public Panel() {
		super();
		collision = false;
		hasMask = false;
		prevMask = false;

		mahaf = new Player(40, 480);
		virus = new Obstacle(740, 480);
		mask = new Mask(780, 400);
		cloud = new Clouds(740, 20);
		cloud1 = new Clouds(900, 20);
		cloud2 = new Clouds(1060, 80);
		cloud3 = new Clouds(1200, 50);
		grass = new Ground(0, 520);
		mahaf = new Player(230, 480);
		virus = new Obstacle(780, 480);
		virusTimer = new Timer("virusTimer");
		maskTimer = new Timer("maskTimer");
		cloudsTimer = new Timer("cloudsTimer");
		pointsTimer = new Timer("pointsTimer");
		backgroundTimer = new Timer("backgroundTimer");
		background = new Music("background.wav");
		gameOver = new Music("gameOver.wav");

		setBackground(Color.CYAN);

	}

	// Runs all of the methods needed
	public void runWithTimer() {
		runVirus();
		runMask();
		runClouds(cloud, 55L);
		runClouds(cloud1, 42L);
		runClouds(cloud2, 49L);
		runClouds(cloud3, 30L);
		runPoints();
		runBackgroundMusic();
	}

	// This method is called in runBackgroundMusic, and is in charge of running code
		// related to the background music
		private void runBackgroundMusic() {
			TimerTask musicTask = new TimerTask() {
				@Override
				public void run() {
					background.play();
					collision = checkCollision();
					if (collision) {
						backgroundTimer.cancel();
					}
					repaint();
				}

			};
			//the period was made 10 millisecinds to make the music appear
			//like it is going on forever
			backgroundTimer.scheduleAtFixedRate(musicTask, 0, 10);
		}

	// This method is called in runWithTimer, and is in charge of running code
	// related to the virus
  private void runVirus() {
    TimerTask virusTask = new TimerTask() {
      @Override
      public void run() {
        virus.circularleftShift();

        if (!prevMask) {
          hasMask = false;
        }

        if (checkCollision() == false && hasMask == true) {
          prevMask = true;
        }

        if (checkCollision()) {
          if (prevMask == true) {
          } else {
          prevMask = false;
          collision = false;
          virusTimer.cancel();
          }
        }

        repaint();
      }

    };
    virusTimer.scheduleAtFixedRate(virusTask, 1000L, 10L);
  }

	// This method is called in runWithTimer, and is in charge of running code
	// related to the mask
	private void runMask() {
		TimerTask maskTask = new TimerTask() {
			@Override
			public void run() {
				mask.circularleftShift();
				hasMask = checkMaskCollision();
				if (collision) {
					maskTimer.cancel();
				}
				repaint();
			}

		};
		maskTimer.scheduleAtFixedRate(maskTask, 3800L, 10L);
	}

	// This method is called in runWithTimer, and is in charge of running code
	// related to the clouds
	private void runClouds(Clouds c, long l) {
		TimerTask cloudsTask = new TimerTask() {
			@Override
			public void run() {
				c.circularLeftShift();
				repaint();
			}

		};
		cloudsTimer.scheduleAtFixedRate(cloudsTask, 1000L, l);
	}

	// This method is called in runWithTimer, and is in charge of running code
	// related to the points system
	private void runPoints() {
		TimerTask virusTask = new TimerTask() {
			@Override
			public void run() {
				pointIncrement(10);
				if (collision) {
					pointsTimer.cancel();
				}
			}

		};
		pointsTimer.scheduleAtFixedRate(virusTask, 1000L, 1000L);
	}

	// In charge of painting all components
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
		if (!hasMask){
		mask.draw(g, this);
		}
		mahaf.draw(g, this);
		cloud.draw(g, this);
		cloud1.draw(g, this);
		cloud2.draw(g, this);
		cloud3.draw(g, this);
		grass.draw(g, this);

		g2.setTransform(at);

		g.setColor(Color.BLACK);
		g.setFont(new Font("SansSerif", Font.BOLD, 12));
		FontMetrics fm = g.getFontMetrics();
		String p = "points: " + points;
		g.drawString(p, width - fm.stringWidth(p) - 10, 20);

		if (collision) {
			g.setFont(new Font("SansSerif", Font.BOLD, 30));
			fm = g.getFontMetrics();
			String s = "You caught the virus! GAME OVER";
			g.drawString(s, width / 2 - fm.stringWidth(s) / 2, height / 2);
			gameOver.play();
		}

		if (hasMask) {
			g.setFont(new Font("SansSerif", Font.BOLD, 12));
			String m = "You have a mask!";
			g.drawString(m, width - fm.stringWidth(p) - 120, 20);
		}

	}

	// Methods of Key Listener, explains what happens when keys are pressed
	public void keyPressed(KeyEvent e) {
		if (collision) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE) {
			mahaf.jump();
		}
	}

	// Methods of Key Listener, explains what happens when keys are released
	public void keyReleased(KeyEvent e) {
		// method needed by key listener
	}

	public void keyTyped(KeyEvent e) {
	}

	public void checkmahaf() {
		int x = mahaf.getX() + mahaf.getWidth() / 2;
		int y = mahaf.getY() + mahaf.getHeight() / 2;
		if (x < 0 || x > DRAWING_WIDTH || y < 0 || y > DRAWING_HEIGHT)
			mahaf = new Player(380, 0);
	}

	public boolean checkCollision() {
		if (mahaf.getX() + mahaf.getWidth() - 30 >= virus.getX() && mahaf.getX() <= virus.getX()
				&& mahaf.getY() + mahaf.getHeight() - 30 >= virus.getY() && mahaf.getY() <= virus.getY()

				||

				virus.getX() + virus.getWidth() - 30 >= mahaf.getX() && virus.getX() <= mahaf.getX()
						&& mahaf.getY() + mahaf.getHeight() - 30 >= virus.getY() && mahaf.getY() <= virus.getY()) {

			return true;
		}

		return false;

	}

	public boolean checkMaskCollision() {
		if (mahaf.getX() + mahaf.getWidth() - 30 >= mask.getX() && mahaf.getX() <= mask.getX()
				&& mahaf.getY() + mahaf.getHeight() - 30 >= mask.getY() && mahaf.getY() <= mask.getY()

				||

				mask.getX() + mask.getWidth() - 30 >= mahaf.getX() && mask.getX() <= mahaf.getX()
						&& mahaf.getY() + mahaf.getHeight() - 30 >= mask.getY() && mahaf.getY() <= mask.getY()) {

			return true;
		}

		return false;

	}

	// used in order to add points
	public int pointIncrement(int n) {
		points += n;
		return points;
	}

	// the main methods
	public static void main(String[] args) {
		JFrame w = new JFrame("PROgrammerS");
		w.setBounds(100, 100, 640, 480);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Panel panel = new Panel();
		w.addKeyListener(panel);
		w.add(panel);
		w.setResizable(false);
		w.setVisible(true);
		panel.runWithTimer();
	}

}
