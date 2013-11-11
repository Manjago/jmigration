package jmigration.impl.data;

import jmigration.common.Items;
import jmigration.impl.data.items.EchoArea;
import jmigration.impl.data.items.ItemsEchoArea;
import jmigration.impl.data.items.ItemsLink;
import jmigration.impl.data.items.Link;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class TargetData {

    private ItemsLink links;
    private ItemsEchoArea areas;

    public Items<Link> asLinks() {
        return getLinks();
    }

    public Items<EchoArea> asAreas() {
        return getAreas();
    }

    private ItemsLink getLinks() {
        if (links == null) {
            links = new ItemsLink();
        }
        return links;
    }

    private ItemsEchoArea getAreas() {
        if (areas == null) {
            areas = new ItemsEchoArea();
        }
        return areas;
    }

    public void addLink(Link link) {
        getLinks().addItem(link);
    }

    public void addArea(EchoArea echoArea) {
        getAreas().addItem(echoArea);
    }

    public void smooth() {
        getLinks().smooth();
    }
}
