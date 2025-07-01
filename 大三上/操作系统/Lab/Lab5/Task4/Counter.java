package Task4;

/**
 * Counter 类用于在多个线程之间共享和管理计数值。
 */
public class Counter {
    // 1. 私有变量 count，初始值为0
    private int count = 0;

    /**
     * 获取当前的计数值。
     *
     * @return 当前的计数值
     */
    public int getCount() {
        return count;
    }

    /**
     * 增加计数值并打印哪个线程正在执行增量操作。
     *
     * @param name 进行增量操作的线程名称
     */
    public void increment(String name) {
        int currCount = count; // 读取当前计数值

        // 引入延迟以增加竞态条件发生的可能性
        try {
            // 使用随机的延迟时间，最多1000毫秒（1秒）
            long delay = (long) (Math.random() * 1000);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            // 如果线程被中断，打印堆栈跟踪
            e.printStackTrace();
        }

        // 打印哪个线程正在执行增量操作
        System.out.println(name + " is incrementing count from " + currCount + " to " + (currCount + 1));

        count = currCount + 1; // 写入新的计数值
    }
}
