package org.liko.study.operators;

import java.util.Arrays;

/**
 * HouseKeeping
 *
 * @author liko
 * @date 2020/5/7
 */
public class HouseKeeping {
    static char c;
    static byte b;
    public static void main(String[] args) {
        System.out.println("char[" + c + "]");
        System.out.println(b);

        System.out.println(Color.BLACK.ordinal());
        System.out.println(Arrays.toString(Color.values()));
    }

    public void otherMethod(Object... obj) {
        for (Object o : obj) {

        }

        System.out.println(obj.length);
    }
}

class HK {
    public HK() {
        this("123");
        System.out.println("123");
    }

    public HK(String string) {

    }

    public void hello() {
    }
}

enum Color {
    RED, BLACK, WHITE
}
