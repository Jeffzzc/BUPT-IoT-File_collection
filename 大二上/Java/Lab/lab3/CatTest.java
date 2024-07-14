import java.awt.*;

/**
 * Title      : CatTest.java
 * Description: This class contains the test class for Cat.
 * Copyright  : Copyright (c) 2006 - 2024
 * @author  Laurissa Tokarchuk
 * @version 1.0
 * @author  Paula Fonseca
 * @version 1.4
 */
public class CatTest {
    public static void main(String[] args) {
        //ii)
        /* 
        Cat myCat = new Cat("Napoleon",Color.ORANGE,300,"short",true);
        myCat.setName("Napoleon");
        myCat.setfurType("short");
	
        
	myCat.sleep(5);
        int numMetres = myCat.run(10, true);
	System.out.println("I have run " + numMetres + " metres.");
        
        */

        //iii)

    //     Cat myCat = new Cat("Napoleon",Color.ORANGE,300,"short",true);
    //     myCat.getName();
    //     myCat.getSpeed();
    //     myCat.sleep(5);
    //     int numMetres = myCat.run(10, true);
    // System.out.println("I have run " + numMetres + " metres.");

        //iv)
        Cat cat1 = new Cat("Tom", Color.BLACK,500,"short",true);
        Cat cat2 = new Cat("Moggy", Color.WHITE, 400, "long", false);
        cat1.getName();
        cat1.getColor();
        int numMeters1 = cat1.run(10,false);
        System.out.println("I have run " + numMeters1 + " meters.");

        cat2.getName();
        cat2.getColor();
        int numMeters2 = cat2.run(5,true);
        System.out.println("I have run " + numMeters2 + " meters.");
    }
}