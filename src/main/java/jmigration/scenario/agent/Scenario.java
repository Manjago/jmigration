package jmigration.scenario.agent;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public interface Scenario {
    void setAgent(Agent agent);

    void setContextResolver(SourceResolver sourceResolver);

    void process();
}
