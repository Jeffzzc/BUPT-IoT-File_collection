public class test1 {
    static int ss = 99;
    int b = 10;
    public static void main(String[] args){
        test1 a = new test1();
        a.b = 1;
        int total = a.b + test1.ss;
        System.out.println(total);
        
        Integer c = a.b;
        System.out.println(c);
    }
}
