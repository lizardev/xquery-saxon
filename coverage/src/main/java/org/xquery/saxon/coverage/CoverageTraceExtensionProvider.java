package org.xquery.saxon.coverage;

import org.xquery.saxon.adapter.trace.TraceExtension;
import org.xquery.saxon.adapter.trace.TraceExtensionProvider;

import javax.annotation.Nullable;

public class CoverageTraceExtensionProvider implements TraceExtensionProvider {

    private SystemProperties systemProperties = new SystemProperties();

    @Override @Nullable
    public TraceExtension getTraceExtension() {
        if (systemProperties.isCoverageTraceExtensionEnabled()) {
            CoverageService coverageService = CoverageService.getInstance();
            return new CoverageTraceExtension(coverageService.getCoverageInstructionInjector(), coverageService.getCoverageInstructionListener());
        } else {
            return null;
        }
    }
}