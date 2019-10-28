package org.liko.study.springcloud.feign.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Snap
 *
 * @author liko
 * @date 2019/10/12
 */
@Getter
@Setter
@ToString
public class Snap {

    @JsonProperty("0")
    private String name;

    @JsonProperty("1")
    private Double last;

    @JsonProperty("2")
    private Double chgRate;

    @JsonProperty("3")
    private Double change;

    @JsonProperty("4")
    private Double amount;

    @JsonProperty("5")
    private Double volume;

    @JsonProperty("6")
    private Double open;

    @JsonProperty("7")
    private Double prevClose;

    @JsonProperty("8")
    private Double[] ask;

    @JsonProperty("9")
    private Double[] bid;

    @JsonProperty("10")
    private Double high;

    @JsonProperty("11")
    private Double low;

    @JsonProperty("12")
    private String tradephase;

    @JsonProperty("13")
    private String hltTag;

    @JsonProperty("14")
    private String gdrRatio;

    @JsonProperty("15")
    private Double gdrPrevpx;

    @JsonProperty("16")
    private String gdrCurrency;

}
