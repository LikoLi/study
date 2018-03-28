package org.liko.bean;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class BeanUtilsTest {

    @Test
    public void testCopyProperties() throws InvocationTargetException, IllegalAccessException {
        A a = new A("liko", 25);
        B b = new B();
        BeanUtils.copyProperties(b, a);
        System.out.println(b);

        A a1 = new A();
        BeanUtils.copyProperties(a1, b);
        System.out.println(a1);
    }

    @Test
    public void testThread() throws InterruptedException {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("f");
        list.add("x");
        list.add("x");
        list.add("x");
        list.add("x");
        list.add("x");
        list.add("x");
        list.add("x");
        System.out.println(Thread.currentThread().getName());
        try {
            list.parallelStream().forEach(str -> {
//                System.out.println(Thread.currentThread().getName());
//                System.out.println(str);
//                System.out.println("-----------------------------");
//                throw new RuntimeException("xxx");
                System.out.println();
            });

        } catch (RuntimeException e) {
            System.out.println("parallel ");
            e.printStackTrace();
        }

//        try {
//            new Thread(() -> {
//                System.out.println("123213");
//                throw new RuntimeException("xxx");
//            }).start();
//        } catch (RuntimeException e) {
//            System.out.println("Runtime");
////            e.printStackTrace();
//        }


        Thread.sleep(20000L);
    }
}
