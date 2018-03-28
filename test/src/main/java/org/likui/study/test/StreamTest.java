package org.likui.study.test;

import java.util.ArrayList;
import java.util.List;

public class StreamTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        list = null;

        list.stream().forEach(str -> {
            System.out.println(str);
        });
    }
}
