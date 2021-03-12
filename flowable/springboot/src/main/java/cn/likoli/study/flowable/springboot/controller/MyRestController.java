package cn.likoli.study.flowable.springboot.controller;

import cn.likoli.study.flowable.springboot.service.MyService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * MyRestController
 *
 * @author liko
 * @date 2021/3/12
 */
@RestController
public class MyRestController {

    @Autowired
    private MyService myService;

    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public void startProcessInstance(@RequestBody StartProcessRepresentation startProcessRepresentation) {
        myService.startProcess(startProcessRepresentation.getAssignee());
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
        List<Task> tasks = myService.getTasks(assignee);
        ArrayList<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }

    @Data
    @AllArgsConstructor
    static class TaskRepresentation {
        private String id;
        private String name;
    }

    @Data
    static class StartProcessRepresentation {
        private String assignee;
    }
}
