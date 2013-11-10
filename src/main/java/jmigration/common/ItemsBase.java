package jmigration.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public abstract class ItemsBase<E> implements Items<E> {
    private List<E> lines;

    private List<E> getLines() {
        if (lines == null) {
            lines = new ArrayList<>();
        }
        return lines;
    }

    public void addLine(E line) {
        getLines().add(line == null ? null : defensiveCopy(line));
    }

    public void forEach(Lambda<E, Void> cmd) {
        if (cmd == null) {
            return;
        }

        for (E line : getLines()) {
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
            for (E line : getLines()) {
                if (predicate.passed(line)) {
                    cmd.execute(defensiveCopy(line));
                }
            }
        }
    }

    protected abstract E defensiveCopy(E item);

    @Override
    public boolean isEmpty() {
        return lines == null || lines.size() == 0;
    }

}
