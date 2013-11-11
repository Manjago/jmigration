package jmigration.impl.data.items;

import jmigration.common.ItemsBase;

/**
 * @author Kirill Temnenkov (kirill@temnenkov.com)
 */
public class ItemsEchoArea extends ItemsBase<EchoArea> {
    @Override
    protected EchoArea defensiveCopy(EchoArea item) {
        return item == null ? null : new EchoArea(item);
    }
}
