package com.github.lizardev.xquery.saxon.coverage.report;

import java.io.File;
import java.util.List;

import static com.github.lizardev.xquery.saxon.coverage.util.FileUtils.deleteDir;
import static com.github.lizardev.xquery.saxon.coverage.util.FileUtils.write;
import static com.github.lizardev.xquery.saxon.coverage.util.UriUtils.uriToFilename;
import static com.google.common.collect.Lists.newArrayList;

public class FileReportPrinter implements ReportPrinter {
    private File baseDir = new File("xquery-saxon-coverage");
    private HtmlModuleReportGenerator reportGenerator = new HtmlModuleReportGenerator();
    private IndexHtmlReportGenerator indexReportGenerator = new IndexHtmlReportGenerator();

    @Override public void print(Report report) {
        deleteDir(baseDir);
        List<ModuleReportFile> moduleReportFiles = newArrayList();
        for (ModuleReport moduleReport : report.getModuleReports()) {
            File file = getHtmlModuleReportFile(moduleReport);
            moduleReportFiles.add(new ModuleReportFile(moduleReport.getModuleUri().toString(), file.getAbsolutePath()));
            write(file, reportGenerator.generate(moduleReport));
        }
        write(new File(baseDir, "index.html"), indexReportGenerator.generate(moduleReportFiles));
    }

    private File getHtmlModuleReportFile(ModuleReport moduleReport) {
        return new File(baseDir, uriToFilename(moduleReport.getModuleUri().getUri()) + ".html");
    }
}