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

    private App() {
    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != Const.CFG_COUNT){
            System.out.println("need 3 files - binkd.cfg, SQAFIX.CFG and SQUISH.CFG");
        }

        SourceData sourceData = new SourceResolver().resolveSource(args[0], args[1], args[2]);
        LOGGER.debug("sourceData = {}", sourceData);
    }
}
