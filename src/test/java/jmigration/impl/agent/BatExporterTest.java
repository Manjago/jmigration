package jmigration.impl.agent;

import jmigration.common.Ftn4D;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class BatExporterTest {
    @Test
    public void testDecToHex() throws Exception {
        TestCase.assertEquals("0000000D", BatExporter.decToHex(13, 8));
        TestCase.assertEquals("139C", BatExporter.decToHex(5020, 4));
        TestCase.assertEquals("033C", BatExporter.decToHex(828, 4));
    }

    @Test
    public void testDecToHex2() throws Exception {
        TestCase.assertEquals("0000000D", BatExporter.decToHex("13", 8));
        TestCase.assertEquals("139C", BatExporter.decToHex("5020", 4));
        TestCase.assertEquals("033C", BatExporter.decToHex("828", 4));
    }

    @Test(expected = NumberFormatException.class)
    public void testDecToHexBad() throws Exception {
        BatExporter.decToHex("k13", 8);
    }

    @Test
    public void testFileNameNode() throws Exception {
        TestCase.assertEquals("13A604E8.*", BatExporter.getFilename(new Ftn4D("2", "5030", "1256")));
    }

    @Test
    public void testFileNamePoint() throws Exception {
        TestCase.assertEquals("139C033C.PNT\\00000007.*", BatExporter.getFilename(new Ftn4D("2", "5020", "828", "7")));
    }

}
