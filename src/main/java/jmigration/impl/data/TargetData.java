package jmigration.impl.data;

import jmigration.common.Items;
import jmigration.impl.data.items.ItemsLink;
import jmigration.impl.data.items.Link;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class TargetData {

    private ItemsLink links;

    public Items<Link> asLinks() {
        return getLinks();
    }

    private ItemsLink getLinks() {
        if (links == null) {
            links = new ItemsLink();
        }
        return links;
    }

    public void addItem(Link link) {
        getLinks().addItem(link);
    }

    public void smooth() {
        getLinks().smooth();
    }
}
