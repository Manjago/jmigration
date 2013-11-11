package jmigration.impl.data.items;

import jmigration.common.ItemsBase;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class ItemsLink extends ItemsBase<Link> {
    @Override
    protected Link defensiveCopy(Link item) {
        return item == null ? null : new Link(item);
    }

    public void smooth(){
         for (Link link : getItems()){
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
