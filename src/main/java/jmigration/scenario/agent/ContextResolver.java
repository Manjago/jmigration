package jmigration.scenario.agent;

import jmigration.scenario.data.Context;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public interface ContextResolver {
    Context resolveContext(String args);
}
