package jmigration.impl.data.items;

import java.util.Locale;

/**
 * @author Kirill Temnenkov (kirill@temnenkov.com)
 */
public class EchoArea {
    private String name;
    private String desc;

    public EchoArea() {
    }

    public EchoArea(EchoArea a) {
        this.name = a.getName();
        this.desc = a.getDesc();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name != null ? name.toUpperCase(Locale.ENGLISH) : null;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EchoArea{");
        sb.append("name='").append(name).append('\'');
        sb.append(", desc='").append(desc).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
