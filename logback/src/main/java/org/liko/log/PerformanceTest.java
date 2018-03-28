package org.liko.log;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.apache.logging.log4j.LogManager.*;

public class PerformanceTest {
    private static long t = 10000000;
    public static void main(String[] args) {
//        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");

        // async_log2
        Logger logger = getLogger(PerformanceTest.class);
        long start = System.currentTimeMillis();
        for (int i = 0; i < t; i++) {
            logger.info("Hello World.");
        }
        System.out.println(System.currentTimeMillis() - start);

    }
}
