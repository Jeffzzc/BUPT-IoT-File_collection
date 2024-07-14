package lab5.q2;

import java.util.*;

/**
 * Title : TestingMonsters.java
 * Description: This class is the test class for Monsters.
 * 
 * @author Zecheng Zuo
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

/*
 * After all these changes, you will notice that the provided TestingMonsters
 * class will
 * no longer work. Determine the reason for this and fix the code in this class
 * so that you can
 * run your modified Java application.
 * 怎么回答
 * ChatGPT
 * 根据提供的实验要求和描述，我们需要对 Monster 类及其子类进行一些修改。以下是详细步骤及其原因，并包括如何修改 TestingMonsters
 * 类以使其正常运行。
 * 
 * 修改 Monster 类
 * 将 Monster 类设为抽象类：
 * java
 * 复制代码
 * package q2;
 * 
 * public abstract class Monster {
 * public String name;
 * public double spAttackProbability;
 * 
 * public Monster(String name) {
 * this.name = name;
 * this.spAttackProbability = 0.2;
 * }
 * 
 * public Monster(String name, double spAttackProbability) {
 * this.name = name;
 * this.spAttackProbability = spAttackProbability;
 * }
 * 
 * public final int attack() {
 * if (Math.random() < spAttackProbability) {
 * return specialAttack();
 * } else {
 * int x = (int) (Math.random() * 5 + 1);
 * System.out.println(this.name + ", of type " + this.getClass() +
 * ", attacks generically: " + x + " points damage caused.");
 * return x;
 * }
 * }
 * 
 * public void move(int direction) {
 * switch (direction) {
 * case 1:
 * System.out.println(this.name + " is moving 1 step NORTH.");
 * break;
 * case 2:
 * System.out.println(this.name + " is moving 1 step EAST.");
 * break;
 * case 3:
 * System.out.println(this.name + " is moving 1 step SOUTH.");
 * break;
 * default:
 * System.out.println(this.name + " is moving 1 step WEST.");
 * break;
 * }
 * }
 * 
 * public abstract int specialAttack();
 * }
 * 修改 Dragon 类
 * 实现 Dragon 类的 specialAttack 方法：
 * java
 * 复制代码
 * package q2;
 * 
 * public class Dragon extends Monster {
 * public Dragon(String name) {
 * super(name);
 * }
 * 
 * public Dragon(String name, double spAttackProbability) {
 * super(name, spAttackProbability);
 * }
 * 
 * @Override
 * public int specialAttack() {
 * int damage = (int) (Math.random() * 50 + 1);
 * System.out.println(this.name + ", of type " + this.getClass() +
 * ", breathes fire: " + damage + " points damage caused.");
 * return damage;
 * }
 * }
 * 修改 Troll 类
 * 实现 Troll 类的 specialAttack 方法，并处理名称限制：
 * java
 * 复制代码
 * package q2;
 * 
 * public class Troll extends Monster {
 * public Troll(String name) {
 * super(name);
 * if ("Saul".equals(name) || "Salomon".equals(name)) {
 * System.out.println("Wrong name!");
 * this.name = "Detritus";
 * }
 * }
 * 
 * public Troll(String name, double spAttackProbability) {
 * super(name, spAttackProbability);
 * if ("Saul".equals(name) || "Salomon".equals(name)) {
 * System.out.println("Wrong name!");
 * this.name = "Detritus";
 * }
 * }
 * 
 * @Override
 * public int specialAttack() {
 * int damage = (int) (Math.random() * 15 + 1);
 * System.out.println(this.name + ", of type " + this.getClass() +
 * ", hits with a stick: " + damage + " points damage caused.");
 * return damage;
 * }
 * }
 * 修改 TestingMonsters 类
 * 修改 TestingMonsters 类以适应新修改的 Monster 类：
 * java
 * 复制代码
 * 
 * public class TestingMonsters {
 * public static void main(String[] args) {
 * ArrayList<Monster> monsters = new ArrayList<>();
 * 
 * monsters.add(new Dragon("Dragon1"));
 * monsters.add(new Dragon("Dragon2"));
 * 
 * monsters.add(new Troll("Troll1"));
 * monsters.add(new Troll("Saul"));
 * 
 * int damageDone = 0;
 * while (damageDone < 100) {
 * for (Monster m : monsters) {
 * m.move((int) (Math.random() * 4) + 1);
 * damageDone += m.attack();
 * }
 * }
 * }
 * }
 * 总结
 * 上述修改确保了 Monster 类为抽象类，并强制子类实现 specialAttack 方法。同时，Dragon 和 Troll 类正确地实现了自己的
 * specialAttack 方法，并且 TestingMonsters 类能够正常运行。
 * 
 * 希望这些修改能解决你的问题，如果还有其他问题，请随时告知。
 */