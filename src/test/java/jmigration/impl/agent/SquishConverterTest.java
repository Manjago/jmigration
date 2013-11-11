package jmigration.impl.agent;

import jmigration.common.FreemarkerRunner;
import jmigration.impl.Utils;
import jmigration.impl.data.SourceData;
import jmigration.impl.data.TargetData;
import jmigration.impl.data.items.EchoArea;
import jmigration.impl.data.items.Link;
import junit.framework.TestCase;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.*;

import static jmigration.impl.Utils.getPath;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class SquishConverterTest {

    private List<EchoArea> areas;

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

    }

    @Test
    public void testConvert() throws Exception {

        TestCase.assertEquals("5020-828.FORWARDS", areas.get(0).getName());
        TestCase.assertEquals("5020-828.LOCAL", areas.get(1).getName());
        TestCase.assertEquals("5020-828.OFFICIAL", areas.get(2).getName());
        TestCase.assertEquals("828.Robots", areas.get(3).getName());
        TestCase.assertEquals("RU.SPORT.OTHER", areas.get(4).getName());

        TestCase.assertEquals("5020-828.FORWARDS", areas.get(0).getDesc());
        TestCase.assertEquals("5020-828.LOCAL", areas.get(1).getDesc());
        TestCase.assertEquals("5020-828.OFFICIAL", areas.get(2).getDesc());
        TestCase.assertEquals("828.Robots", areas.get(3).getDesc());
        TestCase.assertEquals("Споpт вообще", areas.get(4).getDesc());
        
    }

}
