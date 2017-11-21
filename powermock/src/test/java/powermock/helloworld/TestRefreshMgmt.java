package powermock.helloworld;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({RefreshMgmt.class, Messages.class})
public class TestRefreshMgmt {

    @Test
    public void testDownloadFiles() throws Exception {
        PowerMockito.suppress(PowerMockito.method(Messages.class, "getString", String.class));
        RefreshMgmt mgmt = PowerMockito.mock(RefreshMgmt.class);
        PowerMockito.when(mgmt, "download", Matchers.anyString()).thenReturn(true);
        PowerMockito.when(mgmt.downloadFiles(Matchers.anyString())).thenCallRealMethod();
        Assert.assertTrue(mgmt.downloadFiles("C://temp"));
    }

}
