package lab5.q1;
public class Dragon extends Monster{

    public Dragon(String name){
        super(name);
    }

    @Override
    public int attack(){
        int prob = (int)(Math.random() * 10 + 1);
        int damage;
        if(prob <= 3){
            damage = (int) (Math.random() * 50 + 1);
            System.out.println(this.name + " ," + "of type" + getClass() + ", attacks by breathing fire: " + damage
                    + " points damage caused.");
        }
        else{
            damage = super.attack();
        }
        return damage;
    }
    
}