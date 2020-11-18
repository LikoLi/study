package org.liko.study.operators;

/**
 * MathOps
 *
 * @author liko
 * @date 2020/4/15
 */
public class MathOps {

    public static void main(String[] args) {
        int i, j;
        i = 1;
        j = 2;

        i += j;
        System.out.println(i);

        i -= j;
        System.out.println(i);

        i *= j;
        System.out.println(i);

        i /= j;
        System.out.println(i);

        i %= j;
        // 1
        System.out.println(i);

        i &= j;
        /*
             1
          & 10
           ---
            00
         */
        System.out.println(i);

        i |= j;
        /*
              0
           | 10
          -----
             10
         */
        System.out.println(i);

        /*
                1
           & 1111
            -----
             0001
         */
        i = 1;
        j = 15;
        System.out.println(i &= j);

    }
}
