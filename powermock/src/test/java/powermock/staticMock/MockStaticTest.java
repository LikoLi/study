package powermock.staticMock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
public class MockStaticTest {
    @Test
    public void testMockStatic() throws Exception {
        MockDao mockDao = mock(MockDao.class);
//        whenNew(MockDao.class).withAnyArguments().thenReturn(mockDao);

        /**
         * 设置静态字段
         */
        Whitebox.setInternalState(MockStatic.class, mockDao);
        doNothing().when(mockDao).call();

        MockStatic.mockStatic();
//        verifyStatic();
        Mockito.verify(mockDao.getClass());
    }
}
