package cn.likoli.study.mavenbuild.project2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project2Controller
 *
 * @author liko
 * @date 2020/11/19
 */
@RestController
public class Project2Controller {

    @RequestMapping("/project2")
    public String project2() {
        return "project2";
    }
}
