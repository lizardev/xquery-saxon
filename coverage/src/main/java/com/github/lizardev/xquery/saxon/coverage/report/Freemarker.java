package com.github.lizardev.xquery.saxon.coverage.report;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;

import java.io.StringWriter;
import java.util.Map;

public final class Freemarker {
    private static final String TEMPLATE_PATH_PREFIX = "/";
    private static final Configuration CONFIGURATION = new Configuration();

    static {
        CONFIGURATION.setTemplateLoader(new ClassTemplateLoader(Freemarker.class, TEMPLATE_PATH_PREFIX));
        CONFIGURATION.setTemplateUpdateDelay(Integer.MAX_VALUE);
    }

    private Freemarker() {
    }

    public static String renderTemplate(String templatePath, Map<String, ?> rootMap) {
        try {
            StringWriter stringWriter = new StringWriter();
            CONFIGURATION.getTemplate(templatePath).process(rootMap, stringWriter);
            return stringWriter.toString();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}