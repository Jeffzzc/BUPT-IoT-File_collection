package q2;

public class Dragon extends Monster {
    public Dragon(String name) {
        this.name = name;
    }

    public Dragon(String name, double spAttackPropability) {
        this.name = name;
        this.spAttackProbability = spAttackPropability;
    }

    @Override
    public int specialAttack() {
        int x = (int) (Math.random() * 50 + 1);
        System.out.println(this.name + " ," + "of type" + getClass() + ", attacks by breathing fire: " + x
                + " points damage caused.");
        return x;
    }
}
