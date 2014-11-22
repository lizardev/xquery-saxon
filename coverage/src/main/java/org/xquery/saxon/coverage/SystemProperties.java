package org.xquery.saxon.coverage;

public class SystemProperties {

    public static final String COVERAGE_ENABLED = "xquery.saxon.coverage";
    public static final String COVERAGE_REPORT_PRINTING_ON_SHUTDOWN = "xquery.saxon.coverage.report.printing.on.shutdown";

    public boolean isCoverageEnabled() {
        return System.getProperties().containsKey(COVERAGE_ENABLED);
    }

    public boolean isCoverageReportPrintingOnShutdownEnabled() {
        return System.getProperties().containsKey(COVERAGE_REPORT_PRINTING_ON_SHUTDOWN);
    }
}