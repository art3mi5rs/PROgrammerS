
package lab_finalcapstoneproject;

import java.awt.*;
import javax.swing.JFrame;

public class Panel {

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
