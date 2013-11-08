package jmigration.impl.data;

import jmigration.common.Items;
import jmigration.common.ItemsNull;
import jmigration.common.Lambda;
import jmigration.common.Strings;
import jmigration.impl.Utils;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public class StringsTest {
    @Test
    public void testAddLine() throws Exception {


        Items<String> file = new Strings();
        file.addLine("1");
        file.addLine("2");
        file.addLine(null);

        final List<String> test = Utils.getItems(file);

        TestCase.assertEquals(3, test.size());
        TestCase.assertEquals("1", test.get(0));
        TestCase.assertEquals("2", test.get(1));
        TestCase.assertNull(test.get(2));
        TestCase.assertFalse(file.isEmpty());
    }

    @Test
    public void testEmpty() throws Exception {
        Items<String> file = new Strings();
        file.forEach(new Lambda<String, Void>() {
            @Override
            public Void execute(String arg) {
                return null;
            }
        });
        TestCase.assertTrue(file.isEmpty());
    }

    @Test
    public void testNullArgForEach() throws Exception {
        Items<String> file = new Strings();
        file.forEach(null);
        TestCase.assertTrue(file.isEmpty());
    }

    @Test
    public void testNullForEach() throws Exception {
        Items<String> file = new ItemsNull<>();
        file.forEach(null);
        TestCase.assertTrue(file.isEmpty());
    }

    @Test
    public void testNullAddLine() throws Exception {
        Items<String> file = new ItemsNull<>();
        file.addLine("1");
        file.addLine("2");
        file.addLine(null);

        final List<String> test = Utils.getItems(file);

        TestCase.assertEquals(0, test.size());
        TestCase.assertTrue(file.isEmpty());
    }

}
