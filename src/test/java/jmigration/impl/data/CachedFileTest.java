package jmigration.impl.data;

import jmigration.common.Lambda;
import jmigration.impl.Utils;
import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public class CachedFileTest {
    @Test
    public void testAddLine() throws Exception {
        CachedFile file = new CachedFile();
        file.addLine("1");
        file.addLine("2");
        file.addLine(null);

        final List<String> test = Utils.getStrings(file);

        Assert.assertEquals(3, test.size());
        Assert.assertEquals("1", test.get(0));
        Assert.assertEquals("2", test.get(1));
        Assert.assertNull(test.get(2));
    }

    @Test
    public void testEmpty() throws Exception {
        CachedFile file = new CachedFile();
        file.forEach(new Lambda<String, Void>() {
            @Override
            public Void execute(String arg) {
                return null;
            }
        });
    }
}
