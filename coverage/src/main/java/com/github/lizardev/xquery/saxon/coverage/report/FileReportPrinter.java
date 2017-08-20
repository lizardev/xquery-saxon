package com.github.lizardev.xquery.saxon.coverage.report;

import java.io.File;
import java.util.List;

import static com.github.lizardev.xquery.saxon.coverage.util.FileUtils.deleteDir;
import static com.github.lizardev.xquery.saxon.coverage.util.FileUtils.write;
import static com.github.lizardev.xquery.saxon.coverage.util.UriUtils.uriToFilename;
import static com.google.common.collect.Lists.newArrayList;

public class FileReportPrinter implements ReportPrinter {

    public static final String REPORT_INDEX = "index.html";

    private final File reportDir;
    private HtmlModuleReportGenerator moduleReportGenerator = new HtmlModuleReportGenerator();
    private HtmlModuleReportIndexGenerator moduleReportIndexGenerator = new HtmlModuleReportIndexGenerator();
    private StaticResourceTransferor resourceTransferor = new StaticResourceTransferor();

    public FileReportPrinter(File reportDir) {
        this.reportDir = reportDir;
    }

    @Override
    public void print(Report report) {
        deleteDir(reportDir);
        List<ModuleReportReference> moduleReportReferences = newArrayList();
        for (ModuleReport moduleReport : report.getModuleReports()) {
            File file = getHtmlModuleReportFile(moduleReport);
            moduleReportReferences.add(new ModuleReportReference(
                    moduleReport.getModuleUri().toString(), file.getName(), moduleReport.getLineCoverage()));
            write(file, moduleReportGenerator.generate(moduleReport));
        }
        write(new File(reportDir, REPORT_INDEX), moduleReportIndexGenerator.generate(moduleReportReferences));
        resourceTransferor.copyResources(reportDir);
    }

    private File getHtmlModuleReportFile(ModuleReport moduleReport) {
        return new File(reportDir, uriToFilename(moduleReport.getModuleUri().getUri()) + ".html");
    }
}