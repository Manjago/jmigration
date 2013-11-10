package jmigration.impl;

import jmigration.common.Items;
import jmigration.common.Lambda;
import org.apache.commons.io.IOUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public final class Utils {
    private Utils() {
    }

    public static <E> List<E> getItems(Items<E> file) {
        final List<E> test = new ArrayList<>();
        file.forEach(new Lambda<E, Void>() {
            @Override
            public Void execute(E arg) {
                test.add(arg);
                return null;
            }
        });
        return test;
    }


    public static String getPath(String filename) {
        return Utils.class.getResource(filename).getPath();
    }

    public static String inputStreamToString(InputStream is) throws IOException {
        StringWriter writer = new StringWriter();
        IOUtil.copy(is, writer, "UTF-8");
        return writer.toString();
    }
}
