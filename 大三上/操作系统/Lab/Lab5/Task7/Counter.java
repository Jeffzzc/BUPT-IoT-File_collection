package Task7;

/**
 * Counter class used to manage a shared count among multiple threads.
 */
public class Counter {
    // Private variable to store the count, initialized to 0
    private int count = 0;

    /**
     * Synchronized method to get the current count.
     *
     * @return the current count
     */
    public synchronized int getCount() {
        return count;
    }

    /**
     * Synchronized method to increment the count and print the operation.
     *
     * @param name the name of the thread performing the increment
     */
    public synchronized void increment(String name) {
        int currCount = count; // Read the current count

        // Introduce a random delay to simulate work and increase the chance of race
        // conditions (if not synchronized)
        try {
            // Sleep for 0 to 1000 milliseconds
            long sleepTime = (long) (Math.random() * 1000);
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            // Print stack trace if the thread is interrupted
            e.printStackTrace();
        }

        // Print which thread is performing the increment
        System.out.println(name + " is incrementing count from " + currCount + " to " + (currCount + 1));

        count = currCount + 1; // Write the new count
    }
}
