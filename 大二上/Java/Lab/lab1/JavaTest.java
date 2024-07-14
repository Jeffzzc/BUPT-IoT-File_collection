public class JavaTest {
    public static void main(String[] args) {
        int loopUntil = Integer.parseInt(args[0]);
        for (int i=0; i < loopUntil; i++) {
            System.out.print(" " + i);
            System.out.print(":");
            for (int j=loopUntil; j > 0; j--) {
                if (((i + j) % 3) == 0) {
                    System.out.print("*");
                }
                else{
                    System.out.print(j);
                }
            }
            System.out.println();
        }
    }
}
