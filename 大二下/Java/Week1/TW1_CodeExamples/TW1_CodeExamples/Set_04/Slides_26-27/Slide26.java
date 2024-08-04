public class Slide26 {
  public static void main (String args[]) {
    char grade = 'c';
    switch (grade) {
      case 'a':
        System.out.println("excellent");
        break;
      case 'b':
        System.out.println("good");
        break;
      case 'c':
        System.out.println("not bad");
        break;
      case 'd':
        System.out.println("bad");
        break;
      default:
	System.out.println("no such grade!");
    }
  }
}
