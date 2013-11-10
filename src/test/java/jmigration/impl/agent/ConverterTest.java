package jmigration.impl.agent;

import jmigration.impl.Utils;
import jmigration.impl.data.Link;
import jmigration.impl.data.SourceData;
import jmigration.impl.data.TargetData;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static jmigration.impl.Utils.getPath;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class ConverterTest {
    @Test
    public void testConvert() throws Exception {
        SourceResolver a = new SourceResolver();
        SourceData data = a.resolveSource(getPath("binkd.cfg.simple1"), getPath("SQAFIX.cfg"), getPath("SQUISH.cfg"));

        Converter converter = new Converter();
        TargetData targetData = new TargetData();
        converter.convert(data, targetData);

        List<Link> items = Utils.getItems(targetData);
        TestCase.assertEquals(9, items.size());

        Collections.sort(items, new Comparator<Link>() {
            @Override
            public int compare(Link o1, Link o2) {
                return o1.getPassword().compareTo(o2.getPassword());
            }
        });

        TestCase.assertEquals("2:5020/1031", items.get(0).getFtnAddress());
        TestCase.assertEquals("2:5020/12000", items.get(1).getFtnAddress());
        TestCase.assertEquals("2:5020/828.17", items.get(2).getFtnAddress());
        TestCase.assertEquals("2:5020/828.18", items.get(3).getFtnAddress());
        TestCase.assertEquals("2:5020/828.19", items.get(4).getFtnAddress());
        TestCase.assertEquals("2:5020/1641", items.get(5).getFtnAddress());
        TestCase.assertEquals("2:5020/1042", items.get(6).getFtnAddress());
        TestCase.assertEquals("2:5020/837", items.get(7).getFtnAddress());
        TestCase.assertEquals("2:5020/1453", items.get(8).getFtnAddress());

        TestCase.assertEquals("mvgusev.no-ip.org", items.get(0).getHost());
        TestCase.assertEquals("fluid.fidoman.ru", items.get(1).getHost());
        TestCase.assertEquals("", items.get(2).getHost());
        TestCase.assertEquals("", items.get(3).getHost());
        TestCase.assertEquals("", items.get(4).getHost());
        TestCase.assertEquals("fido.t-d-g.ru", items.get(5).getHost());
        TestCase.assertEquals("f1042.ru", items.get(6).getHost());
        TestCase.assertEquals("nightbbs.ru", items.get(7).getHost());
        TestCase.assertEquals("80.240.218.178", items.get(8).getHost());

        TestCase.assertEquals("1fffffff1", items.get(0).getPassword());
        TestCase.assertEquals("2fffffff2", items.get(1).getPassword());
        TestCase.assertEquals("3fffff3", items.get(2).getPassword());
        TestCase.assertEquals("4fffff4", items.get(3).getPassword());
        TestCase.assertEquals("5fffff5", items.get(4).getPassword());
        TestCase.assertEquals("6fffffff6", items.get(5).getPassword());
        TestCase.assertEquals("7fffffff7", items.get(6).getPassword());
        TestCase.assertEquals("8fffffff8", items.get(7).getPassword());
        TestCase.assertEquals("9ffffff9", items.get(8).getPassword());

    }
}
