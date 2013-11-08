package jmigration.impl.data;

import jmigration.common.*;
import jmigration.common.Items;
import jmigration.common.ItemsNull;
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
        Items file = new ItemsBase();
        file.addLine("1");
        file.addLine("2");
        file.addLine(null);

        final List<String> test = Utils.getItems(file);

        Assert.assertEquals(3, test.size());
        Assert.assertEquals("1", test.get(0));
        Assert.assertEquals("2", test.get(1));
        Assert.assertNull(test.get(2));
        Assert.assertFalse(file.isEmpty());
    }

    @Test
    public void testEmpty() throws Exception {
        Items file = new ItemsBase();
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
        Items file = new ItemsBase();
        file.forEach(null);
        Assert.assertTrue(file.isEmpty());
    }

    @Test
    public void testNullForEach() throws Exception {
        Items file = new ItemsNull();
        file.forEach(null);
        Assert.assertTrue(file.isEmpty());
    }

    @Test
    public void testNullAddLine() throws Exception {
        Items file = new ItemsNull();
        file.addLine("1");
        file.addLine("2");
        file.addLine(null);

        final List<String> test = Utils.getItems(file);

        Assert.assertEquals(0, test.size());
        Assert.assertTrue(file.isEmpty());
    }

}
