package org.liko.study.springcloud.feign.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * MDS
 *
 * @author liko
 * @date 2019/10/12
 */
@Getter
@Setter
@ToString
public class MDS {

    private String code;

    private Integer data;

    private String[] snap;

    private Integer time;

}
