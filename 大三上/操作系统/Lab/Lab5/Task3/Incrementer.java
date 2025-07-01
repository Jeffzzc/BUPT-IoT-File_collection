package Task3;

/**
 * Incrementer 类实现 Runnable 接口，用于增加共享 Counter 的计数值。
 */
public class Incrementer extends Thread {

    private int numIncrements;
    private String threadName;
    private Counter counter;

    /**
     * 构造方法，初始化增量次数、线程名称和共享计数器。
     *
     * @param counter       共享的 Counter 对象
     * @param numIncrements 每个线程要执行的增量次数
     * @param index         线程的标识索引
     */
    public Incrementer(Counter counter, int numIncrements, int index) {
        this.counter = counter;
        this.numIncrements = numIncrements;
        this.threadName = "Thread-" + index;
    }

    /**
     * 线程执行的方法，循环增加共享计数器。
     */
    @Override
    public void run() {
        for (int i = 0; i < numIncrements; i++) {
            counter.increment(threadName);
        }
    }
}
