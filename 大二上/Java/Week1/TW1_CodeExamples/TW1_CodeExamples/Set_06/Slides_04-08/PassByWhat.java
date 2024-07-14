public class PassByWhat {
  void go(int z) {
    // do somethings
    z = z + 7;
    System.out.println("z = " + z);
  }

  public static void main(String[] args) {
    PassByWhat p = new PassByWhat();
    int x = 7;
    System.out.println("x before go() call = " + x);
         
    p.go(x);
    System.out.println("x after go() call  = " + x);
    // what is x now? 
  }
}