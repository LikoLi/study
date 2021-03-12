package cn.likoli.study.flowable.helloworld;

import org.flowable.engine.test.Deployment;
import org.flowable.engine.test.FlowableRule;
import org.flowable.engine.test.FlowableTestCase;
import org.flowable.task.api.Task;
import org.junit.Rule;

/**
 * MyBusinessProcessTest
 *
 * @author liko
 * @date 2021/3/12
 */
public class MyBusinessProcessTest extends FlowableTestCase {

    @Rule
    public FlowableRule flowableRule = new FlowableRule();

    @Deployment
    public void testSimpleProcess() {
        runtimeService.startProcessInstanceByKey("holidayRequest");

        Task task = taskService.createTaskQuery().singleResult();
        assertEquals("My Task", task.getName());

        taskService.complete(task.getId());
        assertEquals(0, runtimeService.createProcessInstanceQuery().count());
    }
}
