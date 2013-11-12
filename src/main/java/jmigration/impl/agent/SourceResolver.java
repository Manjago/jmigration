package jmigration.impl.agent;

import jmigration.common.Lambda;
import jmigration.common.SplitReader;
import jmigration.impl.data.ConfigType;
import jmigration.impl.data.SourceData;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public class SourceResolver {
    public SourceData resolveSource(String bink, String sqafix, String squish, String dmtic, String mainUplink) throws FileNotFoundException {

        testPath(bink);
        testPath(sqafix);
        testPath(squish);
        testPath(dmtic);

        SourceData result = new SourceData();

        loadConfig(result, ConfigType.BINK, bink);
        loadConfig(result, ConfigType.SQAFIX, sqafix);
        loadConfig(result, ConfigType.SQUISH, squish);
        loadConfig(result, ConfigType.DMTIC, dmtic);
        result.setMainUplink(mainUplink);

        return result;
    }

    private void testPath(String bink) throws FileNotFoundException {
        if (!new File(bink).exists()) {
            throw new FileNotFoundException(bink + " not found");
        }
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
