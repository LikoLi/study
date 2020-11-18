package powermock.helloworld;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ClassUnderTest.class)
public class TestClassUnderTest {
//    @Test
//    @PrepareForTest(ClassUnderTest.class)
//    public void testCallInternalInstance() throws Exception {
//        File file = PowerMockito.mock(File.class);
//        ClassUnderTest underTest = new ClassUnderTest();
//        PowerMockito.whenNew(File.class).withArguments("bind").thenReturn(file);
//        PowerMockito.when(file.exists()).thenReturn(true);
//        Assert.assertTrue(underTest.callInternalInstance("bind"));
//    }

//    @Test

    //    @PrepareForTest(Dependency.class)
//    public void testCallFinalMethod() throws Exception {
//        Dependency dependency = PowerMockito.mock(Dependency.class);
//        ClassUnderTest underTest = new ClassUnderTest();
//        PowerMockito.when(dependency.isAlive()).thenReturn(false);
//        Assert.assertFalse(underTest.callFinalMethod(dependency));

    /**
     * 这个有问题
     *
     * @throws Exception
     */
    @Test
    public void testCallPrivateMethod() throws Exception {
        ClassUnderTest underTest = new ClassUnderTest();
        PowerMockito.when(underTest.callPrivateMethod()).thenCallRealMethod();
        PowerMockito.when(underTest, "isAlive").thenReturn(true);
        Assert.assertTrue(underTest.callPrivateMethod());
    }
//    }
}

