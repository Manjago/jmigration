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
public class ExportTest {

    private Map<String, Object> root;
    private Writer out;
    private FileObject fo;

    @Before
    public void setUp() throws Exception {
        SourceResolver a = new SourceResolver();
        SourceData data = a.resolveSource(getPath("binkd.cfg.simple1"), getPath("SQAFIX.cfg"), getPath("SQUISH.cfg"), getPath("dmtareas.ini"), "2:5020/1042");

        Converter converter = new Converter();
        TargetData targetData = new TargetData();
        converter.convert(data, targetData);

        List<Link> items = Utils.getItems(targetData.asLinks());
        TestCase.assertEquals(9, items.size());

        Collections.sort(items, new Comparator<Link>() {
            @Override
            public int compare(Link o1, Link o2) {
                return o1.getPassword().compareTo(o2.getPassword());
            }
        });

        StandardFileSystemManager manager = new StandardFileSystemManager();
        manager.init();
        manager.resolveFile("ram://root/file2.txt").createFile();
        fo = manager.resolveFile("ram://root/file2.txt");
        OutputStream os = fo.getContent().getOutputStream();

        out = new OutputStreamWriter(os);

        root = new HashMap<>();
        root.put("links", items);

        List<Subscr> subscrs = Utils.getItems(targetData.asSubscr());
        Collections.sort(subscrs, new Comparator<Subscr>() {
            @Override
            public int compare(Subscr o1, Subscr o2) {
                int t = o1.getArea().compareTo(o2.getArea());
                if (t != 0){
                    return t;
                }
                return o1.getNode().compareTo(o2.getNode());
            }
        });

        List<EchoArea> areas = Utils.getItems(targetData.asAreas());
        Collections.sort(areas, new Comparator<EchoArea>() {
            @Override
            public int compare(EchoArea o1, EchoArea o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        root.put("mainuplink", targetData.getMainUplink());
        root.put("subscr", subscrs.subList(0, 5));
        root.put("areas", areas.subList(0, 23));
    }

    @Test
    public void testExportLinks() throws Exception {

        FreemarkerRunner.runReport("links.sql.ftl", root, out);

        InputStream is = fo.getContent().getInputStream();
        String runString = Utils.inputStreamToString(is);

        InputStream isTest = new FileInputStream(Utils.getPath("links.sql.txt"));
        String testString = Utils.inputStreamToString(isTest);

        TestCase.assertEquals(testString, runString);

    }

    @Test
    public void testExportLinksOptions() throws Exception {

        FreemarkerRunner.runReport("linksoptions.sql.ftl", root, out);

        InputStream is = fo.getContent().getInputStream();
        String runString = Utils.inputStreamToString(is);

        InputStream isTest = new FileInputStream(Utils.getPath("linksoptions.sql.txt"));
        String testString = Utils.inputStreamToString(isTest);

        TestCase.assertEquals(testString, runString);
    }

    @Test
    public void testExportRouting() throws Exception {

        FreemarkerRunner.runReport("routing.sql.ftl", root, out);

        InputStream is = fo.getContent().getInputStream();
        String runString = Utils.inputStreamToString(is);

        InputStream isTest = new FileInputStream(Utils.getPath("routing.sql.txt"));
        String testString = Utils.inputStreamToString(isTest);

        TestCase.assertEquals(testString, runString);
    }

    @Test
    public void testExportSubscr() throws Exception {

        FreemarkerRunner.runReport("subscr.sql.ftl", root, out);

        InputStream is = fo.getContent().getInputStream();
        String runString = Utils.inputStreamToString(is);

        InputStream isTest = new FileInputStream(Utils.getPath("subscr.sql.txt"));
        String testString = Utils.inputStreamToString(isTest);

        TestCase.assertEquals(testString, runString);
    }

    @Test
    public void testExportAreas() throws Exception {

        FreemarkerRunner.runReport("areas.sql.ftl", root, out);

        InputStream is = fo.getContent().getInputStream();
        String runString = Utils.inputStreamToString(is);

        InputStream isTest = new FileInputStream(Utils.getPath("areas.sql.txt"));
        String testString = Utils.inputStreamToString(isTest);

        TestCase.assertEquals(testString, runString);
    }
}
