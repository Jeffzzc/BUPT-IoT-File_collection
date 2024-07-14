package lab4;

import java.awt.*;
import java.util.ArrayList;

public class CatTest3 {
    public static void main(String[] args) {
        ArrayList<Cat> list = new ArrayList<Cat>(5);

        Cat cat1 = new Cat("Tom", Color.BLACK,500,"short",true);
        Cat cat2 = new Cat("Mike", Color.white,100,"long",true);
        Cat cat3 = new Cat("Lily", Color.red,40,"short",true);
        Cat cat4 = new Cat("Jack", Color.blue,200,"long",false);
        Cat cat5 = new Cat("Dory", Color.yellow,700,"short",false);

        list.add(cat1);
        list.add(cat2);
        list.add(cat3);
        list.add(cat4);
        list.add(cat5);

        System.out.println(list.get(4));
        System.out.println(list.size());
        list.remove(3);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
