package powermock.session4;

import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import static org.junit.Assert.*;

/**
 * Powermockito.mock() : 方法根据class创建一个对应的mock对象, Powermock的创建方式可不像Easymock等使用proxy的方式创建, 他是会在你运行的过程中动态的修改class字节码文件的形式来创建
 *
 * Do...When...Then: 当我们想给一个mock的对象进行某种行为的预测时, 都会使用 do..when..then这样的语法
 *
 * Verify: 当我们测试一个void方法的时候, 根本没有办法去验证一个mock对象所执行后的结果, 因此唯一的方法就是检查方法是否被调用
 *
 *
 */

public class EmployeeServiceTest {

    @Test
    public void testGetTotalEmployee() {
        final EmployeeDao employeeDao = new EmployeeDao();
        final EmployeeService employeeService = new EmployeeService(employeeDao);
        int total = employeeService.getTotalEmployee();
        assertEquals(10, total);
    }

    @Test
    public void testGetTotalEmployeeWithMock() {
        EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
        PowerMockito.when(employeeDao.getTotal()).thenReturn(10);
        EmployeeService service = new EmployeeService(employeeDao);
        int total = service.getTotalEmployee();
        assertEquals(10, total);
    }

    @Test
    public void testCreateEmployee() {
        EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
        Employee employee = new Employee();
        PowerMockito.doNothing().when(employeeDao).addEmployee(employee);
        EmployeeService service = new EmployeeService(employeeDao);
        service.createEmployee(employee);

        //verify the method invocation
        /**
         * **********************************************
         * Verify: 验证被mock出来的对象的某个方法是否被调用
         * **********************************************
         */
        Mockito.verify(employeeDao).addEmployee(employee);

//        Mockito.verify(employeeDao).getTotal();
    }
}
