package jmigration.impl.agent;

import jmigration.common.Lambda;
import jmigration.common.SplitReader;
import jmigration.impl.data.ContextImpl;
import jmigration.scenario.agent.ContextResolver;
import jmigration.scenario.data.Context;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public class ContextResolverImpl implements ContextResolver {
    @Override
    public ContextImpl resolveContext(String args) {
        return null;
    }

    public void loadBinkConfig(final ContextImpl context, String path){
        new SplitReader(new Lambda<String, Void>() {
            @Override
            public Void execute(String arg) {

                context.getBinkContent().add(arg);

                return null;
            }
        }, "cp866").doRead(path);
    }

}
