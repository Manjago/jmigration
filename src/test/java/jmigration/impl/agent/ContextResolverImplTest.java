package jmigration.impl.agent;

import jmigration.impl.data.ContextImpl;
import junit.framework.Assert;
import org.junit.Test;

import java.net.URL;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class ContextResolverImplTest {

    @Test
    public void testLoadFile() throws Exception {

        ContextResolverImpl resolver = new ContextResolverImpl();

        URL url = ContextResolverImplTest.class.getResource("binkd.cfg");


        final ContextImpl context = new ContextImpl();
        resolver.loadBinkConfig(context, url.getPath());

        Assert.assertNotNull(context.getBinkContent());
        Assert.assertTrue(context.getBinkContent().size() > 0);

    }

}
