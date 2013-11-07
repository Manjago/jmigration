package jmigration.impl.agent;

import jmigration.common.Lambda;
import jmigration.common.SplitReader;
import jmigration.impl.data.ConfigType;
import jmigration.impl.data.SourceDataImpl;
import jmigration.scenario.agent.SourceResolver;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public class SourceResolverImpl implements SourceResolver {
    @Override
    public SourceDataImpl resolveSource(String args) {
        return null;
    }

    void loadBinkConfig(final SourceDataImpl sourceData, final ConfigType configType, String path) {
        new SplitReader(new Lambda<String, Void>() {
            @Override
            public Void execute(String arg) {

                sourceData.addLine(configType, arg);

                return null;
            }
        }, "cp866").doRead(path);
    }

}
