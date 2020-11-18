package org.liko.springmvc.controller;

import com.alibaba.fastjson.JSONObject;
import org.liko.springmvc.controller.org.liko.springmvc.bean.Test;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("prototype")
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    @ResponseBody// 返回Json String
    public String save(String name, String password) {
        System.out.println("------------------------------------");
        System.out.println("接收到的用户信息：" + name);

        Test test = new Test("liko", 25);
        String testString = JSONObject.toJSONString(test);
        return testString;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody// 返回Json String
    public String update() {
        Test test = new Test("liko++", 25);
        String testString = JSONObject.toJSONString(test);
        return testString;
    }
}
