package com.github.lizardev.xquery.saxon.coverage;

import com.google.common.base.Optional;

import java.util.Properties;

import static com.google.common.base.Optional.of;


public class SystemProperties {

    public static final String COVERAGE_ENABLED = "xquery.saxon.coverage";
    public static final String COVERAGE_REPORT_PRINTING_ON_SHUTDOWN = "xquery.saxon.coverage.report.printing.on.shutdown";
    public static final String COVERAGE_REPORT_DIRECTORY = "xquery.saxon.coverage.report.directory";
    public static final String COVERAGE_SKIP_CONSOLE_OUTPUT = "xquery.saxon.coverage.skip.console.output";

    public boolean isCoverageEnabled() {
        return System.getProperties().containsKey(COVERAGE_ENABLED);
    }

    public boolean isCoverageReportPrintingOnShutdownEnabled() {
        return System.getProperties().containsKey(COVERAGE_REPORT_PRINTING_ON_SHUTDOWN);
    }

    public boolean isCoverageReportPrintingOnConsoleEnabled() {
        return ! System.getProperties().containsKey(COVERAGE_SKIP_CONSOLE_OUTPUT);
    }

    public Optional<String> getCoverageReportDirectory() {
        Properties properties = System.getProperties();
        return properties.containsKey(COVERAGE_REPORT_DIRECTORY) ? of(properties.getProperty(COVERAGE_REPORT_DIRECTORY)) : Optional.<String>absent();
    }
}