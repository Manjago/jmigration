package jmigration.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public class ItemsBase<E> implements Items<E> {
    private List<E> lines;

    private List<E> getLines() {
        if (lines == null) {
            lines = new ArrayList<>();
        }
        return lines;
    }

    public void addLine(E line) {
        getLines().add(line);
    }

    public void forEach(Lambda<E, Void> cmd) {
        if (cmd == null) {
            return;
        }

        for (E line : getLines()) {
            cmd.execute(line);
        }
    }

    @Override
    public boolean isEmpty() {
        return lines == null || lines.size() == 0;
    }

}
