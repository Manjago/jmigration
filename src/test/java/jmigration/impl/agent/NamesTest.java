package jmigration.impl.agent;

import jmigration.impl.data.SourceData;
import jmigration.impl.data.TargetData;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static jmigration.impl.Utils.getPath;

/**
 * @author Kirill Temnenkov (kirill@temnenkov.com)
 */
public class NamesTest {
    @Test
    public void testNames() throws Exception {
        SourceResolver a = new SourceResolver();
        SourceData data = a.resolveSource(getPath("binkd.cfg"), getPath("SQAFIX.cfg.simple3"), getPath("SQUISH.cfg.simple2"), getPath("dmtareas.ini"), "2:5020/1042");

        Converter converter = new Converter();
        Map<String, String> names = new HashMap<>();
        converter.determineNames(data, names);

        TestCase.assertEquals(5, names.size());
        TestCase.assertEquals("XSU.HACKER", names.get("XSU.HACKER"));
        TestCase.assertEquals("Сиcтема ICQ и ее клоны", names.get("RU.INTERNET.ICQ"));
        TestCase.assertEquals("Hовые эхи бекбона", names.get("N5020.BACKBONE.INFO"));
        TestCase.assertEquals("RU.NETHACK", names.get("RU.NETHACK"));
        TestCase.assertEquals("RU.ADSL", names.get("RU.ADSL"));

    }
}
