package jmigration.common;

import jmigration.common.Lambda;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public interface Strings {
    void addLine(String line);

    void forEach(Lambda<String, Void> cmd);

    boolean isEmpty();
}
