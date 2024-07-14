public class Patterns{
    public static void main(String[] args){
        int w = Integer.parseInt(args[0]);
        Patterns pattern = new Patterns();
        pattern.printPattern1(w);
        pattern.printPattern2(w);
    }
    public void printPattern1(int n){
        int i = 1;
        for(;i <= n;i++){
            int j = 1;
            for(;j <= i;j++){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
    public void printPattern2(int n){
        int i = n;
        for(; i > 0; i--)
        {
            int j = 1;
            for(; j <= i; j++)
            {
                System.out.print(" " + j);
            }
            System.out.println();
        }
    }
}
