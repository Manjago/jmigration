package jmigration.impl.data.items;

import jmigration.common.ItemsBase;

/**
 * @author Kirill Temnenkov (kirill@temnenkov.com)
 */
public class ItemsSubscr extends ItemsBase<Subscr> {
    @Override
    protected Subscr defensiveCopy(Subscr item) {
        return item == null ? null : new Subscr(item);
    }
}
