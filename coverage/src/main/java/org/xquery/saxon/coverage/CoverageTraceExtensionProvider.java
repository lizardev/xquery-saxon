package org.xquery.saxon.coverage;

import org.xquery.saxon.adapter.trace.TraceExtension;
import org.xquery.saxon.adapter.trace.TraceExtensionProvider;

public class CoverageTraceExtensionProvider implements TraceExtensionProvider {

    @Override
    public TraceExtension getTraceExtension() {
        CoverageService coverageService = CoverageService.getInstance();
        return new CoverageTraceExtension(coverageService.getCoverageInstructionInjector(), coverageService.getCoverageInstructionListener());
    }
}
