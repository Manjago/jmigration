package jmigration.impl.agent;

import jmigration.common.Lambda;
import jmigration.impl.data.ConfigType;
import jmigration.impl.data.SourceData;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static jmigration.impl.Utils.getPath;

/**
 * @author Kirill Temnenkov (kirill@temnenkov.com)
 */
public class SourceResolverTest {

    private static List<String> getStrings(ConfigType configType, SourceData file) {
        final List<String> test = new ArrayList<>();
        file.forEach(configType, new Lambda<String, Void>() {
            @Override
            public Void execute(String arg) {
                test.add(arg);
                return null;
            }
        });
        return test;
    }

    @Test
    public void testLoadFile() throws Exception {

        SourceResolver resolver = new SourceResolver();


        final SourceData context = new SourceData();
        resolver.loadConfig(context, ConfigType.BINK, getPath("binkd.cfg"));

        final List<String> test = getStrings(ConfigType.BINK, context);

        TestCase.assertEquals(399, test.size());
        TestCase.assertEquals("#defnode -nr *", test.get(398));
    }

    @Test(expected = FileNotFoundException.class)
    public void testResolveBadArgs3() throws Exception {
        SourceResolver a = new SourceResolver();
        a.resolveSource("0", "1", "2", "3", "2:5020/1042");
    }

    @Test
    public void testResolveGood() throws Exception {
        SourceResolver a = new SourceResolver();
        SourceData data = a.resolveSource(getPath("binkd.cfg"), getPath("SQAFIX.cfg"), getPath("SQUISH.cfg"), getPath("dmtareas.ini"), "2:5020/1042");
        TestCase.assertNotNull(data);
        TestCase.assertFalse(data.isEmpty(ConfigType.BINK));
        TestCase.assertFalse(data.isEmpty(ConfigType.SQAFIX));
        TestCase.assertFalse(data.isEmpty(ConfigType.SQUISH));
        TestCase.assertFalse(data.isEmpty(ConfigType.DMTIC));
        TestCase.assertTrue(data.isEmpty(null));
    }

}
