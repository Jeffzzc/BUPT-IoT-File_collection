package lab5.q1;
public class Monster{
    public String name;

    public Monster(String name){
        this.name = name;
    }

    public int attack(){
        int x = (int) (Math.random() * 5 + 1);
        System.out.println(this.name + ", of type" + this.getClass() + ", attacks generically: " + x + "points damage caused.");
        return x;
    }

    public void move(int direction) {
        switch(direction) {
            case 1:
                System.out.println(this.name + "is moving 1 step NORTH.");
                break;
            case 2:
                System.out.println(this.name + "is moving 1 step EAST.");
                break;
            case 3:
                System.out.println(this.name + "is moving 1 step SOUTH.");
                break;
            default:
                System.out.println(this.name + "is moving 1 step WEST.");
                break;
        }
    }
}