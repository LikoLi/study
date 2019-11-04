package org.likui.study.session21;

import static net.mindview.util.Print.print;

/**
 * MultiLock
 *
 * @author liko
 * @date 2019/11/4
 */
public class MultiLock {
    public synchronized void f1(int count) {
        if (count-- > 0) {
            print("f1() calling f2() with count " + count);
            f2(count);
        }
    }

    public synchronized void f2(int count) {
        if (count-- > 0) {
            print("f2() calling f1() whit count " + count);
            f1(count);
        }
    }

    public static void main(String[] args) {
        final MultiLock multiLock = new MultiLock();
        new Thread() {
            @Override
            public void run() {
                multiLock.f1(10);
            }
        }.start();
    }
}
