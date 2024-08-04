public class TestSomeThing {
  public static void main(String[] args) {
    SomeThing myThing = new SomeThing();

    System.out.println("a = " + myThing.getA());

    int out = myThing.doIt();
    System.out.println(out);
    // System.out.println("a = " + myThing.getA());
  }
}