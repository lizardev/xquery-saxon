package org.xquery.saxon.coverage;

public class SystemProperties {

    public static final String COVERAGE_ENABLED = "org.xquery.saxon.coverage";

    public boolean isCoverageTraceExtensionEnabled() {
        return System.getProperties().containsKey(COVERAGE_ENABLED);
    }
}
