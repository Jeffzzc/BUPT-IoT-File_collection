public class FlowerTest {
  public static void main (String[] args) {
    Flower f1 = new Flower();
    f1.setPetalColour("Red");
    f1.setHeight(2.5);
    System.out.println("petalColour = " + f1.getPetalColour() + "; height = " + f1.getHeight());

    Flower f2 = new Flower();
    f2.setPetalColour("Blue");
    f2.setHeight(5.5);         // will print off an error    
    f2.setHeight(4.5);         // better!
  }
}