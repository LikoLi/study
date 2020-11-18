package org.liko.study.operators;

/**
 * ShiftOperator
 *
 * @author liko
 * @date 2020/4/21
 */
public class ShiftOperator {
    public static void main(String[] args) {
        Long l = 1L;
        l <<= 1;
        System.out.println(l);
        System.out.println(Long.toBinaryString(l));

        l = -1L;
        l <<= 1;
        System.out.println(l);
        System.out.println(Long.toBinaryString(l));

        l <<= 1;
        System.out.println(l);
        System.out.println(Long.toBinaryString(l));

        l <<= 1;
        System.out.println(l);
        System.out.println(Long.toBinaryString(l));

        Long k = -24L;
        System.out.println(k);
        System.out.println(Long.toBinaryString(k));

        int i = 1;
        i >>= 1;
        System.out.println(i);

        System.out.println("-----");
        i = -10;
        System.out.println(Integer.toBinaryString(i));

        i >>= 1;
        System.out.println(i);
        System.out.println(Integer.toBinaryString(i));

        i >>= 1;
        System.out.println(i);
        System.out.println(Integer.toBinaryString(i));

        i >>= 1;
        System.out.println(i);
        System.out.println(Integer.toBinaryString(i));

        i >>= 1;
        System.out.println(i);
        System.out.println(Integer.toBinaryString(i));

        i >>= 1;
        System.out.println(i);
        System.out.println(Integer.toBinaryString(i));

        i >>= 1;
        System.out.println(i);
        System.out.println(Integer.toBinaryString(i));

        int m = 5;
        System.out.println(String.format("%032d", Integer.parseInt(Integer.toBinaryString(m))));// 00000000000000000000000000000101
        int n = -5;
        System.out.println(Integer.toBinaryString(n));// 11111111111111111111111111111011

        int d = -10;
        System.out.println(d);
        System.out.println(Integer.toBinaryString(d));
        d >>>= 1;
        System.out.println(d);
        System.out.println(Integer.toBinaryString(d));
        d >>>= 1;
        System.out.println(d);
        System.out.println(Integer.toBinaryString(d));
        d >>>= 1;
        System.out.println(d);
        System.out.println(Integer.toBinaryString(d));
        d >>>= 1;
        System.out.println(d);
        System.out.println(Integer.toBinaryString(d));
        d >>>= 1;
        System.out.println(d);
        System.out.println(Integer.toBinaryString(d));


        byte b = -10; //00001010 11110101 11110110
        System.out.println(Integer.toBinaryString(b));
        System.out.println(b);
        b >>>= 1;
        System.out.println(Integer.toBinaryString(b));
        System.out.println(b);
    }

    private static void method() {
        System.out.println("----------");
        short s = -1;
        System.out.println(Integer.toBinaryString(s));
        s >>>= 10;
        System.out.println(Integer.toBinaryString(s));
        byte b = -1;
        System.out.println(Integer.toBinaryString(b));
        b >>>= 10;
        System.out.println(Integer.toBinaryString(b));
        b = -1;
        System.out.println(Integer.toBinaryString(b));
        System.out.println(Integer.toBinaryString(b>>>10));
    }
}
