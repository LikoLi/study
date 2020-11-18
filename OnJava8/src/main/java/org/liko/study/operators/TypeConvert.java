package org.liko.study.operators;

/**
 * TypeConvert
 *
 * @author liko
 * @date 2020/4/21
 */
public class TypeConvert {
    public static void main(String[] args) {
        int i = 0b1111_1100_1100_1100_0011_1100_1100_0011;
        System.out.println(i);
        System.out.println(Integer.toBinaryString(i));
        short s = (short) i;
        System.out.println(String.format("%032d", Long.parseLong(Integer.toBinaryString(s))));
    }
}
