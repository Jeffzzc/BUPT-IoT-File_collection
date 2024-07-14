public class TestingFlower {
  public static void main(String[] args) {
    Flower aFlower = new Flower("Pink", 1200);
    Flower anotherFlower = new Flower("Purple", -201);    

    System.out.println("Height of aFlower = " + aFlower.getHeight());
    System.out.println("Height of anotherFlower = " + anotherFlower.getHeight());
  }
}