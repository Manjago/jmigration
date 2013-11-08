package jmigration.impl.agent;

import jmigration.common.Lambda;
import jmigration.common.SplitReader;
import jmigration.impl.Const;
import jmigration.impl.data.ConfigType;
import jmigration.impl.data.SourceData;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public class SourceResolver {
    public SourceData resolveSource(String... args) throws FileNotFoundException {

        if (args == null || args.length != Const.CFG_COUNT) {
            throw new IllegalArgumentException();
        }

        for (String path : args) {
            File file = new File(path);
            if (!file.exists()) {
                throw new FileNotFoundException(path + " not found");
            }
        }

        SourceData result = new SourceData();

        loadConfig(result, ConfigType.BINK, args[0]);
        loadConfig(result, ConfigType.SQAFIX, args[1]);
        loadConfig(result, ConfigType.SQUISH, args[2]);

        return result;
    }

    void loadConfig(final SourceData sourceData, final ConfigType configType, String path) {
        new SplitReader(new Lambda<String, Void>() {
            @Override
            public Void execute(String arg) {

                sourceData.addLine(configType, arg);

                return null;
            }
        }, "cp866").doRead(path);
    }

}
