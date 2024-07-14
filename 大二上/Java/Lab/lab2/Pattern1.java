public class Pattern1{
    public static void main(String[] args){
        int num = Integer.parseInt(args[0]);
        int i,j;
        for(i = 1;i <= num;i++){
            for(j = 1;j <= i;j++){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}