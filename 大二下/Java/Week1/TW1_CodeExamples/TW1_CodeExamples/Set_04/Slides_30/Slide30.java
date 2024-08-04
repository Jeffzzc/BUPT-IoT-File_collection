public class Slide30 {
  public static void main (String[] args) {
    System.out.println("== 'do .. while' loop ==");
    int i = 0;
    do {
      System.out.println("i = " + i);
      i++;
    } while (i < 3);

    System.out.println("== 'while' loop ==");
    int j = 0;
    while (j < 3) {
      System.out.println("j = " + j);
      j++;
    }

    System.out.println("== 'for' loop ==");
    for (int k = 0; k < 3; k++)
      System.out.println("k = " + k);       
  }
}
