package jmigration;

import org.junit.Test;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public class UFMSTest {

    @Test
    public void testLoad() throws Exception {
        new UFMS().load();
    }

}
