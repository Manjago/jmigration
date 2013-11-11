package jmigration;

import jmigration.impl.Const;
import jmigration.impl.agent.SourceResolver;
import jmigration.impl.data.SourceData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;

/**
 * Main app
 */
public final class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final int MAINUPLINK = 3;
    private static final int SQUISH = 2;
    private static final int SQAFIX = 1;
    private static final int BINK = 0;

    private App() {
    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != Const.CFG_COUNT) {
            System.out.println("need 3 files - binkd.cfg, SQAFIX.CFG and SQUISH.CFG and mainuplink address");
        }

        SourceData sourceData = new SourceResolver().resolveSource(args[BINK], args[SQAFIX], args[SQUISH], args[MAINUPLINK]);
        LOGGER.debug("sourceData = {}", sourceData);
    }
}
