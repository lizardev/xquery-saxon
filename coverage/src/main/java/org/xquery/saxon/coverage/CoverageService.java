package org.xquery.saxon.coverage;

import net.sf.saxon.lib.TraceListener;
import net.sf.saxon.trace.TraceCodeInjector;
import org.xquery.saxon.adapter.trace.TraceProvider;
import org.xquery.saxon.coverage.collect.DefaultCoverageInstructionEventHandler;
import org.xquery.saxon.coverage.report.Report;
import org.xquery.saxon.coverage.trace.CoverageInstructionInjector;
import org.xquery.saxon.coverage.trace.CoverageInstructionListener;

public class CoverageService {

    private final DefaultCoverageInstructionEventHandler defaultCoverageInstructionEventHandler;
    private final CoverageInstructionInjector coverageInstructionInjector;
    private final CoverageInstructionListener coverageInstructionListener;

    public CoverageService() {
        defaultCoverageInstructionEventHandler = new DefaultCoverageInstructionEventHandler();
        coverageInstructionInjector = new CoverageInstructionInjector(defaultCoverageInstructionEventHandler);
        coverageInstructionListener = new CoverageInstructionListener(defaultCoverageInstructionEventHandler);
    }

    public Report getReport() {
        return defaultCoverageInstructionEventHandler.getReport();
    }

    public TraceProvider getTraceProvider() {
        return new TraceProvider() {

            @Override
            public TraceCodeInjector getTraceCodeInjector() {
                return coverageInstructionInjector;
            }

            @Override
            public TraceListener getTraceListener() {
                return coverageInstructionListener;
            }

            @Override
            public boolean supportsOptimization() {
                return false;
            }
        };
    }
}
