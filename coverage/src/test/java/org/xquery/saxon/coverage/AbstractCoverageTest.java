package org.xquery.saxon.coverage;

import org.xquery.saxon.coverage.util.XQueryExecutor;

import static org.xquery.saxon.coverage.util.XQueryExecutorBuilder.xQueryExecutor;

public abstract class AbstractCoverageTest {
    protected CoverageService coverageService = new CoverageService();
    protected CoverageTraceExtension traceExtension = new CoverageTraceExtension(
            coverageService.getCoverageInstructionInjector(),
            coverageService.getCoverageInstructionListener());
    protected XQueryExecutor xqueryExecutor = xQueryExecutor().withTraceExtension(traceExtension).build();
}
