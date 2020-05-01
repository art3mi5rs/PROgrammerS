<<<<<<< HEAD
=======
package lab_finalcapstoneproject;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;

import javax.swing.JFrame;

public class Panel {
  
  public static void main(String[] args)
  {
    JFrame w = new JFrame("Panel");
    w.setBounds(100, 100, 640, 480);
    w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Panel panel = new Panel();
    //panel.setBackground(Color.WHITE);
    Container c = w.getContentPane();
    c.add(panel);
    w.setVisible(true);
  }
}
>>>>>>> parent of eae6fa6... this is bad
