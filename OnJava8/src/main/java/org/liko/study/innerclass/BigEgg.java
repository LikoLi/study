package org.liko.study.innerclass;

/**
 * BigEgg
 *
 * @author liko
 * @date 2020/6/9
 */
public class BigEgg extends Egg {
    public class Yolk extends Egg.Yolk{
        public Yolk() {
            System.out.println("BigEgg.Yolk()");
        }
    }

    public static void main(String[] args) {
        new BigEgg();
    }
}

class Egg {
    private Yolk y;
    protected class Yolk {
        public Yolk() {
            System.out.println("Egg.Yolk()");
        }
    }

    Egg() {
        System.out.println("New Egg()");
        y = new Yolk();
    }
}
