package jmigration.impl.data;

import jmigration.common.Items;
import jmigration.common.Lambda;

import java.util.HashMap;
import java.util.Map;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class TargetData implements Items<Link>{
    private Map<String, Link> getLinks() {
        if (links == null){
            links = new HashMap<>();
        }
        return links;
    }

    private Map<String, Link> links;

    @Override
    public void addLine(Link link) {
        checkNotNull(link);
        checkNotNull(link.getFtnAddress());

        getLinks().put(link.getFtnAddress(), link);
    }

    @Override
    public void forEach(Lambda<Link, Void> cmd) {
        if (cmd == null){
            return;
        }
        for(Link link : getLinks().values()){
           cmd.execute(new Link(link));
        }
    }

    @Override
    public boolean isEmpty() {
        return links == null || links.isEmpty();
    }
}
