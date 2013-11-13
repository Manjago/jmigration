package jmigration.impl.agent;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class NamesText {

    @Test
    public void testName() throws Exception {
        Map<String, String> names = new HashMap<>();
        Converter.DeternineNames d = new Converter.DeternineNames(names);

        d.execute("EchoArea N5020.BACKBONE.INFO           A\t\"Hовые эх'и бекбона\"\t");
        TestCase.assertTrue(names.containsKey("N5020.BACKBONE.INFO"));
        TestCase.assertTrue(names.get("N5020.BACKBONE.INFO").contains("''"));
    }
}
