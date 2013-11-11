package jmigration.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public abstract class ItemsBase<E> implements Items<E> {
    private List<E> items;

    protected List<E> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }

    public void addItem(E item) {
        getItems().add(item == null ? null : defensiveCopy(item));
    }

    public void forEach(Lambda<E, Void> cmd) {
        if (cmd == null) {
            return;
        }

        for (E line : getItems()) {
            cmd.execute(defensiveCopy(line));
        }
    }

    @Override
    public void forEach(Predicate<E> predicate, Lambda<E, Void> cmd) {
        if (cmd == null) {
            return;
        }

        if (predicate == null) {
            forEach(cmd);
        } else {
            for (E line : getItems()) {
                if (predicate.passed(line)) {
                    cmd.execute(defensiveCopy(line));
                }
            }
        }
    }

    protected abstract E defensiveCopy(E item);

    @Override
    public boolean isEmpty() {
        return items == null || items.size() == 0;
    }

}
