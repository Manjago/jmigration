package jmigration.impl.data;

import jmigration.common.Items;
import jmigration.common.Lambda;
import jmigration.common.Predicate;
import jmigration.impl.data.items.ItemsLink;

import static jmigration.common.LameLib.checkNotNull;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class TargetData implements Items<Link> {

    private ItemsLink links;

    private ItemsLink getLinks() {
        if (links == null) {
            links = new ItemsLink();
        }
        return links;
    }

    @Override
    public void addItem(Link link) {
        getLinks().addItem(link);
    }

    @Override
    public void forEach(Lambda<Link, Void> cmd) {
        getLinks().forEach(cmd);
    }

    @Override
    public void forEach(Predicate<Link> predicate, Lambda<Link, Void> cmd) {
        getLinks().forEach(predicate, cmd);
    }

    @Override
    public boolean isEmpty() {
        return getLinks().isEmpty();
    }

    public void smooth() {
        getLinks().smooth();
    }
}
