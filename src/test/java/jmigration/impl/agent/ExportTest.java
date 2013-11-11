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
public class ExportTest {

    private List<Link> items;
    private Map<String, Object> root;
    private Writer out;
    private FileObject fo;

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

        StandardFileSystemManager manager = new StandardFileSystemManager();
        manager.init();
        manager.resolveFile("ram://root/file2.txt").createFile();
        fo = manager.resolveFile("ram://root/file2.txt");
        OutputStream os = fo.getContent().getOutputStream();

        out = new OutputStreamWriter(os);

        root = new HashMap<>();
        root.put("links", items);
        root.put("mainuplink", targetData.getMainUplink());

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
}
