package jmigration.impl.data.items;

import java.util.Locale;

/**
 * @author Kirill Temnenkov (kirill@temnenkov.com)
 */
public class Subscr {
    private String node;
    private String area;

    public Subscr() {
    }

    public Subscr(Subscr s) {
        this.node = s.getNode();
        this.area = s.getArea();
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area != null ? area.toUpperCase(Locale.ENGLISH) : null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Subscr{");
        sb.append("node='").append(node).append('\'');
        sb.append(", area='").append(area).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
