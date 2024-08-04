public class TestTurtle {
  public static void main(String[] args) {
    Turtle myTurtle = new Turtle("Sam", "fluffy", 3);
    Turtle anotherTurtle = new Turtle("John", "scaly", 1);

    System.out.println(anotherTurtle.getName());
    System.out.println(anotherTurtle.getTailType());
    System.out.println(anotherTurtle.getSpeed());
  }
}
