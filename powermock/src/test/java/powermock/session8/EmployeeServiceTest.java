package powermock.session8;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeService.class)
public class EmployeeServiceTest {
    @Test
    public void testExist() throws Exception {
        EmployeeService employeeService = PowerMockito.spy(new EmployeeService());
        PowerMockito.doNothing().when(employeeService, "checkExist", "liko");
        boolean result = employeeService.exist("liko");
        Assert.assertTrue(result);
        PowerMockito.verifyPrivate(employeeService).invoke("checkExist", "liko");
    }

}
