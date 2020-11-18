package org.liko.study.polymorphism;

/**
 * PrivateOverride
 *
 * @author liko
 * @date 2020/5/19
 */
public class PrivateOverride {
    private void f() {
        System.out.println("private f()");
    }

    public static void main(String[] args) {
        PrivateOverride po = new Derived();
        po.f();
    }
}
