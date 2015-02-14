package com.github.lizardev.xquery.saxon.coverage.report;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import static org.apache.commons.io.FileUtils.copyFileToDirectory;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class StaticResourceTransferor {

    private static final String BASE_PATH = "com/github/lizardev/xquery/saxon/coverage/report";

    public void copyResources(File baseDir) {
        copyResourceFromWebjar("META-INF/resources/webjars/jquery/2.1.3/jquery.min.js", new File(baseDir + "/js/jquery.min.js"));
        copyResourceFromWebjar("META-INF/resources/webjars/tablesorter/2.17.8/js/jquery.tablesorter.min.js", new File(baseDir + "/js/jquery.tablesorter.min.js"));
        copyResourceFromWebjar("META-INF/resources/webjars/tablesorter/2.17.8/css/theme.blue.css", new File(baseDir + "/css/theme.blue.css"));
        copyResourceFromWebjar("META-INF/resources/webjars/jQuery.FilterTable/1.5/jquery.filtertable.min.js", new File(baseDir + "/js/jquery.filtertable.min.js"));
        copyResource(BASE_PATH + "/css/module-report.css", new File(baseDir + "/css"));
        copyResource(BASE_PATH + "/css/module-report-index.css", new File(baseDir + "/css"));
    }

    private void copyResourceFromWebjar(String resourcePath, File destFile) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream stream = classLoader.getResourceAsStream(resourcePath);
        try {
            copyInputStreamToFile(stream, destFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void copyResource(String resourcePath, File destDir) {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            copyFileToDirectory(new File(classLoader.getResource(resourcePath).toURI()), destDir);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
