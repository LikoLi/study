package org.liko.study.innerclass;

/**
 * Parcel8
 *
 * @author liko
 * @date 2020/5/24
 */
public class Parcel8 {

    public Wrapping wrapping() {
        return new Wrapping(1) {
            @Override
            public int value() {
                return super.value() * 47;
            }
        };
    }

    public static void main(String[] args) {
        Parcel8 p = new Parcel8();
        Wrapping wrapping = p.wrapping();
        int value = wrapping.value();
        System.out.println(value);

    }
}

class Wrapping {
    private int i;

    public Wrapping(int i) {
        this.i = i;
    }

    public int value() {
        return i;
    }
}

