package jmigration.impl.agent;

import jmigration.common.Lambda;
import jmigration.impl.data.ConfigType;
import jmigration.impl.data.SourceDataImpl;
import junit.framework.Assert;
import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class ContextResolverImplTest {

    private static List<String> getStrings(ConfigType configType, SourceDataImpl file) {
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

        SourceResolverImpl resolver = new SourceResolverImpl();

        URL url = ContextResolverImplTest.class.getResource("binkd.cfg");


        final SourceDataImpl context = new SourceDataImpl();
        resolver.loadBinkConfig(context, ConfigType.BINK, url.getPath());

        final List<String> test = getStrings(ConfigType.BINK, context);

        Assert.assertEquals(338, test.size());
        Assert.assertEquals("#defnode -nr *", test.get(337));
    }

}
