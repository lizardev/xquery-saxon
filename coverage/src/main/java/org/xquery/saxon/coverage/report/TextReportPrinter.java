package org.xquery.saxon.coverage.report;

import java.io.PrintStream;

public class TextReportPrinter {
    public void print(Report report, PrintStream printStream) {
        for (ModuleReport moduleReport : report.getModuleReports()) {
            printStream.printf("%s - line coverage: %.2f%n", moduleReport.getModuleUri(), moduleReport.getLineCoverage());
        }
    }
}
