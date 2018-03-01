package org.liko.log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PerformanceTest {
    private static long t = 10000000;
    public static void main(String[] args) {
//        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        // print internal state
//        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//        StatusPrinter.print(lc);

        // async_log2
        Logger logger = LoggerFactory.getLogger(PerformanceTest.class);
        long start = System.currentTimeMillis();
        for (int i = 0; i < t; i++) {
            logger.info("Hello World.");
        }
        System.out.println(System.currentTimeMillis() - start);

    }

    public static long avgTime(long[] times) {
        long allTime = 0;
        for (int i = 0; i < times.length; i++) {
            allTime += times[i];
            System.out.println(times[i]);
        }
        long avgTime = allTime / times.length;
        System.out.println("avgTime : " + avgTime);
        System.out.println("perTime : " + (double)avgTime / t);
        return avgTime;
    }
}
