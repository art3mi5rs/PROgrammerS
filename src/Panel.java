//Name: Shachaf Smith, Mana Nagampalli
//Date:05/03/2020
//Ver:1
//Notes: The main class which has all the components to print the backgrounds and run the project along with any additional 
//features that we may add in the future
import java.awt.*;
import javax.swing.JFrame;

public class Panel {
  // panel needs to also paint a background
  
  Player player = new Player(30, 50);
  Obstacle obstacle = new Obstacle(40, 50);

  public static void main(String[] args)
  {
    JFrame w = new JFrame("Panel");
    w.setBounds(100, 100, 640, 480);
    w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Panel panel = new Panel();
    Container c = w.getContentPane();
    w.add(c);
    w.setVisible(true);
  }
}
