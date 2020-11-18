package demo.collection;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TestCyclicBarrier {

    public static void main(String[] args) {
        int counts = 10;//线程大小
        CyclicBarrier cyclicBarrier = new CyclicBarrier(counts, new Runnable() {
            //栅栏动作，在计数器为0的时候执行
            public void run() {
                System.out.println("我们都准备好了.");
            }
        });
        ExecutorService executorService = Executors.newFixedThreadPool(counts);
        for (int i = 0; i < counts; i++)
            executorService.execute(new TestCyclicBarrier().new Task(cyclicBarrier));
        executorService.shutdown();

        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static int Count = 1;

    class Task implements Runnable {
        private CyclicBarrier cyclicBarrier;
        private int id;

        public Task(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
            this.id = Count++;
        }

        Random random = new Random();

        public void run() {
            try {
                Thread.sleep(random.nextInt(2000));
                System.out.println(id + " : 我到了");

                // 等待所有任务准备就绪
                cyclicBarrier.await();

                // 测试内容
                System.out.println("Id " + id + " : 点菜吧!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
