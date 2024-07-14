
import java.util.Random;

public class ThreeRollingDice {
    public static void main(String[] args) {
        Random random = new Random();
        int countRolls = 0;
        boolean allSixes;

        do {
            int die1 = random.nextInt(6) + 1; // Generate a random number for die 1
            int die2 = random.nextInt(6) + 1; // Generate a random number for die 2
            int die3 = random.nextInt(6) + 1; // Generate a random number for die 3
            countRolls++; // Increment the count of rolls
            allSixes = (die1 == 6) && (die2 == 6) && (die3 == 6);
            
            // Uncomment the following line to see the output of each roll
            // System.out.println("Roll " + countRolls + ": " + die1 + ", " + die2 + ", " + die3);
        } while (!allSixes);

        System.out.println("It took " + countRolls + " rolls to get three sixes.");
    }
}
