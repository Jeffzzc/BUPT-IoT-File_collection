public class SomeThing {
  private int a, b = 12;

  public int getA() {
    return a;
  }

  public int doIt() {
    int total = 1;
 
    a = total * 2; 
    if (b < 20) 
      total = a + b;
    return total;
  }
} 