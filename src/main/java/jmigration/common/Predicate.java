package jmigration.common;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public interface Predicate<E> {
    boolean passed(E item);
}
