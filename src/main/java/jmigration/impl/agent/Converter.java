package jmigration.impl.agent;

import jmigration.common.Lambda;
import jmigration.common.Predicate;
import jmigration.impl.data.ConfigType;
import jmigration.impl.data.items.Link;
import jmigration.impl.data.SourceData;
import jmigration.impl.data.TargetData;

import static jmigration.common.LameLib.checkNotNull;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class Converter {

    public void convert(SourceData sourceData, final TargetData targetData) {
        checkNotNull(sourceData);
        checkNotNull(targetData);

        sourceData.forEach(ConfigType.BINK, new Predicate<String>() {
            @Override
            public boolean passed(String item) {
                return item != null && item.startsWith("node ");
            }
        }, new ParseBinkString(targetData)
        );

        targetData.smooth();
    }

    private static class ParseBinkString implements Lambda<String, Void> {
        private static final int MIN_ITEMS_COUNT = 3;
        private final TargetData targetData;

        public ParseBinkString(TargetData targetData) {
            this.targetData = targetData;
        }

        @Override
        public Void execute(String arg) {

            String[] s = arg.split(" ");
            if (!(s.length >= MIN_ITEMS_COUNT)) {
                return null;
            }

            String ftnAddress = s[1];
            String host = s[2].equals("-") ? "" : s[2];
            String pwd = null;
            for (int i = MIN_ITEMS_COUNT; i < s.length; ++i) {
                if (!s[i].contains("-")) {
                    pwd = s[i];
                    break;
                }
            }

            if (pwd != null) {
                Link link = new Link();
                link.setFtnAddress(ftnAddress);
                link.setHost(host);
                link.setPassword(pwd);
                targetData.addItem(link);
            }

            return null;
        }
    }
}
