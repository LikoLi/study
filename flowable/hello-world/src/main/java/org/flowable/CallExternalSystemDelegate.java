package org.flowable;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * CallExternalSystemDelegate
 *
 * @author liko
 * @date 2021/3/11
 */
public class CallExternalSystemDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("Calling the external system for employee " + delegateExecution.getVariable("employee"));
    }
}
