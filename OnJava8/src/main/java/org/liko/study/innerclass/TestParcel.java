package org.liko.study.innerclass;

/**
 * Parcel4
 *
 * @author liko
 * @date 2020/5/24
 */
class Parcel4 {
    private class PContents implements Contents {
        private int i = 11;

        @Override
        public int value() {
            return i;
        }
    }

    protected final class PDestination implements Destination {

        public static final int i = 1;

        private String label;

        public PDestination(String whereTo) {
            this.label = whereTo;
        }

        @Override
        public String readLabel() {
            return label;
        }
    }

    public Destination destination(String s) {
        class InnerClass1 {

        }

        return new PDestination(s);
    }

    public Contents contents() {
        return new PContents();
    }
}

public class TestParcel {
    public static void main(String[] args) {
        Parcel4 p = new Parcel4();
        Contents contents = p.contents();
        Destination tasmania = p.destination("Tasmania");

        System.out.println(Parcel4.PDestination.i);

    }
}