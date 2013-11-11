package jmigration.impl.agent;

import jmigration.common.Lambda;
import jmigration.common.Predicate;
import jmigration.impl.data.ConfigType;
import jmigration.impl.data.SourceData;
import jmigration.impl.data.TargetData;
import jmigration.impl.data.items.EchoArea;
import jmigration.impl.data.items.Link;

import static jmigration.common.LameLib.checkNotNull;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class Converter {

    public void convert(SourceData sourceData, final TargetData targetData) {
        checkNotNull(sourceData);
        checkNotNull(targetData);

        // какие у нас есть живые (то есть из бинк) линки
        sourceData.forEach(ConfigType.BINK, new Predicate<String>() {
            @Override
            public boolean passed(String item) {
                return item != null && item.startsWith("node ");
            }
        }, new ParseBinkString(targetData)
        );

        // пока что просто берем айдишники - и даже без названий
        sourceData.forEach(ConfigType.SQUISH, new Predicate<String>() {
                    @Override
                    public boolean passed(String item) {
                        return item != null && item.startsWith("EchoArea ");
                    }
                }, new Lambda<String, Void>() {
                    @Override
                    public Void execute(String arg) {
                        String[] s = arg.split("\\s");
                        if (s.length < 2){
                            return null;
                        }

                        String id = s[1];
                        EchoArea echoArea = new EchoArea();
                        echoArea.setName(id);

                        targetData.addArea(echoArea);

                        return null;
                    }
                }
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
                targetData.addLink(link);
            }

            return null;
        }
    }
}
