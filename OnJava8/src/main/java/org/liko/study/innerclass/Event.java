package org.liko.study.innerclass;

import java.time.Duration;
import java.time.Instant;

/**
 * Event
 *
 * @author liko
 * @date 2020/5/25
 */
public abstract class Event {
    private Instant eventTime;
    protected final Duration delayTime;

    public Event(long millisecondDelay) {
        this.delayTime = Duration.ofMillis(millisecondDelay);
        start();
    }

    private void start() {
        eventTime = Instant.now().plus(delayTime);
    }

    public boolean ready() {
        return Instant.now().isAfter(eventTime);
    }

    public abstract void action();
}
