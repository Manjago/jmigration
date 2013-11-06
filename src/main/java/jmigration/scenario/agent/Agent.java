package jmigration.scenario.agent;

import jmigration.scenario.data.Context;
import jmigration.scenario.data.FidoData;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public interface Agent {
    void load(Context context, FidoData fidoData);

    void export(Context context, FidoData fidoData);
}
