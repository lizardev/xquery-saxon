package org.xquery.saxon.coverage;

import net.sf.saxon.lib.TraceListener;
import net.sf.saxon.trace.TraceCodeInjector;
import org.xquery.saxon.adapter.trace.TraceExtension;
import org.xquery.saxon.coverage.trace.CoverageInstructionInjector;
import org.xquery.saxon.coverage.trace.CoverageInstructionListener;

public class CoverageTraceExtension implements TraceExtension {

    private CoverageInstructionInjector coverageInstructionInjector;
    private CoverageInstructionListener coverageInstructionListener;

    public CoverageTraceExtension(CoverageInstructionInjector coverageInstructionInjector, CoverageInstructionListener coverageInstructionListener) {
        this.coverageInstructionInjector = coverageInstructionInjector;
        this.coverageInstructionListener = coverageInstructionListener;
    }

    @Override
    public TraceCodeInjector getTraceCodeInjector() {
        return coverageInstructionInjector;
    }

    @Override
    public TraceListener getTraceListener() {
        return coverageInstructionListener;
    }

    @Override
    public boolean allowsOptimization() {
        return false;
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
