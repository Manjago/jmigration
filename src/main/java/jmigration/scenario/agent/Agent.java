package jmigration.scenario.agent;

import jmigration.scenario.data.FidoData;
import jmigration.scenario.data.SourceData;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public interface Agent {
    void load(SourceData sourceData, FidoData fidoData);

    void export(SourceData sourceData, FidoData fidoData);
}
