class Animal{
    private String name;
    private int age;

    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
}

abstract class Canine extends Animal{
    public abstract void roam();
}

public class makeCanine{
    public static void main(String[] args){
        Canine c = new Dog();
        c.roam();
        
    }
}

class Dog extends Canine{
    public void roam(){
        System.out.println("Wang!!!!");
    }
}

