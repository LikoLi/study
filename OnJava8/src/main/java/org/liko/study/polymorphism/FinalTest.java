package org.liko.study.polymorphism;

import java.util.ArrayList;
import java.util.List;

/**
 * FinalTest
 *
 * @author liko
 * @date 2020/5/19
 */
public class FinalTest {
    private static List list = new ArrayList<>();

    public static void main(String[] args) {
        list = new ArrayList();

        switch (3) {
            default:
            case 1:
            case 2:
            case 3:
                System.out.println(1);
        }

        arr(new String[]{"A", "B"});
        arr("A", "B");
    }
    
    public static void arr(String... arr) {
        for (String s : arr) {
            System.out.println(s);
        }
    }
}
