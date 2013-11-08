package jmigration.impl.agent;

import jmigration.common.Lambda;
import jmigration.common.SplitReader;
import jmigration.impl.data.ConfigType;
import jmigration.impl.data.SourceDataImpl;
import jmigration.scenario.agent.SourceResolver;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public class SourceResolverImpl implements SourceResolver {
    @Override
    public SourceDataImpl resolveSource(String... args) throws FileNotFoundException {

        if (args == null || args.length != 3){
            throw new IllegalArgumentException();
        }

        for (String path : args){
            File file = new File(path);
            if (!file.exists()){
                throw new FileNotFoundException(path + " not found");
            }
        }

        SourceDataImpl result = new SourceDataImpl();

        loadConfig(result, ConfigType.BINK, args[0]);
        loadConfig(result, ConfigType.SQAFIX, args[1]);
        loadConfig(result, ConfigType.SQUISH, args[2]);

        return result;
    }

    void loadConfig(final SourceDataImpl sourceData, final ConfigType configType, String path) {
        new SplitReader(new Lambda<String, Void>() {
            @Override
            public Void execute(String arg) {

                sourceData.addLine(configType, arg);

                return null;
            }
        }, "cp866").doRead(path);
    }

}
