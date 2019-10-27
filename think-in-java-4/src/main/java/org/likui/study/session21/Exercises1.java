package org.likui.study.session21;

public class Exercises1 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Print()).start();
        }
    }
}

class Print implements Runnable {

    public Print() {
        System.out.println(Thread.currentThread().getName() + " Init!");
    }

    @Override
    public void run() {
        int i = 3;
        while (i >= 0) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            i -= 1;
        }

        System.out.println(Thread.currentThread().getName() + " Exit!");
    }
}
