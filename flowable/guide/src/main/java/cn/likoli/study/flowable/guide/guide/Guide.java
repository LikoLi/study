package cn.likoli.study.flowable.guide.guide;

import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import java.util.List;

/**
 * Guide
 *
 * @author liko
 * @date 2021/3/15
 */
public class Guide {

    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault().buildProcessEngine();
//        RepositoryService repositoryService = processEngine.getRepositoryService();
//
//        Deployment deploy = repositoryService.createDeployment()
//                .addClasspathResource("FinancialReportProcess.bpmn20.xml")
//                .deploy();
//
//        RuntimeService runtimeService = processEngine.getRuntimeService();
//
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("financialReport");


        TaskService taskService = processEngine.getTaskService();
//        kermit 需要是accountancy组的成员
        List<Task> tasks = taskService.createTaskQuery().taskCandidateUser("fozzie").list();
//        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
        System.out.println("CandidateUser is fozzie, size: " + tasks.size());

        tasks.forEach(task -> taskService.claim(task.getId(), "fozzie"));

        tasks = taskService.createTaskQuery().taskAssignee("fozzie").list();

        System.out.println("Assignee is fozzie, size: " + tasks.size());

        taskService.complete(tasks.get(0).getId());


    }
}
