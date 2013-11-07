package jmigration.impl.data;

import jmigration.scenario.data.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kirill Temnenkov (ktemnenkov@intervale.ru)
 */
public class ContextImpl implements Context {

    private List<String> binkContent;

    public List<String> getBinkContent() {
        if (binkContent == null) {
            binkContent = new ArrayList<>();
        }
        return binkContent;
    }

    public void setBinkContent(List<String> binkContent) {
        this.binkContent = binkContent;
    }

}
