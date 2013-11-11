package jmigration.impl.agent;

import jmigration.common.Lambda;
import jmigration.common.Predicate;
import jmigration.impl.data.ConfigType;
import jmigration.impl.data.SourceData;
import jmigration.impl.data.TargetData;
import jmigration.impl.data.items.EchoArea;
import jmigration.impl.data.items.Link;

import java.util.HashMap;
import java.util.Map;

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

        // получим карту названий
        final Map<String, String> names = new HashMap<>();
        determineNames(sourceData, names);


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
                        if (s.length < 2) {
                            return null;
                        }

                        String id = s[1];
                        EchoArea echoArea = new EchoArea();
                        echoArea.setName(id);
                        echoArea.setDesc(names.containsKey(id) ? names.get(id) : id);

                        targetData.addArea(echoArea);

                        return null;
                    }
                }
        );

        targetData.smooth();
    }

    void determineNames(SourceData sourceData, final Map<String, String> names) {
        sourceData.forEach(ConfigType.SQAFIX, new Predicate<String>() {
            @Override
            public boolean passed(String item) {
                return item != null && item.startsWith("EchoArea ");
            }
        }, new DeternineNames(names)
        );
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

    private static class DeternineNames implements Lambda<String, Void> {
        private static final int MIN_TOKENS_COUNT = 3;
        private final Map<String, String> names;

        public DeternineNames(Map<String, String> names) {
            this.names = names;
        }

        @Override
        public Void execute(String arg) {

            String[] s = arg.split("\\s");
            if (s.length < MIN_TOKENS_COUNT) {
                return null;
            }

            String id = s[1];
            String name = id;

            if (s.length > MIN_TOKENS_COUNT) {

                String[] s2 = arg.split("\u0009");
                if (s2.length > 1) {
                    String pretender = s2[1];
                    if (pretender.startsWith("\"") && pretender.endsWith("\"")) {
                        name = pretender.substring(1, pretender.length() - 1);
                    }
                }

            }

            names.put(id, name);

            return null;
        }
    }
}
