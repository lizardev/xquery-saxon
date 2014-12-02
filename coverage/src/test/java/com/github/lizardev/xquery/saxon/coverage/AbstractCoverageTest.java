package com.github.lizardev.xquery.saxon.coverage;

import com.github.lizardev.xquery.saxon.coverage.util.XQueryExecutor;

import static com.github.lizardev.xquery.saxon.coverage.util.XQueryExecutorBuilder.xQueryExecutor;

public abstract class AbstractCoverageTest {
    protected CoverageService coverageService = new CoverageService();
    protected CoverageTraceExtension traceExtension = new CoverageTraceExtension(
            coverageService.getCoverageInstructionInjector(),
            coverageService.getCoverageInstructionListener());
    protected XQueryExecutor xqueryExecutor = xQueryExecutor().withTraceExtension(traceExtension).build();
}
