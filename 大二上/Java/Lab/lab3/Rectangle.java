/**
 * Title         : Rectangle.java 
 * Description   : This class contains the def of a rectangle.
 * @author       : Jeff Zuo
 * @version      : 1.0
 */

public class Rectangle{
    private int l;
    private int w;

    public Rectangle(int l,int w){
        this.l = l;
        this.w = w;
    }

    /** This is the area calculate method for rectangle.
     * @param l  The length of the rectangle.
     * @param w  The width of the rectangle.
     * @return int The area of the rectangle.
     */
    public int calculateArea(){
        return l * w;
    }

    public static void main(String[] args){
        Rectangle rec1 = new Rectangle(8,6);
        Rectangle rec2 = new Rectangle(7,7);
        int area1 = rec1.calculateArea();
        System.out.println(area1);
        int area2 = rec2.calculateArea();
        System.out.println(area2);
    }
}