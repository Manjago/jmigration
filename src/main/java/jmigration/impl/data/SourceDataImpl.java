package jmigration.impl.data;

import jmigration.common.Lambda;
import jmigration.scenario.data.SourceData;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class SourceDataImpl implements SourceData {

    private Map<ConfigType, CachedFile> content;

    public void addLine(ConfigType configType, String line) {
        if (!getContent().containsKey(configType)) {
            getContent().put(configType, new CachedFileImpl());
        }
        getContent(configType).addLine(line);
    }

    public void forEach(ConfigType configType, Lambda<String, Void> cmd) {
        getContent(configType).forEach(cmd);
    }

    private Map<ConfigType, CachedFile> getContent() {
        if (content == null) {
            content = new HashMap<>();
        }
        return content;
    }

    private CachedFile getContent(ConfigType configType) {
        if (configType == null || !getContent().containsKey(configType)) {
            return new CachedFileNull();
        }
        return getContent().get(configType);
    }

}
