
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

  public static final int DRAWING_WIDTH = 800;
  public static final int DRAWING_HEIGHT = 600;

  private Player mahaf;
  private Obstacle virus;
  private Clouds cloud;
  private Clouds cloud1;
  private Ground grass;
  private Timer virusTimer;
  private Timer cloudsTimer;
  private Timer pointsTimer;

  private boolean collision;
  private int points;

  public Panel() {
    super();
    collision = false;

    mahaf = new Player(40, 480);
    virus = new Obstacle(740, 480);
    cloud = new Clouds(900, 20);
    cloud1 = new Clouds(740, 20);
    grass = new Ground(0, 520);
    mahaf = new Player(230, 480);
    virus = new Obstacle(780, 480);
    virusTimer = new Timer("virusTimer");
    cloudsTimer = new Timer("cloudsTimer");
    pointsTimer = new Timer("pointsTimer");
    setBackground(Color.CYAN);

  }

  public void runWithTimer() {
    runVirus();
    runClouds(cloud, 55L);
    runClouds(cloud1, 42L);
    runPoints();

  }

  private void runVirus() {
    TimerTask virusTask = new TimerTask() {
      @Override
      public void run() {
        virus.circularleftShift();
        collision = checkCollision();
        if (collision) {
          virusTimer.cancel();
        }
        repaint();
      }

    };
    virusTimer.scheduleAtFixedRate(virusTask, 1000L, 10L);
  }

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

    g.setColor(Color.BLACK);
    g.setFont(new Font("SansSerif", Font.BOLD, 12));
    FontMetrics fm = g.getFontMetrics();
    String p = "points: " + points;
    g.drawString(p, width - fm.stringWidth(p) - 10, 20);

    if (collision) {
      g.setColor(Color.BLACK);
      g.setFont(new Font("SansSerif", Font.BOLD, 30));
      fm = g.getFontMetrics();
      String s = "You caught the virus! GAME OVER";
      g.drawString(s, width / 2 - fm.stringWidth(s) / 2, height / 2);
    }

  }

  public void keyPressed(KeyEvent e) {
    if (collision) {
      return;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE) {
      mahaf.jump();
    }
  }

  public void keyReleased(KeyEvent e) {
    //method needed by key listener
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
        && mahaf.getY() + mahaf.getHeight() + 30 >= virus.getY() && mahaf.getY() <= virus.getY()

        ||

        virus.getX() + virus.getWidth() - 30 >= mahaf.getX() && virus.getX() <= mahaf.getX()
            && mahaf.getY() + mahaf.getHeight() + 30 >= virus.getY() && mahaf.getY() <= virus.getY()) {

      return true;
    }

    return false;

  }

  public int pointIncrement(int n) {
    points += n;
    return points;
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
    panel.runWithTimer();
  }

}
