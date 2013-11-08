package jmigration.common;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public class Strings extends ItemsBase<String> {
    @Override
    protected String defensiveCopy(String item) {
        return item;
    }
}
