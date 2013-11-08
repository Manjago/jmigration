package jmigration.common;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public class StringsNull implements Strings {
    @Override
    public void addLine(String line) {
    }

    @Override
    public void forEach(Lambda<String, Void> cmd) {
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

}
