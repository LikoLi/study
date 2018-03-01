//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.util.concurrent.CountDownLatch;
//
//public class Log4j2PerformanceTest {
//    static Logger logger = LogManager.getLogger(Log4j2PerformanceTest.class);
//
//    public static void main(String[] args) throws InterruptedException {
//        fixedPrinting(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
//    }
//
//    private static void fixedPrinting(int totalThread, int maxNum) throws InterruptedException {
//        final CountDownLatch cdl = new CountDownLatch(totalThread);
//        long startTime = System.currentTimeMillis();
//        for (int i = 0; i < totalThread; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for (int j = 0; j < maxNum / totalThread; j++) {
//                        logger.info("Hello World fixedPrinting");
//                    }
//                    cdl.countDown();
//                }
//            }).start();
//        }
//        cdl.await();
//        long endTime = System.currentTimeMillis();
//        System.out.println("耗时:" + (endTime - startTime) + "ms");
//        System.out.println("Per sec:" + maxNum * 1000 / (endTime - startTime));
//    }
//}
