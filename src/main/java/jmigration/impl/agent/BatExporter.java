package jmigration.impl.agent;

import freemarker.template.TemplateException;
import freemarker.template.utility.StringUtil;
import jmigration.common.FreemarkerRunner;
import jmigration.common.Ftn4D;
import jmigration.common.Lambda;
import jmigration.impl.data.TargetData;
import jmigration.impl.data.items.FileInfo;
import jmigration.impl.data.items.Link;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class BatExporter {

    public void export(TargetData targetData, OutputStream os) throws IOException, TemplateException {

        List<FileInfo> files = intExport(targetData);

        Map<String, Object> root = new HashMap<>();
        root.put("files", files);

        Writer out = new OutputStreamWriter(os, Charset.forName("UTF8"));
        FreemarkerRunner.runReport("init.file.ftl", root, out);

    }

    public List<FileInfo> intExport(TargetData targetData){

        final List<FileInfo> result = new ArrayList<>();

        final List<Link> links = new ArrayList<>();
        targetData.asLinks().forEach(new Lambda<Link, Void>() {
            @Override
            public Void execute(Link arg) {
                links.add(arg);
                return null;
            }
        });

        for(Link link : links){
            Ftn4D ftn4D = Ftn4D.fromStr(link.getFtnAddress());
            result.add(new FileInfo(getFilename(ftn4D), link.getFtnAddress()));
        }

        return result;
    }

    static String getFilename(Ftn4D ftn4D) {
        String filename;
        if (ftn4D.isPoint()){
           filename = decToHex(ftn4D.getNet(), 4) + decToHex(ftn4D.getNode(), 4) + ".PNT\\" + decToHex(ftn4D.getPoint(), 8) + ".*";
        } else {
           filename = decToHex(ftn4D.getNet(), 4) + decToHex(ftn4D.getNode(), 4) + ".*";
        }
        return filename;
    }


    public static String decToHex(int dec, int len){
        return StringUtil.leftPad(Integer.toHexString(dec).toUpperCase(Locale.ENGLISH), len, "0");
    }

    public static String decToHex(String dec, int len){
        return decToHex(Integer.parseInt(dec), len);
    }

}
