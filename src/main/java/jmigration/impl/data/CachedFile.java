package jmigration.impl.data;

import jmigration.common.Lambda;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public interface CachedFile {
    void addLine(String line);

    void forEach(Lambda<String, Void> cmd);
}
