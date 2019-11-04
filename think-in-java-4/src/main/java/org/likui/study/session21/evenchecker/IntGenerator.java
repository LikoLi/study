package org.likui.study.session21.evenchecker;

/**
 * IntGenerator
 *
 * @author liko
 * @date 2019/11/1
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false;
    public abstract int next();
    // Allow this to be canceled
    public void cancel() {
        this.canceled = true;
    }
    public boolean isCanceled() {
        return canceled;
    }
}
