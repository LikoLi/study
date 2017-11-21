package powermock.session7;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import powermock.session4.Employee;
import powermock.session4.EmployeeDao;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeService.class)
public class EmployeeServiceTest {
    @Test
    public void testSaveOrUpdateCountLessZero() throws Exception {
        EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
        PowerMockito.whenNew(EmployeeDao.class).withNoArguments().thenReturn(employeeDao);
        Employee employee = new Employee();

        PowerMockito.when(employeeDao.getCount(employee)).thenReturn(0L);
        EmployeeService employeeService = new EmployeeService();
        employeeService.saveOrUpdate(employee);

        Mockito.verify(employeeDao).saveEmployee(employee);

        Mockito.verify(employeeDao, Mockito.never()).updateEmployee(employee);
    }

    @Test
    public void testSaveOrUpdateCountMoreThanZero() throws Exception {
        EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);

        PowerMockito.whenNew(EmployeeDao.class).withNoArguments().thenReturn(employeeDao);

        Employee employee = new Employee();

        PowerMockito.when(employeeDao.getCount(employee)).thenReturn(1L);

        EmployeeService employeeService = new EmployeeService();

        employeeService.saveOrUpdate(employee);

        Mockito.verify(employeeDao).updateEmployee(employee);

        Mockito.verify(employeeDao, Mockito.never()).saveEmployee(employee);
    }
}
