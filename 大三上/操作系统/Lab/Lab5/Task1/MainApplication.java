package Task1;

/**
 * The MainApplication class creates and starts multiple Incrementer threads.
 */
public class MainApplication {

  /**
   * This program creates multiple Incrementer threads that modify the same
   * shared counter. Due to concurrent modifications, race conditions may occur.
   *
   * @param args command-line arguments (not used)
   * @throws InterruptedException if thread execution is interrupted
   */
  public static void main(String[] args) throws InterruptedException {
    int numAdds = 200;

    // 创建两个 Incrementer 实例作为 Runnable
    Incrementer incrementer1 = new Incrementer(numAdds, 1);
    Incrementer incrementer2 = new Incrementer(numAdds, 2);

    // 创建 Thread 实例，并传入 Incrementer 作为 Runnable
    Thread thread1 = new Thread(incrementer1, "Thread-1");
    Thread thread2 = new Thread(incrementer2, "Thread-2");

    // 启动线程
    thread1.start();
    thread2.start();

    // 等待所有线程完成
    thread1.join();
    thread2.join();

    // 打印最终的计数值
    System.out.println("Final count is: " + Incrementer.getSharedCount());
  }
}
