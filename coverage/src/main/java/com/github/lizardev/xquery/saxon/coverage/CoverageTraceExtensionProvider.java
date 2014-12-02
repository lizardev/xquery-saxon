package com.github.lizardev.xquery.saxon.coverage;

import com.github.lizardev.xquery.saxon.support.trace.TraceExtension;
import com.github.lizardev.xquery.saxon.support.trace.TraceExtensionProvider;

import javax.annotation.Nullable;

public class CoverageTraceExtensionProvider implements TraceExtensionProvider {

    private SystemProperties systemProperties = new SystemProperties();

    @Override @Nullable
    public TraceExtension getTraceExtension() {
        if (systemProperties.isCoverageEnabled()) {
            CoverageService coverageService = CoverageService.getInstance();
            return new CoverageTraceExtension(coverageService.getCoverageInstructionInjector(), coverageService.getCoverageInstructionListener());
        } else {
            return null;
        }
    }
}