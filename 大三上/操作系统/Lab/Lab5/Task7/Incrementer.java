package Task7;

/**
 * Incrementer class used to perform increment operations on a shared Counter.
 */
public class Incrementer extends Thread {

    private int numIncrements;
    private String threadName;
    private Counter counter;

    /**
     * Constructor to initialize the Incrementer thread.
     *
     * @param counter       the shared Counter object
     * @param numIncrements the number of times to increment the counter
     * @param index         the identifier index for naming the thread
     */
    public Incrementer(Counter counter, int numIncrements, int index) {
        this.counter = counter;
        this.numIncrements = numIncrements;
        this.threadName = "Thread-" + index;
    }

    /**
     * The run method executed by the thread.
     * It performs the increment operation and sleeps for a random duration.
     */
    @Override
    public void run() {
        for (int i = 0; i < numIncrements; i++) {
            counter.increment(threadName);

            // Sleep for up to 1 second (1000 milliseconds) to simulate work
            try {
                long sleepTime = (long) (Math.random() * 1000);
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                // Print stack trace if the thread is interrupted
                e.printStackTrace();
            }
        }
    }
}
