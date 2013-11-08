package jmigration.impl.agent;

import jmigration.impl.Utils;
import jmigration.impl.data.Link;
import jmigration.impl.data.SourceData;
import jmigration.impl.data.TargetData;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static jmigration.impl.Utils.getPath;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class ConverterTest {
    @Ignore
    @Test
    public void testConvert() throws Exception {
        SourceResolver a = new SourceResolver();
        SourceData data = a.resolveSource(getPath("binkd.cfg.simple1"), getPath("SQAFIX.cfg"), getPath("SQUISH.cfg"));

        Converter converter = new Converter();
        TargetData targetData = new TargetData();
        converter.convert(data, targetData);

        List<Link> items = Utils.getItems(targetData);
        TestCase.assertEquals(9, items.size());

        TestCase.assertEquals("2:5020/1031", items.get(0).getFtnAddress());
        TestCase.assertEquals("2:5020/12000", items.get(1).getFtnAddress());
        TestCase.assertEquals("2:5020/828.17", items.get(2).getFtnAddress());
        TestCase.assertEquals("2:5020/828.18", items.get(3).getFtnAddress());
        TestCase.assertEquals("2:5020/828.19", items.get(4).getFtnAddress());
        TestCase.assertEquals("2:5020/1641", items.get(5).getFtnAddress());
        TestCase.assertEquals("2:5020/1042", items.get(6).getFtnAddress());
        TestCase.assertEquals("2:5020/837", items.get(7).getFtnAddress());
        TestCase.assertEquals("2:5020/1453", items.get(8).getFtnAddress());

        TestCase.assertEquals("fffffff1", items.get(0).getPassword());
        TestCase.assertEquals("fffffff2", items.get(1).getPassword());
        TestCase.assertEquals("fffff3", items.get(2).getPassword());
        TestCase.assertEquals("fffff4", items.get(3).getPassword());
        TestCase.assertEquals("fffff5", items.get(4).getPassword());
        TestCase.assertEquals("fffffff6", items.get(5).getPassword());
        TestCase.assertEquals("fffffff7", items.get(6).getPassword());
        TestCase.assertEquals("fffffff8", items.get(7).getPassword());
        TestCase.assertEquals("ffffff9", items.get(8).getPassword());

    }
}
