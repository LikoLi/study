package org.liko.study.algorithm;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * SelectionProblem
 *
 * @author liko
 * @date 2019/11/12
 */
public class SelectionProblem {
    static Random random = new Random();

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        int size = 100000;
        int[] values = random(size);
        int maxVale = findMaxVale(values, size / 2);
        System.out.println(maxVale);
        long end = System.currentTimeMillis();

    }

    private static int[] random(int size) {
        int[] values = new int[size];
        for (int i = 0; i < size; i++) {
            values[i] = random.nextInt(size);
        }
        return values;
    }

    public static int findMaxVale(int[] values, int index) {
        long begin = System.currentTimeMillis();
        sort(values);
        System.out.println("耗时: " + (System.currentTimeMillis() - begin));
        return values[index - 1];
    }

    /**
     * 冒泡排序
     *
     * @param values
     */
    public static void sort(int[] values) {
        for (int i = values.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (values[i] > values[j]) {
                    int tmp = values[i];
                    values[i] = values[j];
                    values[j] = tmp;
                }
            }
        }
    }

    /**
     * 快速排序
     */
    public static int fastSort(int[] values, int left, int right) {
        int pivot = findMidle(values, left, right);
        System.out.println("end : " + pivot);
        System.out.println(Arrays.toString(values));

        while (left < pivot - 1) {
            pivot = fastSort(values, left, pivot - 1);
        }

        while (pivot + 1 < right) {
            pivot = fastSort(values, pivot + 1, right);
        }

        return pivot;

    }

    private static int findMidle(int[] values, int left, int right) {
        int pivot = left;
        System.out.println("first pivot: " + pivot);
        boolean breakFlag = false;
        left++;
        while (left < right && !breakFlag) {
            while (left < right && values[left] <= values[pivot]) {
                left++;
            }

            while (left < right && values[right] > values[pivot]) {
                right--;
            }

            swap(values, left, right);

            if (left == right) {
                if (values[left] <= values[pivot]) {
                    swap(values, pivot, left);
                    pivot = left;
                } else {
                    swap(values, pivot, left - 1);
                    pivot = left - 1;
                }
            }
        }


        return pivot;
    }

    private static void swap(int[] values, int left, int right) {
        int tmp = values[left];
        values[left] = values[right];
        values[right] = tmp;
    }
}
