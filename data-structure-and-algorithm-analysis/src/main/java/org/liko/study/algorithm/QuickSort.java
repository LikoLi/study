package org.liko.study.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * QuickSort
 *
 * @author liko
 * @date 2019/11/15
 */
public class QuickSort {
    private static Random random = new Random();

    public static void main(String[] args) {
        int size = 1000000;
        int[] values = random(size);
        long begin = System.currentTimeMillis();
        quickSort(values, 0, values.length - 1);
        System.out.println("耗时: " + (System.currentTimeMillis() - begin));
    }

    private static int[] random(int size) {
        int[] values = new int[size];
        for (int i = 0; i < size; i++) {
            values[i] = random.nextInt(size);
        }
        return values;
    }

    public static void quickSort(int[] values, int left, int right) {
        if (left < right) {
            int index = findIndex(values, left, right);
            quickSort(values, left, index - 1);
            quickSort(values, index + 1, right);
        }
    }

    private static int findIndex(int[] values, int left, int right) {
        int pivot = values[left];

        while (left < right) {
            if (left < right && values[right] >= pivot) {
                right--;
            }
            values[left] = values[right];

            if (left < right && values[left] <= pivot) {
                left++;
            }
            values[right] = values[left];
        }
        values[left] = pivot;
        return left;
    }


}
