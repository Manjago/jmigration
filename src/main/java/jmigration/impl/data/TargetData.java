package jmigration.impl.data;

import jmigration.common.Items;
import jmigration.impl.data.items.*;

/**
 * @author Kirill Temnenkov (kirill@temnenkov.com)
 */
public class TargetData {

    private ItemsLink links;
    private ItemsEchoArea areas;
    private ItemsSubscr subscr;
    private String mainUplink;

    public String getMainUplink() {
        return mainUplink;
    }

    public void setMainUplink(String mainUplink) {
        this.mainUplink = mainUplink;
    }

    public ItemsSubscr getSubscr() {
        if (subscr == null) {
            subscr = new ItemsSubscr();
        }
        return subscr;
    }

    public Items<Link> asLinks() {
        return getLinks();
    }

    public Items<EchoArea> asAreas() {
        return getAreas();
    }

    public Items<Subscr> asSubscr() {
        return getSubscr();
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

    public void addSubscr(Subscr subscr) {
        getSubscr().addItem(subscr);
    }

    public void smooth() {
        getLinks().smooth();
    }
}
