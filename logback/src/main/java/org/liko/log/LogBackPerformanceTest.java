//package org.liko.log;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.concurrent.CountDownLatch;
//
//public class LogBackPerformanceTest {
//
//    static Logger logger = LoggerFactory.getLogger(LogBackPerformanceTest.class);
//
//    public static void main(String[] args) throws InterruptedException {
////        fixedPrinting(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
//        fixedPrinting(1, 1000000);
//    }
//
//    private static void fixedPrinting(int totalThread, int maxNum) throws InterruptedException {
//        final CountDownLatch cdl = new CountDownLatch(totalThread);
//        long startTime = System.currentTimeMillis();
//        for (int i = 0; i < totalThread; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(maxNum / totalThread);
//                    for (int j = 0; j < maxNum / totalThread; j++) {
//                        logger.info("Hello World fixedPrinting");
//                    }
//                    cdl.countDown();
//                }
//            }).start();
//        }
//        cdl.await();
//        long endTime = System.currentTimeMillis();
//        System.out.println("Total Time: " + (endTime - startTime) + "ms");
//        System.out.println("Per sec: " + maxNum * 1000 / (endTime - startTime));
//    }
//}
