package cn.likoli.study.flowable.helloworld.event;

import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventType;

/**
 * MyEvent
 *
 * @author liko
 * @date 2021/3/12
 */
public class MyEvent implements FlowableEvent {
    @Override
    public FlowableEventType getType() {
        return null;
    }
}
