package org.likui.study.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    public static void main(String[] args) {
        List<A> list = new ArrayList<>();

        A a1 = new A(1D, 2L);
        A a2 = new A(1D, 2L);
        A a3 = new A(1D, 2L);
        A a4 = new A(0D, 0L);

        list.add(a1);
        list.add(a2);
        list.add(a3);
        list.add(a4);

        List<A> collect = list.stream().filter(a -> a.getA() != 0 && a.getB() != 0).collect(Collectors.toList());
        System.out.println(Arrays.toString(list.toArray()));
    }
}

class A {
    private Double a;
    private Long b;

    public A(Double a, Long b) {
        this.a = a;
        this.b = b;
    }

    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Long getB() {
        return b;
    }

    public void setB(Long b) {
        this.b = b;
    }
}
