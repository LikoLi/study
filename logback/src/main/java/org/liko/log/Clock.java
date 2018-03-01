package org.liko.log;

import java.util.Date;

public class Clock {

    private final long startTime;

    public Clock() {
        startTime = new Date().getTime();
    }

    public long stop() {
        long time = new Date().getTime() - startTime;
        System.out.println(time);
        return time;
    }
}
