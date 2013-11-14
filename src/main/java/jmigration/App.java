package jmigration;

import freemarker.template.TemplateException;
import jmigration.impl.Const;
import jmigration.impl.agent.BatExporter;
import jmigration.impl.agent.Converter;
import jmigration.impl.agent.SqlExporter;
import jmigration.impl.agent.SourceResolver;
import jmigration.impl.data.SourceData;
import jmigration.impl.data.TargetData;

import java.io.IOException;

/**
 * Main app
 */
public final class App {

    private static final int MAINUPLINK = 4;
    private static final int DMTIC = 3;
    private static final int SQUISH = 2;
    private static final int SQAFIX = 1;
    private static final int BINK = 0;

    private App() {
    }

    public static void main(String[] args) throws IOException, TemplateException {
        if (args.length != Const.CFG_COUNT) {
            System.out.println("need 4 files - binkd.cfg, SQAFIX.CFG, SQUISH.CFG, dmtareas.ini and mainuplink address");
            return;
        }


        SourceData sourceData = new SourceResolver().resolveSource(args[BINK], args[SQAFIX], args[SQUISH], args[DMTIC], args[MAINUPLINK]);
        TargetData targetData = new TargetData();
        new Converter().convert(sourceData, targetData);
        new SqlExporter().export(targetData, System.out);
        new BatExporter().export(targetData, System.out);
    }
}
