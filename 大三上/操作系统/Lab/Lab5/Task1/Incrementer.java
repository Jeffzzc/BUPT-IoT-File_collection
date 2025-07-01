package Task1;

/**
 * The Incrementer class implements Runnable and increments a shared counter.
 */
public class Incrementer implements Runnable {

  private int numIncrements;
  private String threadName;
  private static Counter sharedCounter = new Counter(); // 共享的计数器

  /**
   * Constructs an Incrementer with the specified number of increments and thread
   * index.
   *
   * @param numIncrements the number of times to increment the counter
   * @param index         the identifier for the thread
   */
  public Incrementer(int numIncrements, int index) {
    this.numIncrements = numIncrements;
    this.threadName = "Thread " + index;
  }

  /**
   * The run method performs the incrementing operation.
   */
  @Override
  public void run() {
    for (int i = 0; i < numIncrements; i++) {
      sharedCounter.increment();
      // 可选：打印当前线程和计数值
      System.out.println(threadName + " incremented to " +
          sharedCounter.getValue());
    }
  }

  /**
   * 获取共享计数器的当前值。
   *
   * @return 当前计数值
   */
  public static int getSharedCount() {
    return sharedCounter.getValue();
  }

  /**
   * 内部静态类，用于表示共享的计数器。
   */
  private static class Counter {
    private int count = 0;

    /**
     * 增加计数器的值。
     */
    public void increment() {
      count++;
    }

    /**
     * 获取计数器的当前值。
     *
     * @return 当前计数值
     */
    public int getValue() {
      return count;
    }
  }
}
