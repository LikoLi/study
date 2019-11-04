package org.likui.study.session21;

/**
 * SerialNumberGenerator
 *
 * @author liko
 * @date 2019/11/4
 */
public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;

    public static int nextSerialNumber() {
        return serialNumber++; // Not thread-safe
    }
}
