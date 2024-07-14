package q2;

public class Troll extends Monster {
    public Troll(String name) {

        if (name.equals("Saul") || name.equals("Salomon")) {
            System.out.println("Error: name is illegal.");
            this.name = "Detritus";
        } else {
            this.name = name;
        }
    }

    public Troll(String name, double spAttackPropability) {
        this(name);
        this.spAttackProbability = spAttackPropability;
    }

    @Override
    public int specialAttack() {
        int x = (int) (Math.random() * 15 + 1);
        System.out.println(this.name + " ," + "of type" + getClass() + ", attacks by hitting with a stick " + x
                + " points damage caused.");
        return x;
    }
}
