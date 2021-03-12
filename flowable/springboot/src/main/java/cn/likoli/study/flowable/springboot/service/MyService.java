package cn.likoli.study.flowable.springboot.service;

import cn.likoli.study.flowable.springboot.entity.Person;
import cn.likoli.study.flowable.springboot.repository.PersonRepository;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MyService
 *
 * @author liko
 * @date 2021/3/12
 */
@Service
@Transactional
public class MyService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    public void startProcess(String assignee) {
        Person person = personRepository.findByUsername(assignee);
        Map<String, Object> variables = new HashMap<>();
        variables.put("person", person);

        runtimeService.startProcessInstanceByKey("oneTaskProcess", variables);
    }

    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    public void createDemoUsers() {
        if (personRepository.findAll().size() == 0) {
            personRepository.save(new Person("jbarrez", "Joram", "Barrez", new Date()));
            personRepository.save(new Person("trademakers", "Tijs", "Rademakers", new Date()));
        }
    }
}
