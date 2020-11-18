package org.liko.study.polymorphism;

/**
 * Derived
 *
 * @author liko
 * @date 2020/5/19
 */
public class Derived extends PrivateOverride {

    public void f() {
        System.out.println("public f()");
    }

    public static void main(String[] args) {
        PrivateOverride p2 = new Derived();
//        p2.f();
    }

}
