package org.xquery.saxon.coverage;

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

    public CoverageInstructionInjector getCoverageInstructionInjector() {
        return coverageInstructionInjector;
    }

    public CoverageInstructionListener getCoverageInstructionListener() {
        return coverageInstructionListener;
    }

    public Report getReport() {
        return defaultCoverageInstructionEventHandler.getReport();
    }
}
