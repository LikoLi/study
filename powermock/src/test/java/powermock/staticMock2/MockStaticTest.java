package powermock.staticMock2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MockDao.class)
public class MockStaticTest {

    @InjectMocks
    MockStatic mockStatic;

    @Test
    public void testMockStatic1() {

        mockStatic(MockDao.class);

        when(mockStatic.mockStatic()).thenReturn(2);
        System.out.println(mockStatic.mockStatic());

        System.out.println(mockStatic.mockStatic1());
    }
}
