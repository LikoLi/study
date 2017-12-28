package powermock.privateMock;

import org.junit.Test;
import powermock.privateMock.PrivateMock;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.powermock.api.mockito.PowerMockito.*;

/**
 * test private method
 */
public class PrivateMockTest {
    @Test
    public void testToUpperCase() throws InvocationTargetException, IllegalAccessException {
        PrivateMock privateMethod = new PrivateMock();
        Method toUpperCase = method(PrivateMock.class, "toUpperCase", String.class);
        String upperStr = (String) toUpperCase.invoke(privateMethod, "abdsdfdsa");
        System.out.println(upperStr);
    }
}
