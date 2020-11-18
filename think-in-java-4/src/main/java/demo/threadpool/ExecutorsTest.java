package demo.threadpool;


import demo.taskdefine.PrintTime;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * ExecutorsTest
 *
 * @author liko
 * @date 2019/11/6
 */
public class ExecutorsTest {
    public static void main(String[] args) {
        ExecutorService exec1 = Executors.newCachedThreadPool();
        exec1.execute(new PrintTime());
        exec1.shutdown();

        ExecutorService exec2 = Executors.newFixedThreadPool(10);
        ExecutorService exec3 = Executors.newSingleThreadExecutor();
        // newWorkStealingPool适合使用在很耗时的操作，但是newWorkStealingPool不是ThreadPoolExecutor的扩展，
        // 它是新的线程池类ForkJoinPool的扩展，但是都是在统一的一个Executors类中实现，由于能够合理的使用CPU进行对任务操作（并行操作），所以适合使用在很耗时的任务中
        ExecutorService exec4 = Executors.newWorkStealingPool();


        ScheduledExecutorService exec5 = Executors.newScheduledThreadPool(10);

//        exec4.schedule();
//        exec4.scheduleAtFixedRate()


    }
}
