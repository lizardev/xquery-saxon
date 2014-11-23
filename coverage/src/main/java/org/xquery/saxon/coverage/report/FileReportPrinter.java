package org.xquery.saxon.coverage.report;

import java.io.File;

import static org.xquery.saxon.coverage.util.FileUtils.deleteDir;
import static org.xquery.saxon.coverage.util.FileUtils.write;
import static org.xquery.saxon.coverage.util.UriUtils.uriToFilename;

public class FileReportPrinter implements ReportPrinter {
    private File baseDir = new File("xquery-saxon-coverage");
    private HtmlModuleReportGenerator reportGenerator = new HtmlModuleReportGenerator();

    @Override public void print(Report report) {
        deleteDir(baseDir);
        for (ModuleReport moduleReport : report.getModuleReports()) {
            write(getHtmlModuleReportFile(moduleReport), reportGenerator.generate(moduleReport));
        }
    }

    private File getHtmlModuleReportFile(ModuleReport moduleReport) {
        return new File(baseDir, uriToFilename(moduleReport.getModuleUri().getUri()) + ".html");
    }
}