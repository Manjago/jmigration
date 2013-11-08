package jmigration.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public class StringsBase implements Strings {
    private List<String> lines;

    private List<String> getLines() {
        if (lines == null) {
            lines = new ArrayList<>();
        }
        return lines;
    }

    public void addLine(String line) {
        getLines().add(line);
    }

    public void forEach(Lambda<String, Void> cmd) {
        if (cmd == null) {
            return;
        }

        for (String line : getLines()) {
            cmd.execute(line);
        }
    }

    @Override
    public boolean isEmpty() {
        return lines == null || lines.size() == 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StringsBase{");
        sb.append("lines count=").append(lines != null ? lines.size() : 0);
        sb.append('}');
        return sb.toString();
    }
}
