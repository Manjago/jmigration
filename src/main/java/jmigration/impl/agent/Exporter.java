package jmigration.impl.agent;

import freemarker.template.TemplateException;
import jmigration.common.FreemarkerRunner;
import jmigration.common.Lambda;
import jmigration.impl.data.TargetData;
import jmigration.impl.data.items.EchoArea;
import jmigration.impl.data.items.Link;
import jmigration.impl.data.items.Subscr;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class Exporter {
    public void export(TargetData targetData, OutputStream os) throws IOException, TemplateException {

        final List<Link> links = new ArrayList<>();
        targetData.asLinks().forEach(new Lambda<Link, Void>() {
            @Override
            public Void execute(Link arg) {
                links.add(arg);
                return null;
            }
        });

        final List<Subscr> subscrs = new ArrayList<>();
        targetData.asSubscr().forEach(new Lambda<Subscr, Void>() {
            @Override
            public Void execute(Subscr arg) {
                subscrs.add(arg);
                return null;
            }
        });

        final List<EchoArea> areas = new ArrayList<>();
        targetData.asAreas().forEach(new Lambda<EchoArea, Void>() {
            @Override
            public Void execute(EchoArea arg) {
                areas.add(arg);
                return null;
            }
        });

        final List<EchoArea> fileareas = new ArrayList<>();
        targetData.asFileAreas().forEach(new Lambda<EchoArea, Void>() {
            @Override
            public Void execute(EchoArea arg) {
                fileareas.add(arg);
                return null;
            }
        });

        final List<Subscr> filesubscrs = new ArrayList<>();
        targetData.asFilesubscr().forEach(new Lambda<Subscr, Void>() {
            @Override
            public Void execute(Subscr arg) {
                filesubscrs.add(arg);
                return null;
            }
        });

        Map<String, Object> root = new HashMap<>();
        root.put("links", links);
        root.put("mainuplink", targetData.getMainUplink());
        root.put("subscr", subscrs);
        root.put("areas", areas);
        root.put("fileareas", fileareas);
        root.put("filesubscr", filesubscrs);

        Writer out = new OutputStreamWriter(os, Charset.forName("UTF8"));
        FreemarkerRunner.runReport("init.sql.ftl", root, out);
    }
}
