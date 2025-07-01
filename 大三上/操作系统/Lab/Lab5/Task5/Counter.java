package Task5;

/**
 * Counter 类用于在多个线程之间共享和管理计数值。
 */
public class Counter {
    // 私有变量 count，初始值为0
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

        // 引入更长的延迟以增加竞态条件发生的可能性
        try {
            // 增加睡眠时间到100毫秒
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // 如果线程被中断，打印堆栈跟踪
            e.printStackTrace();
        }

        // 打印哪个线程正在执行增量操作
        System.out.println(name + " is incrementing count from " + currCount + " to " + (currCount + 1));

        count = currCount + 1; // 写入新的计数值
    }
}
