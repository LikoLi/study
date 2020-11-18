package org.liko.study.operators;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Equals
 *
 * @author liko
 * @date 2020/4/15
 */
public class Equals {
    public static void main(String[] args) {
        double d = 123_123D;

        for (;;) {
            System.out.println(new Date());
        }
    }

}

@Data
@EqualsAndHashCode
class User {
    int age;
}
