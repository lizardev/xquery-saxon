package com.github.lizardev.xquery.saxon.coverage;

import com.github.lizardev.xquery.saxon.coverage.trace.CoverageInstructionInjector;
import com.github.lizardev.xquery.saxon.coverage.trace.CoverageInstructionListener;
import com.github.lizardev.xquery.saxon.support.trace.TraceExtension;
import com.google.common.base.Optional;
import net.sf.saxon.lib.TraceListener;
import net.sf.saxon.trace.TraceCodeInjector;


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
