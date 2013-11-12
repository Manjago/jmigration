package jmigration.impl.data;

import jmigration.common.Items;
import jmigration.impl.data.items.*;

/**
 * @author Kirill Temnenkov (kirill@temnenkov.com)
 */
public class TargetData {

    private ItemsLink links;
    private ItemsEchoArea areas;
    private ItemsEchoArea fileareas;
    private ItemsSubscr subscr;
    private ItemsSubscr filesubscr;
    private String mainUplink;

    private ItemsSubscr getFilesubscr() {
        if (filesubscr == null) {
            filesubscr = new ItemsSubscr();
        }
        return filesubscr;
    }

    private ItemsEchoArea getFileareas() {
        if (fileareas == null) {
            fileareas = new ItemsEchoArea();
        }
        return fileareas;
    }

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

    public Items<EchoArea> asFileAreas() {
        return getFileareas();
    }

    public Items<Subscr> asSubscr() {
        return getSubscr();
    }

    public Items<Subscr> asFilesubscr() {
        return getFilesubscr();
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

    public void addFilearea(EchoArea echoArea) {
        getFileareas().addItem(echoArea);
    }

    public void addSubscr(Subscr subscr) {
        getSubscr().addItem(subscr);
    }

    public void addFilesubscr(Subscr subscr) {
        getFilesubscr().addItem(subscr);
    }

    public void smooth() {
        getLinks().smooth();
    }
}
