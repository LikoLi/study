package org.liko.study.innerclass;

/**
 * BigEgg2
 *
 * @author liko
 * @date 2020/6/9
 */
public class BigEgg2 {
}

class Egg2 {
    protected class Yolk {
        public Yolk() {
            System.out.println("Egg2.yolk()");
        }

        public void f() {
            System.out.println("Egg2.yolk().f()");
        }
    }
    private Yolk y = new Yolk();

    Egg2() {
        System.out.println("New Egg2()");
    }

    public void insertYold(Yolk yy) {
        y = yy;
    }
}
