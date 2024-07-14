/**
 * Title      : Counter.java
 * Description: This class contains the definition of a counter.
 * @author      Jeff Zuo
 * @version     1.0
 */

public class Counter{
    private int count;
    private int max;

    public Counter(){
        this.count = 0;
        this.max = 10;
    }

    public void setCount(int n){
        this.count = n;
    }
    public void setMax(int n){
        this.max = n;
    }
    public int getCount(){
        return this.count;
    }
    public int getMax(){
        return this.max;
    }
    
    public void increase(){
        this.count += 2;
        if(count > max){
            reset();
        }
    }
    public void decrease(){
        if(count > 0) this.count -= 1;
    }
    public void doubler(){
        this.count *= 2;
    }
    public void reset(){
        this.count = 0;
        System.out.println("Counter reset!");
    }
    public void increase(int n) {
        count += n;
        if(count > max)
            reset();
    }
    public void decrease(int n) {
        if(count > 0)
            count -= n;
    }
    public String toString(){
        return "count: " + count + " max: " + max;
    }
}