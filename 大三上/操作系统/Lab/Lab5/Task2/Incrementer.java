package Task2;

/**
 * Incrementer 类用于增加共享计数器的值。
 */
public class Incrementer extends Thread {

  private int numIncrements;
  private String threadName;

  // 共享的计数器
  private static int counter = 0;

  /**
   * 构造方法，初始化增量次数和线程名称。
   *
   * @param numIncrements 每个线程要执行的增量次数
   * @param index         线程的编号
   */
  public Incrementer(int numIncrements, int index) {
    this.numIncrements = numIncrements;
    this.threadName = "Thread-" + index;
  }

  /**
   * 线程执行的方法，循环增加共享计数器。
   */
  @Override
  public void run() {
    for (int i = 0; i < numIncrements; i++) {
      incrementCounter();
      // 可选：打印当前线程和计数值
      // System.out.println(threadName + " incremented to " + getCounter());
    }
  }

  /**
   * 增加计数器的值。此方法不是同步的，可能导致竞态条件。
   */
  private void incrementCounter() {
    counter++;
  }

  /**
   * 获取当前计数器的值。
   *
   * @return 当前计数值
   */
  public static int getCounter() {
    return counter;
  }
}
