public class CounterTest{
    
    public static void main(String[] args){
        Counter c = new Counter();
        c.increase();
        String s = c.toString();
        System.out.println(s);

        c.decrease();
        s = c.toString();
        System.out.println(s);

        c.doubler();
        s = c.toString();
        System.out.println(s);

        c.reset();
        s = c.toString();
        System.out.println(s);
        

    }
}