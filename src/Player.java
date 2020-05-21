import java.util.Timer;
import java.util.TimerTask;

//Name: Shachaf Smith, Mana Nagampalli
//Date:05/03/2020
//Ver:1
//Notes: This class is a subclass of sprite and extends all methods in sprite and contains the additional code for 
//player in game

public class Player extends Sprite {

  int count;
  private Music jump;

  // Constructor
  public Player(int x, int y) {
    super("running.png", x, y - 25, 80, 100);
    count = 0;
    jump = new Music("jump.wav");

  }

//Makes the player jump and adds music  
  public void jump() {
    Timer jumpTimer = new Timer("jumpTimer");
    Timer musicTimer = new Timer("musicTimer");
    
    TimerTask musicTask = new TimerTask() {
      public void run() {
        jump.play();
      }
    };
    musicTimer.schedule(musicTask, 0L);

    TimerTask jumpTask = new TimerTask() {
      public void run() {
        count++;
        moveByAmount(0, -1);
        if (count >= 150) {
          jumpTimer.cancel();
          comeToSurface();
        }
      }
    };
    jumpTimer.scheduleAtFixedRate(jumpTask, 0L, 10L);

  }

  //Returns the player to the surface
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
