import java.awt.Color;

/**
 * 
 *
 * @author   Ling Ma
 * @date     Jan 19, 2009    
 * @version  1.0
 * @author   Jeff Zuo
 * @date     Mar 26, 2024    
 * @version  1.5 
 */
public class CatTest2 {
    public static void main(String[] args) {
        Cat[] array = new Cat[6];
        for(int i=0;i<6;i++){
            array[i] = new Cat("Tom", Color.BLACK, 500, "short", true);

        }
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}