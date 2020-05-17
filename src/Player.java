import java.util.Timer;
import java.util.TimerTask;

//Name: Shachaf Smith, Mana Nagampalli
//Date:05/03/2020
//Ver:1
//Notes: This class is a subclass of sprite and extends all methods in sprite and contains the additional code for 
//player in game

public class Player extends Sprite {

  int count;

  // Constructor
  public Player(int x, int y) {
    super("running.png", x, y - 25, 80, 100);
    int count = 0;

  }

  // Methods
  public void jump() {
    Timer jumpTimer = new Timer("jumpTimer");


    TimerTask jumpTask = new TimerTask() {
      public void run() {
        count++;
        moveByAmount(0, -1);
        if (count >= 100) {
          jumpTimer.cancel();
          comeToSurface();
        }
      }
    };
    jumpTimer.scheduleAtFixedRate(jumpTask, 0L, 10L);

  }

  public void comeToSurface() {
    Timer downTimer = new Timer("downTimer");

    TimerTask downTask = new TimerTask() {
      public void run() {
        count--;
        moveByAmount(0, 1);
        if (count <= 0) {
          downTimer.cancel();
        }
      }
    };
    downTimer.scheduleAtFixedRate(downTask, 0L, 10L);

  }
}
