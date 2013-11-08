package jmigration.scenario.agent;

import jmigration.scenario.data.SourceData;

import java.io.FileNotFoundException;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public interface SourceResolver {
    SourceData resolveSource(String... args) throws FileNotFoundException;
}
