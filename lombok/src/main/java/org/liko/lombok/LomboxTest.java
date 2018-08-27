package org.liko.lombok;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Liko
 * @Description:
 * @Date: Created at 19:20 2018/8/2
 */
@Slf4j
public class LomboxTest {

    //https://blog.csdn.net/luoww1/article/details/81013020
    public static void main(String[] args) {
        LomboxBean lomboxBean = new LomboxBean("123", 1);
        log.info(lomboxBean.getName());
        log.error(lomboxBean.getType().toString());
        System.out.println();
    }
}
