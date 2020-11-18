package org.liko.study.innerclass;

/**
 * DotNew
 *
 * @author liko
 * @date 2020/5/24
 */
public class DotNew {
    public class Inner {}

    public static void main(String[] args) {
        DotNew dn = new DotNew();
        Inner inner = dn.new Inner();

//        DotNew.Inner inner1 = new DotNew.Inner();
    }
}
