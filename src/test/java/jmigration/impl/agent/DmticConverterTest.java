package jmigration.impl.agent;

import jmigration.impl.Utils;
import jmigration.impl.data.SourceData;
import jmigration.impl.data.TargetData;
import jmigration.impl.data.items.EchoArea;
import jmigration.impl.data.items.Subscr;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static jmigration.impl.Utils.getPath;

/**
 * @author Kirill Temnenkov (kirill@temnenkov.com)
 */
public class DmticConverterTest {

    private List<EchoArea> areas;
    private List<Subscr> subscrs;

    @Before
    public void setUp() throws Exception {
        SourceResolver a = new SourceResolver();
        SourceData data = a.resolveSource(getPath("binkd.cfg"), getPath("SQAFIX.cfg"), getPath("SQUISH.cfg"), getPath("dmtareas.ini.simple"), "2:5020/1042");

        Converter converter = new Converter();
        TargetData targetData = new TargetData();
        converter.convert(data, targetData);

        areas = Utils.getItems(targetData.asFileAreas());
        TestCase.assertEquals(2, areas.size());

        Collections.sort(areas, new Comparator<EchoArea>() {
            @Override
            public int compare(EchoArea o1, EchoArea o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        subscrs = Utils.getItems(targetData.asFilesubscr());
        Collections.sort(subscrs, new Comparator<Subscr>() {
            @Override
            public int compare(Subscr o1, Subscr o2) {
                int temp = o1.getArea().compareTo(o2.getArea());
                if (temp != 0) {
                    return temp;
                }

                return o1.getNode().compareTo(o2.getNode());

            }
        });
        TestCase.assertEquals(10, subscrs.size());
    }

    @Test
    public void testConvert() throws Exception {

        TestCase.assertEquals("FAR", areas.get(0).getName());
        TestCase.assertEquals("PNT5020", areas.get(1).getName());

        TestCase.assertEquals("Софт касающийся программы FAR by Eugene Roshal", areas.get(0).getDesc());
        TestCase.assertEquals("Moscow pointlist", areas.get(1).getDesc());

        TestCase.assertEquals("FAR", subscrs.get(0).getArea());
        TestCase.assertEquals("2:5020/12000", subscrs.get(0).getNode());
        TestCase.assertEquals("FAR", subscrs.get(1).getArea());
        TestCase.assertEquals("2:5020/828.44", subscrs.get(1).getNode());

        TestCase.assertEquals("PNT5020", subscrs.get(2).getArea());
        TestCase.assertEquals("2:5020/2160", subscrs.get(2).getNode());
        TestCase.assertEquals("PNT5020", subscrs.get(3).getArea());
        TestCase.assertEquals("2:5020/828.1373", subscrs.get(3).getNode());
        TestCase.assertEquals("PNT5020", subscrs.get(4).getArea());
        TestCase.assertEquals("2:5020/828.16", subscrs.get(4).getNode());
        TestCase.assertEquals("PNT5020", subscrs.get(5).getArea());
        TestCase.assertEquals("2:5020/828.17", subscrs.get(5).getNode());
        TestCase.assertEquals("PNT5020", subscrs.get(6).getArea());
        TestCase.assertEquals("2:5020/828.27", subscrs.get(6).getNode());
        TestCase.assertEquals("PNT5020", subscrs.get(7).getArea());
        TestCase.assertEquals("2:5020/828.529", subscrs.get(7).getNode());
        TestCase.assertEquals("PNT5020", subscrs.get(8).getArea());
        TestCase.assertEquals("2:5020/828.67", subscrs.get(8).getNode());
        TestCase.assertEquals("PNT5020", subscrs.get(9).getArea());
        TestCase.assertEquals("2:5020/828.91", subscrs.get(9).getNode());


    }

}
