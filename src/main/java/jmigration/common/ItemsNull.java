package jmigration.common;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public class ItemsNull<E> implements Items<E> {
    @Override
    public void addLine(E line) {
    }

    @Override
    public void forEach(Lambda<E, Void> cmd) {
    }

    @Override
    public void forEach(Predicate<E> predicate, Lambda<E, Void> cmd) {
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

}
