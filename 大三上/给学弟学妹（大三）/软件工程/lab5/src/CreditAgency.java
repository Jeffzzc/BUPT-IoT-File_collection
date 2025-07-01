import java.util.HashMap;

public class CreditAgency {
    HashMap<Integer, Boolean> credits = new HashMap<>();

    void initialize() {
        credits.put(1234, true);
        System.out.println("credits: " + credits.get(1234));
    }
}
