package org.xquery.saxon.coverage;

import org.junit.Ignore;
import org.junit.Test;
import org.xquery.saxon.coverage.report.ModuleReport;
import org.xquery.saxon.coverage.util.XQueryExecutor;

import static org.xquery.saxon.coverage.assertj.ProjectAssertions.assertThat;
import static org.xquery.saxon.coverage.util.ExecutionContext.executionContext;
import static org.xquery.saxon.coverage.util.XQueryExecutorBuilder.xQueryExecutor;

public class MultipleModuleCompilationCoverageTest {
    private CoverageService coverageService = new CoverageService();
    private CoverageTraceExtension traceExtension = new CoverageTraceExtension(
            coverageService.getCoverageInstructionInjector(),
            coverageService.getCoverageInstructionListener());

    @Ignore("feature not implemented yet")
    @Test
    public void shouldMergeCoverageOfModuleCompiledTwice() {
        XQueryExecutor xQueryExecutor = xQueryExecutor().withTraceExtension(traceExtension).build();
        boolean result = xQueryExecutor.execute("/branches/TwoBranches.xq", executionContext().withExternalVariable("switch", true).build());
        assertThat(result).isTrue();
        result = xQueryExecutor.execute("/branches/TwoBranches.xq", executionContext().withExternalVariable("switch", false).build());
        assertThat(result).isFalse();

        ModuleReport moduleReport = coverageService.getReport().getModuleReports().iterator().next();

        assertThat(moduleReport).isFullyCovered();
    }
}
