package jmigration.impl;

import jmigration.common.Lambda;
import jmigration.common.StringsBase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public final class Utils {
    private Utils() {
    }

    public static List<String> getStrings(StringsBase file) {
        final List<String> test = new ArrayList<>();
        file.forEach(new Lambda<String, Void>() {
            @Override
            public Void execute(String arg) {
                test.add(arg);
                return null;
            }
        });
        return test;
    }
}
