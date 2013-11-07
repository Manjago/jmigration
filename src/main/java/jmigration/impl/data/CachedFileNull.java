package jmigration.impl.data;

import jmigration.common.Lambda;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public class CachedFileNull implements CachedFile {
    @Override
    public void addLine(String line) {
    }

    @Override
    public void forEach(Lambda<String, Void> cmd) {
    }
}
