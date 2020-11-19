package cn.likoli.study.mavenbuild.project1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project1Controller
 *
 * @author liko
 * @date 2020/11/19
 */
@RestController
public class Project1Controller {

    @RequestMapping("/project1")
    public String project1() {
        return "project1";
    }
}
