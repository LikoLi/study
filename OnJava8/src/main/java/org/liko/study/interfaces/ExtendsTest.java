package org.liko.study.interfaces;

/**
 * ExtendsTest
 *
 * @author liko
 * @date 2020/5/21
 */
public class ExtendsTest {
    public static void main(String[] args) {
        A a = new B();
        A c = new C();

        a.say();
        c.say();
    }
}

abstract class A {
    abstract void say();

    void hello() {
        System.out.println("Hello~");
    }
}

class B extends A {

    @Override
    void say() {
        System.out.println("B");
    }
}

class C extends A {

    @Override
    void say() {
        System.out.println("C");
    }
}

interface D {
    public static final int i = 1;
    default void say() {
        System.out.println("B");
    }
}
