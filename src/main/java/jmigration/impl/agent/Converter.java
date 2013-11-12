package jmigration.impl.agent;

import jmigration.common.Lambda;
import jmigration.common.Predicate;
import jmigration.impl.data.ConfigType;
import jmigration.impl.data.SourceData;
import jmigration.impl.data.TargetData;
import jmigration.impl.data.items.EchoArea;
import jmigration.impl.data.items.Link;
import jmigration.impl.data.items.Subscr;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static jmigration.common.LameLib.checkNotNull;

/**
 * @author Kirill Temnenkov (kirill@temnenkov.com)
 */
public class Converter {

    public void convert(SourceData sourceData, final TargetData targetData) {
        checkNotNull(sourceData);
        checkNotNull(targetData);

        targetData.setMainUplink(sourceData.getMainUplink());

        final Set<String> activeLinks = new HashSet<>();

        // какие у нас есть живые (то есть из бинк) линки
        sourceData.forEach(ConfigType.BINK, new Predicate<String>() {
            @Override
            public boolean passed(String item) {
                return item != null && item.startsWith("node ");
            }
        }, new ParseBinkString(targetData, activeLinks)
        );

        // получим карту названий
        final Map<String, String> names = new HashMap<>();
        determineNames(sourceData, names);

        // арии
        sourceData.forEach(ConfigType.SQUISH, new Predicate<String>() {
            @Override
            public boolean passed(String item) {
                return item != null && item.startsWith("EchoArea ");
            }
        }, new ReadAreas(names, targetData, activeLinks)
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
        private final Set<String> activeLinks;

        public ParseBinkString(TargetData targetData, Set<String> activeLinks) {
            this.targetData = targetData;
            this.activeLinks = activeLinks;
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
                activeLinks.add(ftnAddress);
            }

            return null;
        }
    }

    static class DeternineNames implements Lambda<String, Void> {
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
                        name = pretender.substring(1, pretender.length() - 1).replace("'","''");
                    }
                }

            }

            names.put(id, name);

            return null;
        }
    }

    private static class ReadAreas implements Lambda<String, Void> {
        private static final int MIN_TOKEN_COUNT = 3;
        private final Map<String, String> names;
        private final TargetData targetData;
        private final Set<String> activeLinks;

        public ReadAreas(Map<String, String> names, TargetData targetData, Set<String> activeLinks) {
            this.names = names;
            this.targetData = targetData;
            this.activeLinks = activeLinks;
        }

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

            // подписки

            if (s.length > MIN_TOKEN_COUNT) {
                for (int i = MIN_TOKEN_COUNT; i < s.length; ++i) {
                    if (!s[i].startsWith("-")) {
                        String node = s[i];
                        if (activeLinks.contains(node)) {
                            Subscr subscr = new Subscr();
                            subscr.setArea(id);
                            subscr.setNode(node);
                            targetData.addSubscr(subscr);
                        }
                    }
                }
            }

            return null;
        }
    }
}
