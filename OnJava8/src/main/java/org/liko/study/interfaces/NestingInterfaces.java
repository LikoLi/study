package org.liko.study.interfaces;

/**
 * NestingInterfaces
 *
 * @author liko
 * @date 2020/5/23
 */
public class NestingInterfaces {
    public class InnerClassA {

    }

    public class InnerClassB {

    }
}

interface IA {
    interface IB {
        void f();
    }

    interface IC {
        void f();
    }
}

class EA implements IA {
    class EB implements IB {

        @Override
        public void f() {

        }
    }

    class EC implements IC {

        @Override
        public void f() {

        }
    }
}

class ED implements IA.IC {

    @Override
    public void f() {

    }
}
