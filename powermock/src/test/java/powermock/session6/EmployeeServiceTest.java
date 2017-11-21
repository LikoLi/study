package powermock.session6;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import powermock.session4.Employee;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeUtils.class)
public class EmployeeServiceTest {
    @Test
    public void testGetEmployeeCountWithMock() {
        PowerMockito.mockStatic(EmployeeUtils.class);

        PowerMockito.when(EmployeeUtils.getEmployeeCount()).thenReturn(10);
        PowerMockito.when(EmployeeUtils.getLong(2)).thenReturn(1L);
        PowerMockito.when(EmployeeUtils.getInt(2)).thenReturn(3);

        final EmployeeService employeeService = new EmployeeService();

        int count = employeeService.getEmployeeService();
        long aLong = EmployeeUtils.getLong(2);
        long aInt = EmployeeUtils.getInt(2);

        Assert.assertEquals(1L, aLong);
        Assert.assertEquals(3, aInt);
        Assert.assertEquals(10, count);
    }

    @Test
    public void testCreateEmployeeWithMock() {
        PowerMockito.mockStatic(EmployeeUtils.class);
        Employee employee = new Employee();

        PowerMockito.doNothing().when(EmployeeUtils.class);

        final EmployeeService employeeService = new EmployeeService();

        employeeService.createEmployee(employee);

        PowerMockito.verifyStatic();
    }

}
