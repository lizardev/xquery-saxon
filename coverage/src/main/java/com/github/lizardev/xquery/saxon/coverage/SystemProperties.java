package com.github.lizardev.xquery.saxon.coverage;

import com.google.common.base.Optional;

import java.util.Properties;

import static com.google.common.base.Optional.of;


public class SystemProperties {

    public static final String COVERAGE_ENABLED = "xquery.saxon.coverage";
    public static final String REPORT_BIN_SAVE_ON_SHUTDOWN = "xquery.saxon.coverage.report.bin.save.on.shutdown";
    public static final String REPORT_HTML_SAVE_ON_SHUTDOWN = "xquery.saxon.coverage.report.html.save.on.shutdown";
    public static final String REPORT_DIR = "xquery.saxon.coverage.report.dir";

    public boolean isCoverageEnabled() {
        return System.getProperties().containsKey(COVERAGE_ENABLED);
    }

    public Optional<String> getReportDir() {
        Properties properties = System.getProperties();
        return properties.containsKey(REPORT_DIR) ? of(properties.getProperty(REPORT_DIR)) : Optional.<String>absent();
    }

    public boolean isBinReportSavingEnabledOnShutdown() {
        return System.getProperties().containsKey(REPORT_BIN_SAVE_ON_SHUTDOWN);
    }

    public boolean isHtmlReportSavingEnabledOnShutdown() {
        return System.getProperties().containsKey(REPORT_HTML_SAVE_ON_SHUTDOWN);
    }
}