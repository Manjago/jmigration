package jmigration.impl.agent;

import jmigration.common.Lambda;
import jmigration.impl.data.ConfigType;
import jmigration.impl.data.SourceData;
import junit.framework.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

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

        Assert.assertEquals(399, test.size());
        Assert.assertEquals("#defnode -nr *", test.get(398));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testResolveBadArgs() throws Exception {
        SourceResolver a = new SourceResolver();
        a.resolveSource(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testResolveBadArgs2() throws Exception {
        SourceResolver a = new SourceResolver();
        a.resolveSource("0", "1");
    }

    @Test(expected = FileNotFoundException.class)
    public void testResolveBadArgs3() throws Exception {
        SourceResolver a = new SourceResolver();
        a.resolveSource("0", "1", "2");
    }

    private String getPath(String filename) {
        return SourceResolverTest.class.getResource(filename).getPath();
    }

    @Test
    public void testResolveGood() throws Exception {
        SourceResolver a = new SourceResolver();
        SourceData data = a.resolveSource(getPath("binkd.cfg"), getPath("SQAFIX.CFG"), getPath("SQUISH.CFG"));
        Assert.assertNotNull(data);
        Assert.assertFalse(data.isEmpty(ConfigType.BINK));
        Assert.assertFalse(data.isEmpty(ConfigType.SQAFIX));
        Assert.assertFalse(data.isEmpty(ConfigType.SQUISH));
        Assert.assertTrue(data.isEmpty(null));
    }

}
