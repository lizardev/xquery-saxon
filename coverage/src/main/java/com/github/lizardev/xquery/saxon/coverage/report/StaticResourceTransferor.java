package com.github.lizardev.xquery.saxon.coverage.report;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class StaticResourceTransferor {

    private static final String BASE_PATH = "com/github/lizardev/xquery/saxon/coverage/report";
    private static final String WEB_JARS_PATH = "META-INF/resources/webjars";

    public void copyResources(File baseDir) {
        copyResource(WEB_JARS_PATH + "/jquery/2.1.3/jquery.min.js", new File(baseDir + "/js/jquery.min.js"));
        copyResource(WEB_JARS_PATH + "/tablesorter/2.17.8/js/jquery.tablesorter.min.js", new File(baseDir + "/js/jquery.tablesorter.min.js"));
        copyResource(WEB_JARS_PATH + "/tablesorter/2.17.8/css/theme.blue.css", new File(baseDir + "/css/theme.blue.css"));
        copyResource(WEB_JARS_PATH + "/jQuery.FilterTable/1.5/jquery.filtertable.min.js", new File(baseDir + "/js/jquery.filtertable.min.js"));
        copyResource(BASE_PATH + "/css/module-report.css", new File(baseDir + "/css/module-report.css"));
        copyResource(BASE_PATH + "/css/module-report-index.css", new File(baseDir + "/css/module-report-index.css"));
    }

    private void copyResource(String resourcePath, File destFile) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream stream = classLoader.getResourceAsStream(resourcePath);
        try {
            copyInputStreamToFile(stream, destFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
