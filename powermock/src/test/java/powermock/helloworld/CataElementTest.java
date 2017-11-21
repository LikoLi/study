package powermock.helloworld;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import java.util.List;

public class CataElementTest {
    @Test
    public void test() {
        CataElement e = new CataElement();
        Whitebox.setInternalState(e, "isAvailable", true);
        List<FileItem> items = Whitebox.getInternalState(e, "items");
        Assert.assertTrue(items.size() == 0);

    }
}
