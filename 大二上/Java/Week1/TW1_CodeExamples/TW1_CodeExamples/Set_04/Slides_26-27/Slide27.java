public class Slide27 {
  public static void main (String args[]) {
    // char grade = 'a';
    char grade = 'c';
    switch (grade) {
      case 'a':
        System.out.println("excellent");
      case 'b':
        System.out.println("good");
      case 'c':
        System.out.println("not bad");
      case 'd':
        System.out.println("bad");
      default:
	System.out.println("no such grade!");
    }
  }
}
