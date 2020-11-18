package org.liko.study.innerclass;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * RemoveListItem
 *
 * @author liko
 * @date 2020/6/7
 */
public class RemoveListItem {
    public static void main(String[] args) {
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);

        for (Integer integer : Lists.newArrayList(list)) {
            System.out.println(integer);
            if (integer % 2 == 0) {
                list.remove(integer);
            }
        }

        System.out.println(list);
    }
}
