package org.likui.study.qlexpress.helloworld;

public class BeanExample {
    public static String upper(String abc) {
        return abc.toUpperCase();
    }

    public boolean anyContains(String str, String searchStr) {
        char[] s = str.toCharArray();
        for (char c : s) {
            if (searchStr.contains(c + "")) {
                return true;
            }
        }
        return false;
    }

    public void println(String str) {
        System.out.println(str);
    }


}
