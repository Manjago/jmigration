package jmigration.impl.agent;

import jmigration.impl.Utils;
import jmigration.impl.data.ConfigType;
import jmigration.impl.data.SourceData;
import jmigration.impl.data.TargetData;
import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static jmigration.impl.Utils.getPath;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class ConverterTest {
    @Ignore @Test
    public void testConvert() throws Exception {
        SourceResolver a = new SourceResolver();
        SourceData data = a.resolveSource(getPath("binkd.cfg.simple1"), getPath("SQAFIX.cfg"), getPath("SQUISH.cfg"));

        Converter converter = new Converter();
        TargetData targetData = new TargetData();
        converter.convert(data, targetData);

        Assert.assertEquals(9, Utils.getItems(targetData).size());
    }
}
