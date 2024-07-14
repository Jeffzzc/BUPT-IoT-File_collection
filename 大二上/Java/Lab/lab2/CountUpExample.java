/**
 * @author Zecheng Zuo
 * @version 1.0
 */

public class CountUpExample{
    /**
     * This method counts up from zero to a specific number.
     * Print to the command line.
     * @param count The number to count to
     */
    
    public void countUp(int count){
        for(int i = 0;i <= count;i++){
            System.out.println(i);
        }
        System.out.println("\nAll done!");
    }
    
    
    /**
     * Main function creates a new instance of my program
     * 
     * @param args I don't use this parameter.
     */
    public static void main(String[] args){
        CountUpExample counter = new CountUpExample();
        counter.countUp(5);
    }
}