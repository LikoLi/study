package org.liko.study.innerclass;

/**
 * DotThis
 *
 * @author liko
 * @date 2020/5/24
 */
public class DotThis {

    public void f() {
        System.out.println("DotThis.f()");
    }

    public class Inner {
        public DotThis outer() {
            return DotThis.this;
        }
    }

    public Inner inner() {
        return new Inner();
    }

    public static void main(String[] args) {
        DotThis dotThis = new DotThis();

        Inner inner = dotThis.inner();

        inner.outer().f();
    }

}
