import java.awt.*;

public class Turtle {
  private String name;
  private String tailType;
  private Color colour;
  private int speed;

  public Turtle(String name, String tailType, int speed) {
    this.setName(name);
    this.setTailType(tailType);
    this.setSpeed(speed);
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public String getName() { return name; }

  public void setTailType(String tailType) {
    if (!tailType.equals("scaly"))
      System.out.println("ERROR: Illegal tail type.");
    else this.tailType = tailType;
  }

  public String getTailType() { return tailType; }

  public void setSpeed(int speed) {
    if (speed > 2)
      System.out.println("ERROR: Illegal speed.");
    else this.speed = speed;
  }

  public int getSpeed() { return speed; }

  public int run() {
    // code of run; e.g. print message "I’m running";
    return 0;
  }

  public void swim() {
    // code of swim; e.g. print message "I’m swimming"
  }
}
