public class SomeMaths { 
  public int doSomeMaths(int x, int y) {
    int temp;
    if (x > y)      temp = x * y;
    else if (x < y) temp = x + y;
    else            temp = x;
    return temp;
  }
}