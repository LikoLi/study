package powermock.session5;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import powermock.session4.Employee;
import powermock.session4.EmployeeDao;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeService.class)
public class EmployeeServiceTest {
    @Test
    public void testGetTotalEmployeeWithMock() throws Exception {
        EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
        PowerMockito.whenNew(EmployeeDao.class).withNoArguments().thenReturn(employeeDao);
        PowerMockito.when(employeeDao.getTotal()).thenReturn(10);

        EmployeeService service = new EmployeeService();
        int total = service.getTotalEmployee();
        assertEquals(10, total);
    }

    @Test
    public void testCreateEmployee() throws Exception {
        EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
        PowerMockito.whenNew(EmployeeDao.class).withNoArguments().thenReturn(employeeDao);
        Employee employee = new Employee();
        PowerMockito.doNothing().when(employeeDao).addEmployee(employee);
        EmployeeService service = new EmployeeService();
        service.createEmployee(employee);

        Mockito.verify(employeeDao).addEmployee(employee);
    }

}
