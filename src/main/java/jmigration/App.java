package jmigration;

import jmigration.impl.agent.AgentImpl;
import jmigration.impl.agent.ScenarioImpl;
import jmigration.impl.agent.SourceResolverImpl;
import jmigration.scenario.agent.Scenario;

/**
 * Main app
 */
public final class App {

    private App() {
    }

    public static void main(String[] args) {
        Scenario scenario = new ScenarioImpl();
        scenario.setAgent(new AgentImpl());
        scenario.setContextResolver(new SourceResolverImpl());
        scenario.process();
    }
}
