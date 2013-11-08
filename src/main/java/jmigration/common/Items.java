package jmigration.common;

import jmigration.common.Lambda;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public interface Items<E> {
    void addLine(E item);

    void forEach(Lambda<E, Void> cmd);

    boolean isEmpty();
}
