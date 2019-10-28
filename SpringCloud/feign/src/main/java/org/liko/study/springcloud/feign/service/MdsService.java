package org.liko.study.springcloud.feign.service;

import org.liko.study.springcloud.feign.model.MDS;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * MdsService
 *
 * @author liko
 * @date 2019/10/12
 */
@FeignClient(name = "MDS", url = "http://yunhq.sse.com.cn:32041/v1/sh1/snap")
public interface MdsService {

    /**
     * Get MDS
     * @param symbol
     * @param timestamp
     * @param select
     * @return
     */
    @GetMapping(value = "/{symbol}")
    MDS getMds(@PathVariable("symbol") String symbol, @RequestParam("_") Long timestamp, @RequestParam("select") String select);
}
