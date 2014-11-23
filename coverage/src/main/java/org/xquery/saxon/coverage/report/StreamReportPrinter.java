package org.xquery.saxon.coverage.report;

import java.io.PrintStream;

public class StreamReportPrinter implements ReportPrinter {

    private final PrintStream stream;

    public StreamReportPrinter(PrintStream stream) {
        this.stream = stream;
    }

    public void print(Report report) {
        for (ModuleReport moduleReport : report.getModuleReports()) {
            stream.printf("%s - line coverage: %.2f%n", moduleReport.getModuleUri(), moduleReport.getLineCoverage());
        }
    }
}
