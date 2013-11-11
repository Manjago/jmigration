package jmigration.impl.agent;

import freemarker.template.TemplateException;
import jmigration.common.FreemarkerRunner;
import jmigration.common.Lambda;
import jmigration.impl.data.TargetData;
import jmigration.impl.data.items.Link;

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


        Map<String, Object> root = new HashMap<>();
        root.put("links", links);
        root.put("mainuplink", targetData.getMainUplink());


        Writer out = new OutputStreamWriter(os, Charset.forName("UTF8"));
        FreemarkerRunner.runReport("init.sql.ftl", root, out);
    }
}
