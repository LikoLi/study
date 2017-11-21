package powermock.helloworld;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

//@RunWith(PowerMockRunner.class)
public class MyPowerMockTest {

    /**
     * 普通Mock: Mock参数传递的对象
     * 普通Mock不能加@RunWith和@PrepareForTest注解。加了会导致异常
     * 与下面的方法不兼容所以需要注释
     */
//    @Test
//    public void testCallArgumentInstance() {
//        File file = PowerMockito.mock(File.class);
//        MyPowerMock myPowerMock = new MyPowerMock();
//        PowerMockito.when(file.exists()).thenReturn(true);
//        Assert.assertTrue(myPowerMock.callArgumentInstance(file));
//    }

    /**
     * Mock方法内部new出来的对象
     * 当使用PowerMockito.whenNew方法时，必须加注解@PrepareForTest和@RunWith。注解@PrepareForTest里写的类是需要mock的new对象代码所在的类。
     */
//    @Test
//    @PrepareForTest(MyPowerMock.class)
//    public void testCallInternalInstance() throws Exception {
//        File file = PowerMockito.mock(File.class);
//
//        MyPowerMock powerMock = new MyPowerMock();
//
//        PowerMockito.whenNew(File.class).withArguments("bbb").thenReturn(file);
//
//        PowerMockito.when(file.exists()).thenReturn(true);
//
//        Assert.assertTrue(powerMock.callInternalInstance("bbb"));
//    }
//
//    @Test
//    @PrepareForTest(ClassDependency.class)
//    public void testCallFinalMethod() {
//        ClassDependency dependency = PowerMockito.mock(ClassDependency.class);
//
//        MyPowerMock powerMock = new MyPowerMock();
//
//        PowerMockito.when(dependency.isAlive()).thenReturn(true);
//
//        Assert.assertTrue(powerMock.callFinalMethod(dependency));
//    }

    @Test
    public void testGetPayloadName() throws Exception {
        FileItem item = PowerMockito.mock(FileItem.class);
        String filePath = "../../../test/updates/Citrix.ibr";
        PowerMockito.when(item.getFilePath()).thenReturn(filePath);
        PowerMockito.when(item, "getPayloadName").thenCallRealMethod();
        Assert.assertEquals("Citrix.ibr", item.getPayloadName());
    }
}
