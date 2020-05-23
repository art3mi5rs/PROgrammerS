//Name: Shachaf Smith, Mana Nagampalli
//Date:05/03/2020
//Ver:1
//Notes: This class is a subclass of sprite and extends all methods in sprite and contains the additional code for 
//mask in game

public class Mask extends Sprite {

    private int orgX;
    private int orgY;
    private double curX;
    
    //Constructor
    public Mask(int x, int y) {
      super("mask.png",x,y,39,39);
      orgX = x;
      curX = x;
      orgY = y;
    }
    
    //Moves the mask left
    public void circularleftShift() {
      if (curX >= 0) {
        moveByAmount(-1, 0);
        curX -= 1;
      }
      else{
        moveToLocation(orgX, orgY);
        curX = orgX;
      }
    }
}
 
