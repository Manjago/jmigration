package jmigration.impl.agent;

import jmigration.common.FreemarkerRunner;
import jmigration.impl.Utils;
import jmigration.impl.data.SourceData;
import jmigration.impl.data.TargetData;
import jmigration.impl.data.items.EchoArea;
import jmigration.impl.data.items.Link;
import jmigration.impl.data.items.Subscr;
import junit.framework.TestCase;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.*;

import static jmigration.impl.Utils.getPath;

/**
 * @author Kirill Temnenkov (kirill@temnenkov.com)
 */
public class SquishConverterTest {

    private List<EchoArea> areas;
    private List<Subscr> subscrs;

    @Before
    public void setUp() throws Exception {
        SourceResolver a = new SourceResolver();
        SourceData data = a.resolveSource(getPath("binkd.cfg"), getPath("SQAFIX.cfg"), getPath("SQUISH.cfg.simple2"));

        Converter converter = new Converter();
        TargetData targetData = new TargetData();
        converter.convert(data, targetData);

        areas = Utils.getItems(targetData.asAreas());
        TestCase.assertEquals(5, areas.size());

        Collections.sort(areas, new Comparator<EchoArea>() {
            @Override
            public int compare(EchoArea o1, EchoArea o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        subscrs = Utils.getItems(targetData.getSubscr());
        Collections.sort(subscrs, new Comparator<Subscr>() {
            @Override
            public int compare(Subscr o1, Subscr o2) {
                int temp = o1.getArea().compareTo(o2.getArea());
                if (temp != 0){
                    return temp;
                }

                return o1.getNode().compareTo(o2.getNode());

            }
        });

    }

    @Test
    public void testConvert() throws Exception {

        TestCase.assertEquals("5020-828.FORWARDS", areas.get(0).getName());
        TestCase.assertEquals("5020-828.LOCAL", areas.get(1).getName());
        TestCase.assertEquals("5020-828.OFFICIAL", areas.get(2).getName());
        TestCase.assertEquals("828.ROBOTS", areas.get(3).getName());
        TestCase.assertEquals("RU.SPORT.OTHER", areas.get(4).getName());

        TestCase.assertEquals("5020-828.FORWARDS", areas.get(0).getDesc());
        TestCase.assertEquals("5020-828.LOCAL", areas.get(1).getDesc());
        TestCase.assertEquals("5020-828.OFFICIAL", areas.get(2).getDesc());
        TestCase.assertEquals("828.Robots", areas.get(3).getDesc());
        TestCase.assertEquals("Споpт вообще", areas.get(4).getDesc());



        TestCase.assertEquals("5020-828.FORWARDS", subscrs.get(0).getArea());
        TestCase.assertEquals("2:6000/8632", subscrs.get(0).getNode());

        TestCase.assertEquals("5020-828.LOCAL", subscrs.get(1).getArea());
        TestCase.assertEquals("2:6000/8632", subscrs.get(1).getNode());

        TestCase.assertEquals("5020-828.OFFICIAL", subscrs.get(2).getArea());
        TestCase.assertEquals("2:6000/8632", subscrs.get(2).getNode());

        TestCase.assertEquals("828.ROBOTS", subscrs.get(3).getArea());
        TestCase.assertEquals("2:5020/5052", subscrs.get(3).getNode());
        TestCase.assertEquals("828.ROBOTS", subscrs.get(4).getArea());
        TestCase.assertEquals("2:5020/828.10", subscrs.get(4).getNode());
        TestCase.assertEquals("828.ROBOTS", subscrs.get(5).getArea());
        TestCase.assertEquals("2:5020/828.128", subscrs.get(5).getNode());
        TestCase.assertEquals("828.ROBOTS", subscrs.get(6).getArea());
        TestCase.assertEquals("2:5020/828.1337", subscrs.get(6).getNode());
        TestCase.assertEquals("828.ROBOTS", subscrs.get(7).getArea());
        TestCase.assertEquals("2:5020/828.14", subscrs.get(7).getNode());
        TestCase.assertEquals("828.ROBOTS", subscrs.get(8).getArea());
        TestCase.assertEquals("2:5020/828.16", subscrs.get(8).getNode());
        TestCase.assertEquals("828.ROBOTS", subscrs.get(9).getArea());
        TestCase.assertEquals("2:5020/828.17", subscrs.get(9).getNode());
        TestCase.assertEquals("828.ROBOTS", subscrs.get(10).getArea());
        TestCase.assertEquals("2:5020/828.67", subscrs.get(10).getNode());
        TestCase.assertEquals("828.ROBOTS", subscrs.get(11).getArea());
        TestCase.assertEquals("2:5020/828.777", subscrs.get(11).getNode());
        TestCase.assertEquals("828.ROBOTS", subscrs.get(12).getArea());
        TestCase.assertEquals("2:5030/1111", subscrs.get(12).getNode());
        TestCase.assertEquals("828.ROBOTS", subscrs.get(13).getArea());
        TestCase.assertEquals("2:6000/8632", subscrs.get(13).getNode());

        TestCase.assertEquals("RU.SPORT.OTHER", subscrs.get(14).getArea());
        TestCase.assertEquals("2:5020/1042", subscrs.get(14).getNode());
        TestCase.assertEquals("RU.SPORT.OTHER", subscrs.get(15).getArea());
        TestCase.assertEquals("2:5020/828.777", subscrs.get(15).getNode());
        TestCase.assertEquals("RU.SPORT.OTHER", subscrs.get(16).getArea());
        TestCase.assertEquals("2:6000/8632", subscrs.get(16).getNode());



    }

}
