package jmigration.common;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class Ftn4D {
    private static final int PRIME = 31;
    private final String zone;
    private final String net;
    private final String node;
    private final String point;

    public boolean isPoint(){
        return !getPoint().equals("0");
    }

    public Ftn4D(String zone, String net, String node) {
        this.zone = zone;
        this.net = net;
        this.node = node;
        this.point = "0";
    }

    public Ftn4D(String zone, String net, String node, String point) {
        this.zone = zone;
        this.net = net;
        this.node = node;
        this.point = point;
    }

    public static Ftn4D fromStr(String s) {
        if (s == null || !(s.contains(":") || !s.contains("/"))) {
            throw new IllegalArgumentException();
        }

        String[] items = s.split(":|/|\\.");

        switch (items.length) {
            case 3:
                return new Ftn4D(items[0], items[1], items[2], "0");
            case 4:
                return new Ftn4D(items[0], items[1], items[2], items[3]);
            default:
                throw new IllegalArgumentException();
        }

    }

    private String sf(String s) {
        return s != null ? s : "0";
    }

    public String getPoint() {
        return sf(point);
    }

    public String getZone() {
        return sf(zone);
    }

    public String getNet() {
        return sf(net);
    }

    public String getNode() {
        return sf(node);
    }

    public String toFtnString() {
        StringBuilder sb = new StringBuilder();

        sb.append(getZone());
        sb.append(":");
        sb.append(getNet());
        sb.append("/");
        sb.append(getNode());
        sb.append(".");
        sb.append(getPoint());

        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ftn4D{");
        sb.append("zone='").append(zone).append('\'');
        sb.append(", net='").append(net).append('\'');
        sb.append(", node='").append(node).append('\'');
        sb.append(", point='").append(point).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Ftn4D ftn4D = (Ftn4D) o;

        if (net != null ? !net.equals(ftn4D.net) : ftn4D.net != null) {
            return false;
        }
        if (node != null ? !node.equals(ftn4D.node) : ftn4D.node != null) {
            return false;
        }
        if (point != null ? !point.equals(ftn4D.point) : ftn4D.point != null) {
            return false;
        }
        if (zone != null ? !zone.equals(ftn4D.zone) : ftn4D.zone != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = zone != null ? zone.hashCode() : 0;
        result = PRIME * result + (net != null ? net.hashCode() : 0);
        result = PRIME * result + (node != null ? node.hashCode() : 0);
        result = PRIME * result + (point != null ? point.hashCode() : 0);
        return result;
    }
}
