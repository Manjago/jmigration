package jmigration.impl.data;

import jmigration.common.Items;
import jmigration.common.Lambda;
import jmigration.common.Predicate;

import java.util.HashMap;
import java.util.Map;

import static jmigration.common.LameLib.checkNotNull;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class TargetData implements Items<Link> {
    private Map<String, Link> getLinks() {
        if (links == null) {
            links = new HashMap<>();
        }
        return links;
    }

    private Map<String, Link> links;

    @Override
    public void addLine(Link link) {
        checkNotNull(link);
        checkNotNull(link.getFtnAddress());

        getLinks().put(link.getFtnAddress(), new Link(link));
    }

    @Override
    public void forEach(Lambda<Link, Void> cmd) {
        if (cmd == null) {
            return;
        }
        for (Link link : getLinks().values()) {
            cmd.execute(new Link(link));
        }
    }

    @Override
    public void forEach(Predicate<Link> predicate, Lambda<Link, Void> cmd) {
        if (cmd == null) {
            return;
        }
        if (predicate == null) {
            forEach(cmd);
        } else {
            for (Link link : getLinks().values()) {
                if (predicate.passed(link)) {
                    cmd.execute(new Link(link));
                }
            }
        }

    }

    @Override
    public boolean isEmpty() {
        return links == null || links.isEmpty();
    }

    public void smooth() {
        for (Link link : getLinks().values()) {

            if (link.getPktPassword() == null) {
                link.setPktPassword(link.getPassword());
            }

            if (link.getPort() == null) {
                link.setPort(link.getHost() == null || link.getHost().isEmpty() ? "0" : "24554");
            }

            if (link.getStationName() == null) {
                link.setStationName(link.getFtnAddress());
            }

        }
    }
}
