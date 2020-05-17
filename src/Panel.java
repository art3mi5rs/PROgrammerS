
//Name: Shachaf Smith, Mana Nagampalli
// Date:05/03/2020
// Ver:1
// Notes: The main class which has all the components to print the backgrounds
// and run the project along with any additional
// features that we may add in the future

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
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
	private Music jump;
	private Timer clock;
	
	

	private boolean upKeyPressed;
	private boolean upKeyReleased;

	private int points;
	private boolean collision;
	

	
	class VirusRunnerTask extends TimerTask 
	{ 

	    public void run() 
	    { 
	    	virus.circularleftShift();
	    } 
	}
	
	class PlayMusicTask extends TimerTask 
	{ 

	    public void run() 
	    {
	    	if (upKeyPressed && !upKeyReleased) {
	    		jump.play();
	    	}
	    } 
	}
	
	class CloudRunnerTask extends TimerTask 
	{ 

	    public void run() 
	    { 
	    	cloud.circularLeftShift();
			cloud1.circularLeftShift();
	    } 
	}

	public Panel() {
		super();
		upKeyPressed = false;
		upKeyReleased = false;
		points = 1;
		collision = false;


	//	mahaf = new Player(40, 480);
	//	virus = new Obstacle(740, 480);
		cloud = new Clouds(400, 20);
		cloud1 = new Clouds(200, 20);
		grass = new Ground(0, 520);
		mahaf = new Player(230, 480);
		virus = new Obstacle(780, 480);
		jump = new Music("jump.wav");
		clock = new Timer();
		
		
		setBackground(Color.CYAN);
	}

	public void run() {
		
		Timer timer = new Timer(true);
		TimerTask virustask = new VirusRunnerTask();
		TimerTask cloudtask = new CloudRunnerTask();
		TimerTask playmusictask = new PlayMusicTask();
        timer.scheduleAtFixedRate(virustask, 0, 10);
        timer.scheduleAtFixedRate(cloudtask, 0, 100);
        timer.scheduleAtFixedRate(playmusictask, 0, 1);
        
        Timer playtimer = new Timer (true);
	
	
		while (true) {

			if (collision) {
				break;
			}

			if (upKeyPressed) {
				upKeyPressed = false; //reset the upkey press flag
				mahaf.jump();
				
			} 
			else if (upKeyReleased) {
				upKeyReleased = false; //reset the upkey press flag
				mahaf.comeToSurface();
				pointIncrement(10); // Players receive ten points for completing
									// a jump
			}
			
			checkCollision();
			 
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

		g.setColor(Color.BLACK);
		g.setFont(new Font("SansSerif", Font.BOLD, 12));
		FontMetrics fm = g.getFontMetrics();
		/*String p = "points: " + points;
		g.drawString(p, width - fm.stringWidth(p) / 2, 20);
		*/
		

		if (collision) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("SansSerif", Font.BOLD, 30));
			fm = g.getFontMetrics();
			String s = "You caught the virus! GAME OVER";
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
			upKeyReleased = true;
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
		if (mahaf.getX() + mahaf.getWidth() >= virus.getX() && mahaf.getX() <= virus.getX()
				&& mahaf.getY() + mahaf.getHeight() >= virus.getY() && mahaf.getY() <= virus.getY()

				||

				virus.getX() + virus.getWidth() >= mahaf.getX() && virus.getX() <= mahaf.getX()
						&& mahaf.getY() + mahaf.getHeight() >= virus.getY() && mahaf.getY() <= virus.getY()) {

			collision = true;
		} else {
			collision = false;
		}

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
		panel.run();
	}
}



 