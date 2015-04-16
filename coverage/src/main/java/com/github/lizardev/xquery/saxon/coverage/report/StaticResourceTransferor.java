package com.github.lizardev.xquery.saxon.coverage.report;

import java.io.File;

import static com.github.lizardev.xquery.saxon.coverage.util.FileUtils.copyResourceToFile;

public class StaticResourceTransferor {

    private static final String BASE_REPORT_PATH = "com/github/lizardev/xquery/saxon/coverage/report";

    public void copyResources(File destinationDir) {
        copyResourceToFile(BASE_REPORT_PATH + "/jquery/2.1.3/jquery.min.js", new File(destinationDir + "/js/jquery.min.js"));
        copyResourceToFile(BASE_REPORT_PATH + "/tablesorter/2.17.8/js/jquery.tablesorter.min.js", new File(destinationDir + "/js/jquery.tablesorter.min.js"));
        copyResourceToFile(BASE_REPORT_PATH + "/tablesorter/2.17.8/css/theme.blue.css", new File(destinationDir + "/css/theme.blue.css"));
        copyResourceToFile(BASE_REPORT_PATH + "/jQuery.FilterTable/1.5/jquery.filtertable.min.js", new File(destinationDir + "/js/jquery.filtertable.min.js"));
        copyResourceToFile(BASE_REPORT_PATH + "/css/module-report.css", new File(destinationDir + "/css/module-report.css"));
        copyResourceToFile(BASE_REPORT_PATH + "/css/module-report-index.css", new File(destinationDir + "/css/module-report-index.css"));
    }
}
