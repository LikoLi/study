package demo.catchexception;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * SettingDefaulthandler
 *
 * @author liko
 * @date 2019/11/6
 */
public class SettingDefaulthandler {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        ThreadPoolExecutor exec = new ThreadPoolExecutor(1, 10, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1024));
        exec.execute(new ExceptionThread2());
    }
}
