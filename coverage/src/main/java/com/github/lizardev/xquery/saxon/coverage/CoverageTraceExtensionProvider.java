package com.github.lizardev.xquery.saxon.coverage;

import com.github.lizardev.xquery.saxon.support.trace.TraceExtension;
import com.github.lizardev.xquery.saxon.support.trace.TraceExtensionProvider;
import com.google.common.base.Optional;

public class CoverageTraceExtensionProvider implements TraceExtensionProvider {

    private SystemProperties systemProperties = new SystemProperties();

    @Override
    public Optional<TraceExtension> getTraceExtension() {
        if (systemProperties.isCoverageEnabled()) {
            CoverageService coverageService = CoverageService.getInstance();
            return Optional.of((TraceExtension) new CoverageTraceExtension(coverageService.getCoverageInstructionInjector(), coverageService.getCoverageInstructionListener()));
        } else {
            return Optional.absent();
        }
    }
}