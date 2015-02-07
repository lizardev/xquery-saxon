package com.github.lizardev.xquery.saxon.coverage.report;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.apache.commons.io.FileUtils.copyFileToDirectory;

public class StaticResourceTransferor {

    private static final String BASE_PATH = "com/github/lizardev/xquery/saxon/coverage/report";

    public void copyResources(File baseDir) {
        copyResource(BASE_PATH + "/js/jquery.filtertable.min.js", new File(baseDir + "/js"));
        copyResource(BASE_PATH + "/js/jquery.tablesorter.min.js", new File(baseDir + "/js"));
        copyResource(BASE_PATH + "/js/jquery-2.1.3.min.js", new File(baseDir + "/js"));
        copyResource(BASE_PATH + "/css/module-report.css", new File(baseDir + "/css"));
        copyResource(BASE_PATH + "/css/module-report-index.css", new File(baseDir + "/css"));
        copyResource(BASE_PATH + "/css/img/asc.gif", new File(baseDir + "/css/img/"));
        copyResource(BASE_PATH + "/css/img/bg.gif", new File(baseDir + "/css/img/"));
        copyResource(BASE_PATH + "/css/img/desc.gif", new File(baseDir + "/css/img/"));
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
