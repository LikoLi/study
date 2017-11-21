package org.liko.generator;


public class MyTest {
    public static void main(String[] args) {
        try {
            System.out.println(1);
            throw new RuntimeException("error");
        } catch (NullPointerException e) {
            System.out.println("null");
        }
        System.out.println("2");

    }
}
