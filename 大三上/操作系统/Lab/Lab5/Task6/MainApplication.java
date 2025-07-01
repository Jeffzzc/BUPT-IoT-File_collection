package Task6;

/**
 * MainApplication 类用于创建和管理多个 Incrementer 线程。
 */
public class MainApplication {

    /**
     * 程序入口点，根据命令行参数创建指定数量的线程，每个线程执行指定次数的增量操作。
     *
     * @param args 命令行参数，第一个参数为要创建的线程数量
     * @throws InterruptedException 如果线程执行被中断
     */
    public static void main(String[] args) throws InterruptedException {
        int numThreads = 2; // 默认线程数量
        int numIncrements = 10; // 每个线程的增量次数

        // 解析命令行参数以获取线程数量
        if (args.length > 0) {
            try {
                numThreads = Integer.parseInt(args[0]);
                if (numThreads <= 0) {
                    System.out.println("线程数量必须是正整数。使用默认值 2。");
                    numThreads = 2;
                }
            } catch (NumberFormatException e) {
                System.out.println("无效的线程数量参数。使用默认值 2。");
                numThreads = 2;
            }
        } else {
            System.out.println("未提供线程数量参数。使用默认值 2。");
        }

        // 创建共享的 Counter 实例
        Counter sharedCounter = new Counter();

        // 创建一个线程数组来存储所有线程
        Thread[] threads = new Thread[numThreads];

        // 使用循环创建并启动所有线程
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Incrementer(sharedCounter, numIncrements, i + 1);
            threads[i].start();
        }

        // 等待所有线程完成
        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
        }

        // 计算预期的计数值
        int expectedCount = numThreads * numIncrements;

        // 获取实际的计数值
        int actualCount = sharedCounter.getCount();

        // 打印最终的计数值
        System.out.println("Final count is: " + actualCount);
        System.out.println("Expected count is: " + expectedCount);

        // 检查是否观察到了竞态条件
        if (actualCount < expectedCount) {
            System.out.println("Race conditions were observed!");
        } else {
            System.out.println("No race conditions were observed.");
        }
    }
}
