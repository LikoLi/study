package powermock.session2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Static.class, StaticService.class})
public class StaticTest {

    /**
     * Mock static method
     */
    @Test
    public void testCallStaticMethod() {
        PowerMockito.mockStatic(Static.class);
        Mockito.when(Static.callStaticMethod1("123")).thenReturn("234");
        String result = Static.callStaticMethod1("123");
        System.out.println(result);
        result = Static.callStaticMethod1("345");
        System.out.println(result);
    }

    /**
     * Verify behavior
     */
    @Test
    public void verifyBehavior() {
//        PowerMockito.verifyStatic();
        String result = Static.callStaticMethod1("123");
        System.out.println(result);
    }

    /**
     * Argument matchers
     */
    @Test
    public void verifyArgumentMatchers() {
//        PowerMockito.verifyStatic();
        String result = Static.callStaticMethod2(Mockito.anyInt());
        System.out.println(result);
    }

    /**
     * exact number
     */
    public void exactNumber() {
    }

    /**
     * throw exception
     */
    @Test
    public void throwException() {
    }
}

