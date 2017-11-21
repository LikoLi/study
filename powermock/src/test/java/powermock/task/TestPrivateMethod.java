package powermock.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PrivateMethod.class)
public class TestPrivateMethod {
    @Test
    public void testPrivateMethod() throws Exception {
        PrivateMethod privateMethod = PowerMockito.spy(new PrivateMethod());
        List<String> list = new ArrayList<>();
        list.add("mock str");
        PowerMockito.when(privateMethod, "getList", "a").thenReturn(list);

        privateMethod.sout();
    }
}
