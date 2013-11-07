package jmigration.impl.agent;

import jmigration.common.Lambda;
import jmigration.impl.data.ContextImpl;
import junit.framework.Assert;
import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class ContextResolverImplTest {

    private static List<String> getStrings(ContextImpl file) {
        final List<String> test = new ArrayList<>();
        file.forEachBink(new Lambda<String, Void>() {
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

        ContextResolverImpl resolver = new ContextResolverImpl();

        URL url = ContextResolverImplTest.class.getResource("binkd.cfg");


        final ContextImpl context = new ContextImpl();
        resolver.loadBinkConfig(context, url.getPath());

        final List<String> test = getStrings(context);

        Assert.assertEquals(338, test.size());
        Assert.assertEquals("#defnode -nr *", test.get(337));
    }

}
