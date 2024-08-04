public class Test {
  public static void main(String[] args) {
    int i = 5;
    while (i >= 1) {
      int num = 1;
      for (int j = i; j <= i; j++) { 
        System.out.print(num + "xxx");
        num *= 2;
      }
      System.out.println();
      i--;
    }
  }
}
