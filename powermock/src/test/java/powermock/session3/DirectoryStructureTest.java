package powermock.session3;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.powermock.api.mockito.PowerMockito.*;
import static org.junit.Assert.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DirectoryStructure.class)
public class DirectoryStructureTest {
    @Test
    public void createDirectoryStructureWhenPathDoesNotExist() throws Exception {
        final String directoryPath = "mocked path";

        File directoryMock = mock(File.class);

        whenNew(File.class).withArguments(directoryPath).thenReturn(directoryMock);

        when(directoryMock.exists()).thenReturn(false);

        when(directoryMock.mkdirs()).thenReturn(true);

        assertTrue(new DirectoryStructure().create(directoryPath));

        verifyNew(File.class).withArguments(directoryPath);
    }
}
