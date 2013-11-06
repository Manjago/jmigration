package jmigration;

import jmigration.impl.agent.AgentImpl;
import jmigration.impl.agent.ContextResolverImpl;
import jmigration.impl.agent.ScenarioImpl;
import jmigration.scenario.agent.Scenario;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Scenario scenario = new ScenarioImpl();
        scenario.setAgent(new AgentImpl());
        scenario.setContextResolver(new ContextResolverImpl());
        scenario.process();
    }
}
