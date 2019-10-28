package org.liko.study.springcloud.feign.controller;

import org.liko.study.springcloud.feign.service.MdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * MDSController
 *
 * @author liko
 * @date 2019/10/12
 */
@Controller
@RequestMapping("/mds")
public class MDSController {

    @Autowired
    private MdsService mdsService;

    @GetMapping("/{symbol}")
    public String getMDS(@PathVariable("symbol") String symbol) {
        String select = "last,chg_rate,change,amount,volume,open,prev_close,ask,bid,high,low,tradephase";
        return mdsService.getMds(symbol, System.currentTimeMillis(), select).toString();
    }
}
