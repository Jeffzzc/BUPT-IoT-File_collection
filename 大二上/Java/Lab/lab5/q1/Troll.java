package q1;

public class Troll extends Monster {
    public Troll(String name) {
        super(name);
        if ("Saul".equals(name) || "Salomon".equals(name)) {
            System.out.println("Wrong name!");
            this.name = "Detritus";
        }
    }

}