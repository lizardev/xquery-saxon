package com.github.lizardev.xquery.saxon.coverage;

import com.github.lizardev.xquery.saxon.coverage.stub.TraceExtensionStub;
import com.github.lizardev.xquery.saxon.coverage.util.XQueryExecutor;
import com.github.lizardev.xquery.saxon.support.trace.TraceExtensionComposite;

import static com.github.lizardev.xquery.saxon.coverage.util.XQueryExecutorBuilder.xQueryExecutor;

public abstract class AbstractMultipleTraceExtensionsCoverageTest {
    protected final CoverageService coverageServiceForSingleTraceExtensionCases = new CoverageService();
    protected final CoverageTraceExtension coverageTraceExtensionForSingleTraceExtensionCases = new CoverageTraceExtension(
            coverageServiceForSingleTraceExtensionCases.getCoverageInstructionInjector(),
            coverageServiceForSingleTraceExtensionCases.getCoverageInstructionListener());
    protected final XQueryExecutor xQueryExecutorWithSingleTraceExtension = xQueryExecutor()
            .withTraceExtension(coverageTraceExtensionForSingleTraceExtensionCases).build();

    protected final CoverageService coverageServiceForMultipleTraceExtensionsCases = new CoverageService();
    protected final CoverageTraceExtension coverageTraceExtensionForMultipleTraceExtensionsCases = new CoverageTraceExtension(
            coverageServiceForMultipleTraceExtensionsCases.getCoverageInstructionInjector(),
            coverageServiceForMultipleTraceExtensionsCases.getCoverageInstructionListener());
    protected final TraceExtensionStub traceExtensionStubForMultipleTraceExtensionsCases = new TraceExtensionStub();
    protected final XQueryExecutor xQueryExecutorWithMultipleTraceExtensions = xQueryExecutor()
            .withTraceExtension(new TraceExtensionComposite(coverageTraceExtensionForMultipleTraceExtensionsCases, traceExtensionStubForMultipleTraceExtensionsCases))
            .build();
}
