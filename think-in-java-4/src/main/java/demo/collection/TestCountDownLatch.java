package demo.collection;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author maxiao
 * @create 2019/11/7
 * @since 1.0.0
 */
public class TestCountDownLatch {


    public static void main(String[] args) throws InterruptedException {
        int count = 3;//线程大小
        final CountDownLatch latch = new CountDownLatch(count);
        ExecutorService executorService = Executors.newFixedThreadPool(count);

        System.out.println("初始计数器：" + latch.getCount());

        executorService.execute(new Runnable() {
            public void run() {
                fatherToRes();
                latch.countDown();
                System.out.println("爸爸到，计数器为：" + latch.getCount());
            }
        });

        executorService.execute(new Runnable() {
            public void run() {
                motherToRes();
                latch.countDown();
                System.out.println("妈妈到，计数器为：" + latch.getCount());
            }
        });

        executorService.execute(new Runnable() {
            public void run() {
                meToRes();
                latch.countDown();
                System.out.println("我到，计数器为：" + latch.getCount());
            }
        });

        System.out.println("三人准备出发。");
        long l = System.currentTimeMillis();

        latch.await();
        togetherToEat(l);

        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 模拟爸爸去饭店
     */
    public static void fatherToRes() {
        System.out.println("爸爸步行去饭店需要3小时。");
        long l = System.currentTimeMillis();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("爸爸步行到。时间为：" + (System.currentTimeMillis() - l) / 1000 + "小时");
    }

    /**
     * 模拟我去饭店
     */
    public static void motherToRes() {
        System.out.println("妈妈挤公交去饭店需要2小时。");
        long l = System.currentTimeMillis();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("妈妈步行到。时间为：" + (System.currentTimeMillis() - l) / 1000 + "小时");
    }

    /**
     * 模拟妈妈去饭店
     */
    public static void meToRes() {
        System.out.println("我乘地铁去饭店需要1小时。");
        long l = System.currentTimeMillis();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("我步行到。时间为：" + (System.currentTimeMillis() - l) / 1000 + "小时");
    }

    /**
     * 模拟一家人到齐了
     *
     * @param l
     */
    public static void togetherToEat(long l) {
        System.out.println("一家人到齐了，开始吃饭，时间为：" + (System.currentTimeMillis() - l) / 1000 + "小时");
    }


}
