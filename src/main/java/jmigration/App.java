package jmigration;

import freemarker.template.TemplateException;
import jmigration.impl.Const;
import jmigration.impl.agent.Converter;
import jmigration.impl.agent.Exporter;
import jmigration.impl.agent.SourceResolver;
import jmigration.impl.data.SourceData;
import jmigration.impl.data.TargetData;

import java.io.IOException;

/**
 * Main app
 */
public final class App {

    private static final int MAINUPLINK = 3;
    private static final int SQUISH = 2;
    private static final int SQAFIX = 1;
    private static final int BINK = 0;

    private App() {
    }

    public static void main(String[] args) throws IOException, TemplateException {
        if (args.length != Const.CFG_COUNT) {
            System.out.println("need 3 files - binkd.cfg, SQAFIX.CFG and SQUISH.CFG and mainuplink address");
        }


        SourceData sourceData = new SourceResolver().resolveSource(args[BINK], args[SQAFIX], args[SQUISH], args[MAINUPLINK]);
        TargetData targetData = new TargetData();
        new Converter().convert(sourceData, targetData);
        new Exporter().export(targetData, System.out);
    }
}
