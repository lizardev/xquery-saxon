package com.github.lizardev.xquery.saxon.coverage.report;

import java.io.File;
import java.util.List;

import static com.github.lizardev.xquery.saxon.coverage.util.FileUtils.deleteDir;
import static com.github.lizardev.xquery.saxon.coverage.util.FileUtils.write;
import static com.github.lizardev.xquery.saxon.coverage.util.UriUtils.uriToFilename;
import static com.google.common.collect.Lists.newArrayList;

public class FileReportPrinter implements ReportPrinter {
    private File baseDir = new File("xquery-saxon-coverage");
    private HtmlModuleReportGenerator moduleReportGenerator = new HtmlModuleReportGenerator();
    private HtmlModuleReportIndexGenerator moduleReportIndexGenerator = new HtmlModuleReportIndexGenerator();

    @Override public void print(Report report) {
        deleteDir(baseDir);
        List<ModuleReportReference> moduleReportReferences = newArrayList();
        for (ModuleReport moduleReport : report.getModuleReports()) {
            File file = getHtmlModuleReportFile(moduleReport);
            moduleReportReferences.add(new ModuleReportReference(
                    moduleReport.getModuleUri().toString(), file.getName(), moduleReport.getLineCoverage()));
            write(file, moduleReportGenerator.generate(moduleReport));
        }
        write(new File(baseDir, "index.html"), moduleReportIndexGenerator.generate(moduleReportReferences));
    }

    private File getHtmlModuleReportFile(ModuleReport moduleReport) {
        return new File(baseDir, uriToFilename(moduleReport.getModuleUri().getUri()) + ".html");
    }
}