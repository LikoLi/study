package org.likui.study.generator;

import com.iquantex.generator.MybatisBeanDaoGenerator;

public class Generator {
    public static void main(String[] args) {
        try {
            MybatisBeanDaoGenerator.generator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
