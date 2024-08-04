/**
  * ExampleTypeCast: prints an example of type
  * @author Raul Mondragon
  */
public class ExampleTypeCast {
  public static void main (String[] args) {
    double x = 6.8;
    int i, j;
    j = (int)(x + 1.3);
    i = (int)x + (int)1.3;
    System.out.println("j = " + j + "," + "i = " + i);
  }
}
