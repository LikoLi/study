package powermock.staticMock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MockStatic.class)
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

    @Test
    public void testMockStatic1() {
        mockStatic(MockStatic.class);

        MockStatic.mockStatic1();

    }
}
