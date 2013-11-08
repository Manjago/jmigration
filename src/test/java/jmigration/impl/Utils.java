package jmigration.impl;

import jmigration.common.Items;
import jmigration.common.Lambda;

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

}
