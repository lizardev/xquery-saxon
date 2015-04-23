package com.github.lizardev.xquery.saxon.coverage;

import java.util.Optional;
import java.util.Properties;

import static java.util.Optional.of;

public class SystemProperties {

    public static final String COVERAGE_ENABLED = "xquery.saxon.coverage";
    public static final String COVERAGE_REPORT_PRINTING_ON_SHUTDOWN = "xquery.saxon.coverage.report.printing.on.shutdown";
    public static final String COVERAGE_REPORT_DIRECTORY = "xquery.saxon.coverage.report.directory";

    public boolean isCoverageEnabled() {
        return System.getProperties().containsKey(COVERAGE_ENABLED);
    }

    public boolean isCoverageReportPrintingOnShutdownEnabled() {
        return System.getProperties().containsKey(COVERAGE_REPORT_PRINTING_ON_SHUTDOWN);
    }

    public Optional<String> getCoverageReportDirectory() {
        Properties properties = System.getProperties();
        return properties.containsKey(COVERAGE_REPORT_DIRECTORY) ? of(properties.getProperty(COVERAGE_REPORT_DIRECTORY)) : Optional.<String>empty();
    }
}