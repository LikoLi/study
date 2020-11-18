package org.liko.study.interfaces;

/**
 * Jim
 *
 * @author liko
 * @date 2020/5/21
 */
interface Jim1 {
    default void jim() {
        System.out.println("Jim1::jim");
    }
}

interface Jim2 {
    default void jim() {
        System.out.println("Jim2::jim");
    }
}

public class Jim implements Jim1, Jim2 {
    @Override
    public void jim() {
        Jim2.super.jim();
        Jim1.super.jim();
    }

    public static void main(String[] args) {
        new Jim().jim();
    }
}
