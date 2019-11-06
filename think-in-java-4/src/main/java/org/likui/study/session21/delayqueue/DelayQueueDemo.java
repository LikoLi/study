//package org.likui.study.session21.delayqueue;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.Delayed;
//import java.util.concurrent.TimeUnit;
//
///**
// * DelayQueueDemo
// *
// * @author liko
// * @date 2019/11/5
// */
//class DelayedTask implements Runnable, Delayed {
//
//    private static int counter = 0;
//
//    private final int id = counter++;
//
//    private final int delta;
//
//    private final long trigger;
//
//    protected static List<DelayedTask> sequence = new ArrayList<>();
//
//    public DelayedTask(int delta) {
//        this.delta = delta;
//    }
//
//    /**
//     * When an object implementing interface <code>Runnable</code> is used
//     * to create a thread, starting the thread causes the object's
//     * <code>run</code> method to be called in that separately executing
//     * thread.
//     * <p>
//     * The general contract of the method <code>run</code> is that it may
//     * take any action whatsoever.
//     *
//     * @see Thread#run()
//     */
//    @Override
//    public void run() {
//
//    }
//
//    /**
//     * Returns the remaining delay associated with this object, in the
//     * given time unit.
//     *
//     * @param unit the time unit
//     * @return the remaining delay; zero or negative values indicate
//     * that the delay has already elapsed
//     */
//    @Override
//    public long getDelay(TimeUnit unit) {
//        return 0;
//    }
//
//    /**
//     * Compares this object with the specified object for order.  Returns a
//     * negative integer, zero, or a positive integer as this object is less
//     * than, equal to, or greater than the specified object.
//     *
//     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
//     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
//     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
//     * <tt>y.compareTo(x)</tt> throws an exception.)
//     *
//     * <p>The implementor must also ensure that the relation is transitive:
//     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
//     * <tt>x.compareTo(z)&gt;0</tt>.
//     *
//     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
//     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
//     * all <tt>z</tt>.
//     *
//     * <p>It is strongly recommended, but <i>not</i> strictly required that
//     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
//     * class that implements the <tt>Comparable</tt> interface and violates
//     * this condition should clearly indicate this fact.  The recommended
//     * language is "Note: this class has a natural ordering that is
//     * inconsistent with equals."
//     *
//     * <p>In the foregoing description, the notation
//     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
//     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
//     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
//     * <i>expression</i> is negative, zero or positive.
//     *
//     * @param o the object to be compared.
//     * @return a negative integer, zero, or a positive integer as this object
//     * is less than, equal to, or greater than the specified object.
//     * @throws NullPointerException if the specified object is null
//     * @throws ClassCastException   if the specified object's type prevents it
//     *                              from being compared to this object.
//     */
//    @Override
//    public int compareTo(Delayed o) {
//        return 0;
//    }
//}
//public class DelayQueueDemo {
//}
