package jmigration.impl.data;

import jmigration.common.Lambda;
import jmigration.scenario.data.Context;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class ContextImpl implements Context {

    private CachedFile binkContent;

    public void addLineBink(String line) {
        getBinkContent().addLine(line);
    }

    public void forEachBink(Lambda<String, Void> cmd) {
        getBinkContent().forEach(cmd);
    }

    private CachedFile getBinkContent() {
        if (binkContent == null) {
            binkContent = new CachedFile();
        }
        return binkContent;
    }


}
