package Task7;

/**
 * MainApplication class used to create and manage multiple Incrementer threads.
 */
public class MainApplication {

    /**
     * The main method to execute the application.
     *
     * @param args command-line arguments where the first argument specifies the
     *             number of threads
     * @throws InterruptedException if thread execution is interrupted
     */
    public static void main(String[] args) throws InterruptedException {
        int numThreads = 2; // Default number of threads
        int numIncrements = 200; // Number of increments per thread

        // Parse command-line argument to get the number of threads
        if (args.length > 0) {
            try {
                numThreads = Integer.parseInt(args[0]);
                if (numThreads <= 0) {
                    System.out.println("Number of threads must be a positive integer. Using default value 2.");
                    numThreads = 2;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid thread count parameter. Using default value 2.");
                numThreads = 2;
            }
        } else {
            System.out.println("No thread count parameter provided. Using default value 2.");
        }

        // Create a shared Counter instance
        Counter sharedCounter = new Counter();

        // Create an array to hold all thread references
        Thread[] threads = new Thread[numThreads];

        // Initialize and start all Incrementer threads
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Incrementer(sharedCounter, numIncrements, i + 1);
            threads[i].start();
        }

        // Use join() to wait for all threads to complete
        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
        }

        // Calculate the expected final count
        int expectedCount = numThreads * numIncrements;

        // Retrieve the actual final count from the shared Counter
        int actualCount = sharedCounter.getCount();

        // Print the final and expected counts
        System.out.println("Final count is: " + actualCount);
        System.out.println("Expected count is: " + expectedCount);

        // Check if race conditions were observed
        if (actualCount < expectedCount) {
            System.out.println("Race conditions were observed!");
        } else {
            System.out.println("No race conditions were observed.");
        }
    }
}
