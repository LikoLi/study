package powermock.session3;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Method;

@RunWith(PowerMockRunner.class)
@PrepareForTest({IdGenerator.class, ClassUnderTest.class})
public class TestClassUnderTest {

    /**
     * Static
     */
    @Test
    public void mockStatic() {
        PowerMockito.mockStatic(IdGenerator.class);
        PowerMockito.when(IdGenerator.generateNewId()).thenReturn(2L);
        new ClassUnderTest().methodTest();

        PowerMockito.verifyStatic();
        System.out.println(IdGenerator.generateNewId());
    }


    /**
     * private
     */
    @Test
    public void demoPrivateMethodMocking() throws Exception {
        final String expected = "TEST VALUE";
        final String nameOfMethodToMock = "methodToMock";
        final String input = "input";

        ClassUnderTest underTest = PowerMockito.spy(new ClassUnderTest());
        PowerMockito.when(underTest, nameOfMethodToMock, input).thenReturn(expected);

        Assert.assertEquals(expected, underTest.pm());

        PowerMockito.verifyPrivate(underTest).invoke(nameOfMethodToMock, input);
    }

    /**
     * private 2
     */
    @Test
    public void demoPrivateMethodMocking2() throws Exception {
        final String expected = "TEST VALUE";
        final String input = "input";
        /*
         * We get the method to mock by specifying the class where th method is defined as well as its parameter types
         */
        final Method methodToMock = PowerMockito.method(ClassUnderTest.class, String.class);

        ClassUnderTest underTest = PowerMockito.spy(new ClassUnderTest());

        //Notice how we pass the actual method instead of the name
        PowerMockito.when(underTest, methodToMock).withArguments(input).thenReturn(expected);

        Assert.assertEquals(expected, underTest.pm());

        PowerMockito.verifyPrivate(underTest).invoke(methodToMock).withArguments(input);
    }

}
