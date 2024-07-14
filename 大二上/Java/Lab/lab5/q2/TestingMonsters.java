package q2;

import java.util.*;

/**
 * Title : TestingMonsters.java
 * Description: This class is the test class for Monsters.
 * 
 * @author Kevin Wang
 * @version 1.0
 */
public class TestingMonsters {
    public static void main(String[] args) {
        ArrayList<Monster> monsters = new ArrayList<>();

        monsters.add(new Dragon("Dragon1"));
        monsters.add(new Dragon("Dragon2"));

        monsters.add(new Troll("Troll1"));
        monsters.add(new Troll("Saul", 0.5));

        int damageDone = 0;
        while (damageDone < 100) {
            for (Monster m : monsters) {
                m.move((int) (Math.random() * 4) + 1);
                damageDone += m.attack();
            }
        }
    }
}