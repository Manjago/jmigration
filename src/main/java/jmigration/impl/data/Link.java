package jmigration.impl.data;

/**
 * Link
 *
 * @author Manjago (kirill@temnenkov.com)
 */
public class Link {
    private String stationName;
    private String ftnAddress;
    private String pktPassword;
    private String host;
    private String port;
    private String password;

    public Link() {
    }

    public Link(String stationName, String ftnAddress, String pktPassword, String host, String port, String password) {
        this.stationName = stationName;
        this.ftnAddress = ftnAddress;
        this.pktPassword = pktPassword;
        this.host = host;
        this.port = port;
        this.password = password;
    }

    public Link(Link link) {
        this.stationName = link.getStationName();
        this.ftnAddress = link.getFtnAddress();
        this.pktPassword = link.getPktPassword();
        this.host = link.getHost();
        this.port = link.getPort();
        this.password = link.getPassword();
    }

    public String getFtnAddress() {
        return ftnAddress;
    }

    public void setFtnAddress(String ftnAddress) {
        this.ftnAddress = ftnAddress;
    }

    public String getPktPassword() {
        return pktPassword;
    }

    public void setPktPassword(String pktPassword) {
        this.pktPassword = pktPassword;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStationName() {

        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Link{");
        sb.append("stationName='").append(stationName).append('\'');
        sb.append(", ftnAddress='").append(ftnAddress).append('\'');
        sb.append(", pktPassword='").append(pktPassword).append('\'');
        sb.append(", host='").append(host).append('\'');
        sb.append(", port='").append(port).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
