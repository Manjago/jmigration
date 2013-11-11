package jmigration.impl.agent;

import jmigration.common.FreemarkerRunner;
import jmigration.impl.Utils;
import jmigration.impl.data.SourceData;
import jmigration.impl.data.TargetData;
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
 * @author Kirill Temnenkov (kirill@temnenkov.com)
 */
public class BinkConverterTest {

    private List<Link> items;

    @Before
    public void setUp() throws Exception {
        SourceResolver a = new SourceResolver();
        SourceData data = a.resolveSource(getPath("binkd.cfg.simple1"), getPath("SQAFIX.cfg"), getPath("SQUISH.cfg"), "2:5020/1042");

        Converter converter = new Converter();
        TargetData targetData = new TargetData();
        converter.convert(data, targetData);

        items = Utils.getItems(targetData.asLinks());
        TestCase.assertEquals(9, items.size());

        Collections.sort(items, new Comparator<Link>() {
            @Override
            public int compare(Link o1, Link o2) {
                return o1.getPassword().compareTo(o2.getPassword());
            }
        });

    }

    @Test
    public void testConvert() throws Exception {

        TestCase.assertEquals("2:5020/1031", items.get(0).getFtnAddress());
        TestCase.assertEquals("2:5020/12000", items.get(1).getFtnAddress());
        TestCase.assertEquals("2:5020/828.17", items.get(2).getFtnAddress());
        TestCase.assertEquals("2:5020/828.18", items.get(3).getFtnAddress());
        TestCase.assertEquals("2:5020/828.19", items.get(4).getFtnAddress());
        TestCase.assertEquals("2:5020/1641", items.get(5).getFtnAddress());
        TestCase.assertEquals("2:5020/1042", items.get(6).getFtnAddress());
        TestCase.assertEquals("2:5020/837", items.get(7).getFtnAddress());
        TestCase.assertEquals("2:5020/1453", items.get(8).getFtnAddress());

        TestCase.assertEquals("2:5020/1031", items.get(0).getStationName());
        TestCase.assertEquals("2:5020/12000", items.get(1).getStationName());
        TestCase.assertEquals("2:5020/828.17", items.get(2).getStationName());
        TestCase.assertEquals("2:5020/828.18", items.get(3).getStationName());
        TestCase.assertEquals("2:5020/828.19", items.get(4).getStationName());
        TestCase.assertEquals("2:5020/1641", items.get(5).getStationName());
        TestCase.assertEquals("2:5020/1042", items.get(6).getStationName());
        TestCase.assertEquals("2:5020/837", items.get(7).getStationName());
        TestCase.assertEquals("2:5020/1453", items.get(8).getStationName());

        TestCase.assertEquals("mvgusev.no-ip.org", items.get(0).getHost());
        TestCase.assertEquals("fluid.fidoman.ru", items.get(1).getHost());
        TestCase.assertEquals("", items.get(2).getHost());
        TestCase.assertEquals("", items.get(3).getHost());
        TestCase.assertEquals("", items.get(4).getHost());
        TestCase.assertEquals("fido.t-d-g.ru", items.get(5).getHost());
        TestCase.assertEquals("f1042.ru", items.get(6).getHost());
        TestCase.assertEquals("nightbbs.ru", items.get(7).getHost());
        TestCase.assertEquals("80.240.218.178", items.get(8).getHost());

        TestCase.assertEquals("24554", items.get(0).getPort());
        TestCase.assertEquals("24554", items.get(1).getPort());
        TestCase.assertEquals("0", items.get(2).getPort());
        TestCase.assertEquals("0", items.get(3).getPort());
        TestCase.assertEquals("0", items.get(4).getPort());
        TestCase.assertEquals("24554", items.get(5).getPort());
        TestCase.assertEquals("24554", items.get(6).getPort());
        TestCase.assertEquals("24554", items.get(7).getPort());
        TestCase.assertEquals("24554", items.get(8).getPort());

        TestCase.assertEquals("1fffffff1", items.get(0).getPassword());
        TestCase.assertEquals("2fffffff2", items.get(1).getPassword());
        TestCase.assertEquals("3fffff3", items.get(2).getPassword());
        TestCase.assertEquals("4fffff4", items.get(3).getPassword());
        TestCase.assertEquals("5fffff5", items.get(4).getPassword());
        TestCase.assertEquals("6fffffff6", items.get(5).getPassword());
        TestCase.assertEquals("7fffffff7", items.get(6).getPassword());
        TestCase.assertEquals("8fffffff8", items.get(7).getPassword());
        TestCase.assertEquals("9ffffff9", items.get(8).getPassword());

        TestCase.assertEquals("1fffffff1", items.get(0).getPktPassword());
        TestCase.assertEquals("2fffffff2", items.get(1).getPktPassword());
        TestCase.assertEquals("3fffff3", items.get(2).getPktPassword());
        TestCase.assertEquals("4fffff4", items.get(3).getPktPassword());
        TestCase.assertEquals("5fffff5", items.get(4).getPktPassword());
        TestCase.assertEquals("6fffffff6", items.get(5).getPktPassword());
        TestCase.assertEquals("7fffffff7", items.get(6).getPktPassword());
        TestCase.assertEquals("8fffffff8", items.get(7).getPktPassword());
        TestCase.assertEquals("9ffffff9", items.get(8).getPktPassword());
    }

    @Test
    public void testExportLinks() throws Exception {


        StandardFileSystemManager manager = new StandardFileSystemManager();
        manager.init();
        manager.resolveFile("ram://root/file2.txt").createFile();
        FileObject fo = manager.resolveFile("ram://root/file2.txt");
        OutputStream os = fo.getContent().getOutputStream();

        Writer out = new OutputStreamWriter(os);

        Map<String, Object> root = new HashMap<>();
        root.put("links", items);

        FreemarkerRunner.runReport("links.sql.ftl", root, out);

        InputStream is = fo.getContent().getInputStream();
        String runString = Utils.inputStreamToString(is);

        InputStream isTest = new FileInputStream(Utils.getPath("links.sql.txt"));
        String testString = Utils.inputStreamToString(isTest);

        TestCase.assertEquals(testString, runString);

    }

    @Test
    public void testExportLinksOptions() throws Exception {

        StandardFileSystemManager manager = new StandardFileSystemManager();
        manager.init();
        manager.resolveFile("ram://root/file2.txt").createFile();
        FileObject fo = manager.resolveFile("ram://root/file2.txt");
        OutputStream os = fo.getContent().getOutputStream();

        Writer out = new OutputStreamWriter(os);

        Map<String, Object> root = new HashMap<>();
        root.put("links", items);

        FreemarkerRunner.runReport("linksoptions.sql.ftl", root, out);

        InputStream is = fo.getContent().getInputStream();
        String runString = Utils.inputStreamToString(is);

        InputStream isTest = new FileInputStream(Utils.getPath("linksoptions.sql.txt"));
        String testString = Utils.inputStreamToString(isTest);

        TestCase.assertEquals(testString, runString);
    }

}
