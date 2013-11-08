package jmigration.impl.data;

import jmigration.common.Items;
import jmigration.common.Lambda;
import jmigration.common.ItemsBase;
import jmigration.common.ItemsNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kirill Temnenkov (kirill@temnenkov.com)
 */
public class SourceData {

    private Map<ConfigType, Items<String>> content;

    public void addLine(ConfigType configType, String line) {
        if (!getContent().containsKey(configType)) {
            getContent().put(configType, new ItemsBase<String>());
        }
        getContent(configType).addLine(line);
    }

    public void forEach(ConfigType configType, Lambda<String, Void> cmd) {
        getContent(configType).forEach(cmd);
    }

    private Map<ConfigType, Items<String>> getContent() {
        if (content == null) {
            content = new HashMap<>();
        }
        return content;
    }

    private Items<String> getContent(ConfigType configType) {
        if (configType == null || !getContent().containsKey(configType)) {
            return new ItemsNull<>();
        }
        return getContent().get(configType);
    }

    public boolean isEmpty(ConfigType configType) {
        return getContent(configType).isEmpty();
    }

}
