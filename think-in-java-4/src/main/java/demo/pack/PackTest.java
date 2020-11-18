package demo.pack;

import java.util.concurrent.locks.LockSupport;

/**
 * PackTest
 *
 * @author liko
 * @date 2019/11/7
 */
public class PackTest {
    public static void main(String[] args) {
        LockSupport.park(new PackMonitor());
    }
}

class PackMonitor {

}
