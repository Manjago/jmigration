package jmigration.impl.data.items;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class FileInfo {
    private String name;
    private String ftn;

    public FileInfo(String name, String ftn) {
        this.name = name;
        this.ftn = ftn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFtn() {
        return ftn;
    }

    public void setFtn(String ftn) {
        this.ftn = ftn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FileInfo{");
        sb.append("name='").append(name).append('\'');
        sb.append(", ftn='").append(ftn).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
