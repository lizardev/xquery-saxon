package org.xquery.saxon.coverage;

import org.junit.Ignore;
import org.junit.Test;
import org.xquery.saxon.coverage.report.ModuleReport;

import static org.xquery.saxon.coverage.TestConstants.TWO_BRANCHES_MODULE;
import static org.xquery.saxon.coverage.assertj.ProjectAssertions.assertThat;
import static org.xquery.saxon.coverage.util.ExecutionContext.executionContext;

public class MultipleModuleCompilationCoverageTest extends AbstractCoverageTest {

    @Ignore("feature not implemented yet")
    @Test
    public void shouldMergeCoverageOfModuleCompiledTwice() {
        boolean result = xqueryExecutor.execute(TWO_BRANCHES_MODULE, executionContext().withExternalVariable("switch", true).build());
        assertThat(result).isTrue();
        result = xqueryExecutor.execute(TWO_BRANCHES_MODULE, executionContext().withExternalVariable("switch", false).build());
        assertThat(result).isFalse();

        ModuleReport moduleReport = coverageService.getReport().getModuleReports().iterator().next();
        assertThat(moduleReport).isFullyCovered();
    }
}
