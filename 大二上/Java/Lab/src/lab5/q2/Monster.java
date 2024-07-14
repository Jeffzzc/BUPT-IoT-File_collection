package lab5.q2;

public abstract class Monster {
    String name;
    double spAttackProbability = 0.2;

    public abstract int specialAttack();
    public final int attack(){
        int damage;
        // 题目要求 如果生成的随机数小于spAttackPropability 则使用specialAttack()，否则 使用 常规攻击
        double probability = Math.random();
        if (probability < spAttackProbability) {
            damage = specialAttack();
        }
        else {
            damage = (int) (Math.random() * 5 + 1);
            System.out.println(this.name + ", " + "of type" + getClass() + ", attacks generically: " + damage
                    + " points damage caused.");
        }
        return damage;
    }
    public void move(int direction) {
        switch(direction) {
            case 1:
                System.out.println(this.name + " is moving 1 step NORTH.");
                break;
            case 2:
                System.out.println(this.name + " is moving 1 step EAST.");
                break;
            case 3:
                System.out.println(this.name + " is moving 1 step SOUTH.");
                break;
            default:
                System.out.println(this.name + " is moving 1 step WEST.");
                break;
        }
    }
}
