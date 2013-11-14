package jmigration.common;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class Ftn4DTest {
    @Test
    public void testFtnStr1() throws Exception {
       Ftn4D f = new Ftn4D("2", "5020", "828", "17");
       TestCase.assertEquals("2:5020/828.17", f.toFtnString());
    }

    @Test
    public void testFtnStr2() throws Exception {
        Ftn4D f = new Ftn4D("2", "5020", "828", "0");
        TestCase.assertEquals("2:5020/828.0", f.toFtnString());
    }

    @Test
    public void testStr1() throws Exception {
        Ftn4D f = Ftn4D.fromStr("2:5020/828.17");
        TestCase.assertEquals(new Ftn4D("2", "5020", "828", "17"), f);
    }

    @Test
    public void testStr2() throws Exception {
        Ftn4D f = Ftn4D.fromStr("2:5020/828");
        TestCase.assertEquals(new Ftn4D("2", "5020", "828", "0"), f);
    }

    @Test
    public void testStr3() throws Exception {
        Ftn4D f = Ftn4D.fromStr("2:5020/828");
        TestCase.assertEquals(new Ftn4D("2", "5020", "828"), f);
    }

    @Test
    public void testPoint() throws Exception {
        TestCase.assertEquals(true, new Ftn4D("2", "5020", "828", "10").isPoint() );
    }
    @Test
    public void testPoint2() throws Exception {
        TestCase.assertEquals(false, new Ftn4D("2", "5020", "828", "0").isPoint() );
    }
    @Test
    public void testPoint3() throws Exception {
        TestCase.assertEquals(false, new Ftn4D("2", "5020", "828").isPoint() );
    }
}
