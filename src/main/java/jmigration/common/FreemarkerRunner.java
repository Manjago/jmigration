package jmigration.common;

import freemarker.template.*;

import java.io.IOException;
import java.io.Writer;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public final class FreemarkerRunner {

    private static final int MAJOR = 2;
    private static final int MINOR = 3;
    private static final int MICRO = 20;

    private FreemarkerRunner() {
    }

    public static void runReport(String templateName, Object model, Writer out) throws IOException, TemplateException {
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(FreemarkerRunner.class, "/templates");

        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setIncompatibleImprovements(new Version(MAJOR, MINOR, MICRO));

        Template temp = cfg.getTemplate(templateName);
        temp.process(model, out);
    }
}
