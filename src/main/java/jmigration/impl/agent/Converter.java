package jmigration.impl.agent;

import jmigration.common.Lambda;
import jmigration.common.Predicate;
import jmigration.impl.data.ConfigType;
import jmigration.impl.data.Link;
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
                }, new Lambda<String, Void>() {
                    @Override
                    public Void execute(String arg) {

                        String[] s = arg.split(" ");
                        if (s.length < 4) {
                            return null;
                        }

                        String ftnAddress = s[1];
                        String host = s[2].equals("-") ? "" : s[2];
                        String pwd = null;
                        for (int i = 3; i < s.length; ++i) {
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
                            targetData.addLine(link);
                        }

                        return null;
                    }
                }
        );

        targetData.smooth();
    }
}
