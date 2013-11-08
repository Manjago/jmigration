package jmigration.impl.data;

import jmigration.common.Lambda;
import jmigration.common.Strings;
import jmigration.common.StringsBase;
import jmigration.common.StringsNull;
import jmigration.impl.Utils;
import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public class StringsBaseTest {
    @Test
    public void testAddLine() throws Exception {
        Strings file = new StringsBase();
        file.addLine("1");
        file.addLine("2");
        file.addLine(null);

        final List<String> test = Utils.getStrings(file);

        Assert.assertEquals(3, test.size());
        Assert.assertEquals("1", test.get(0));
        Assert.assertEquals("2", test.get(1));
        Assert.assertNull(test.get(2));
        Assert.assertFalse(file.isEmpty());
    }

    @Test
    public void testEmpty() throws Exception {
        Strings file = new StringsBase();
        file.forEach(new Lambda<String, Void>() {
            @Override
            public Void execute(String arg) {
                return null;
            }
        });
        Assert.assertTrue(file.isEmpty());
    }

    @Test
    public void testNullArgForEach() throws Exception {
        Strings file = new StringsBase();
        file.forEach(null);
        Assert.assertTrue(file.isEmpty());
    }

    @Test
    public void testNullForEach() throws Exception {
        Strings file = new StringsNull();
        file.forEach(null);
        Assert.assertTrue(file.isEmpty());
    }

    @Test
    public void testNullAddLine() throws Exception {
        Strings file = new StringsNull();
        file.addLine("1");
        file.addLine("2");
        file.addLine(null);

        final List<String> test = Utils.getStrings(file);

        Assert.assertEquals(0, test.size());
        Assert.assertTrue(file.isEmpty());
    }

}
