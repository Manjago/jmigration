package jmigration.impl.data;

import jmigration.common.*;
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
        file.addItem("1");
        file.addItem("2");
        file.addItem(null);

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
    public void testNullArgForEachPredicate1() throws Exception {
        Items<String> file = new Strings();
        file.forEach(null, null);
        TestCase.assertTrue(file.isEmpty());
    }

    @Test
    public void testNullArgForEachPredicate2() throws Exception {
        Items<String> file = new Strings();
        file.forEach(new Predicate<String>() {
            @Override
            public boolean passed(String item) {
                return true;
            }
        }, null);
        TestCase.assertTrue(file.isEmpty());
    }

    @Test
    public void testPredicate() throws Exception {
        Items<String> file = new Strings();
        file.addItem("1");
        file.addItem("2");
        file.addItem(null);
        final int[] i = {0};
        file.forEach(new Predicate<String>() {
                         @Override
                         public boolean passed(String item) {
                             return item != null && item.equals("2");
                         }
                     }, new Lambda<String, Void>() {
                         @Override
                         public Void execute(String arg) {
                             ++i[0];
                             return null;
                         }
                     }
        );
        TestCase.assertFalse(file.isEmpty());
        TestCase.assertEquals(1, i[0]);
    }

    @Test
    public void testNullForEach() throws Exception {
        Items<String> file = new ItemsNull<>();
        file.forEach(null);
        TestCase.assertTrue(file.isEmpty());
    }

    @Test
    public void testNullTrueForEach() throws Exception {
        Items<String> file = new ItemsNull<>();
        file.forEach(new Predicate<String>() {
            @Override
            public boolean passed(String item) {
                return false;
            }
        }, null);
        TestCase.assertTrue(file.isEmpty());
    }

    @Test
    public void testNullAddLine() throws Exception {
        Items<String> file = new ItemsNull<>();
        file.addItem("1");
        file.addItem("2");
        file.addItem(null);

        final List<String> test = Utils.getItems(file);

        TestCase.assertEquals(0, test.size());
        TestCase.assertTrue(file.isEmpty());
    }

}
