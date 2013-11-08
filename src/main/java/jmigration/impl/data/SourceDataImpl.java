package jmigration.impl.data;

import jmigration.common.Lambda;
import jmigration.common.Strings;
import jmigration.common.StringsBase;
import jmigration.common.StringsNull;
import jmigration.scenario.data.SourceData;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class SourceDataImpl implements SourceData {

    private Map<ConfigType, Strings> content;

    public void addLine(ConfigType configType, String line) {
        if (!getContent().containsKey(configType)) {
            getContent().put(configType, new StringsBase());
        }
        getContent(configType).addLine(line);
    }

    public void forEach(ConfigType configType, Lambda<String, Void> cmd) {
        getContent(configType).forEach(cmd);
    }

    private Map<ConfigType, Strings> getContent() {
        if (content == null) {
            content = new HashMap<>();
        }
        return content;
    }

    private Strings getContent(ConfigType configType) {
        if (configType == null || !getContent().containsKey(configType)) {
            return new StringsNull();
        }
        return getContent().get(configType);
    }

    public boolean isEmpty(ConfigType configType){
        return getContent(configType).isEmpty();
    }

}
