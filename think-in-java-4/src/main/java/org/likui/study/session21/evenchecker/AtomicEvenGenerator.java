package org.likui.study.session21.evenchecker;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicEvenGenerator
 *
 * @author liko
 * @date 2019/11/4
 */
public class AtomicEvenGenerator extends IntGenerator {

    private AtomicInteger currentEvenValue = new AtomicInteger(0);

    @Override
    public int next() {
        return currentEvenValue.addAndGet(2);
    }

    public static void main(String[] args) {
        EvenChecker.test(new AtomicEvenGenerator());
    }
}
