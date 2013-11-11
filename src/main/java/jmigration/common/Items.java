package jmigration.common;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public interface Items<E> {
    void addItem(E item);

    void forEach(Lambda<E, Void> cmd);

    void forEach(Predicate<E> predicate, Lambda<E, Void> cmd);

    boolean isEmpty();
}
