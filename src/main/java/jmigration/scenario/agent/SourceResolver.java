package jmigration.scenario.agent;

import jmigration.scenario.data.SourceData;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public interface SourceResolver {
    SourceData resolveSource(String args);
}
