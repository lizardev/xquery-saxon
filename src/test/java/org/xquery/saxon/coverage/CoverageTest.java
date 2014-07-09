package org.xquery.saxon.coverage;

import org.junit.Test;
import org.xquery.saxon.coverage.report.ModuleReport;
import org.xquery.saxon.coverage.report.ModuleReportHtmlPrinter;
import org.xquery.saxon.coverage.report.Report;
import org.xquery.saxon.coverage.util.XQueryExecutor;

import java.io.File;

import static org.xquery.saxon.coverage.util.Utils.fileNameFromUri;
import static org.xquery.saxon.coverage.util.Utils.writeStringToFile;

public class CoverageTest {

    @Test
    public void testCoverageOfTourModule() {
        CoverageService coverageService = new CoverageService();
        XQueryExecutor xQueryExecutor = new XQueryExecutor(coverageService.getCoverageInstructionInjector(),
                coverageService.getCoverageInstructionListener());

        xQueryExecutor.execute("/tour.xq");

        printCoverageReport(coverageService.getReport());
    }

    @Test
    public void testCoverageOfTestModule() {
        CoverageService coverageService = new CoverageService();
        XQueryExecutor xQueryExecutor = new XQueryExecutor(coverageService.getCoverageInstructionInjector(),
                coverageService.getCoverageInstructionListener());

        xQueryExecutor.execute("/test.xq");

        printCoverageReport(coverageService.getReport());
    }

    private static void printCoverageReport(Report report) {
        for (ModuleReport moduleReport : report.getModuleReports()) {
            System.out.printf("Coverage of %s is: %.2f%n", moduleReport.getModule(), moduleReport.getLineCoverage());
            writeStringToFile(new File(fileNameFromUri(moduleReport.getModule()) + ".html"), new ModuleReportHtmlPrinter().print(moduleReport));
        }
    }
}