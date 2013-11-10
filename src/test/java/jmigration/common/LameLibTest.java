package jmigration.common;

import org.junit.Test;

import static jmigration.common.LameLib.checkNotNull;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public class LameLibTest {
    @Test
    public void testCheckNotNull() throws Exception {
        checkNotNull("1");
    }

    @Test(expected = NullPointerException.class)
    public void testCheckNotNull2() throws Exception {
        checkNotNull(null);
    }

    @Test
    public void testCheckNotNullExt() throws Exception {
        checkNotNull("1", "2");
    }

    @Test(expected = NullPointerException.class)
    public void testCheckNotNullExtBad() throws Exception {
        checkNotNull(null, "2");
    }
}
