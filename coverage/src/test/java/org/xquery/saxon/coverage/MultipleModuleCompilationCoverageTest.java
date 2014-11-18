package org.xquery.saxon.coverage;

import org.junit.Test;
import org.xquery.saxon.coverage.report.Report;

import static org.xquery.saxon.coverage.TestConstants.TWO_BRANCHES_MODULE;
import static org.xquery.saxon.coverage.assertj.ProjectAssertions.assertThat;
import static org.xquery.saxon.coverage.util.ExecutionContext.executionContext;

public class MultipleModuleCompilationCoverageTest extends AbstractCoverageTest {

    @Test
    public void shouldMergeCoverageOfModuleCompiledTwice() {
        boolean result = xqueryExecutor.execute(TWO_BRANCHES_MODULE, executionContext().withExternalVariable("switch", true).build());
        assertThat(result).isTrue();
        result = xqueryExecutor.execute(TWO_BRANCHES_MODULE, executionContext().withExternalVariable("switch", false).build());
        assertThat(result).isFalse();

        Report report = coverageService.getReport();
        assertThat(report).hasNumberOfModules(1);
        assertThat(report.getModuleReport(TWO_BRANCHES_MODULE)).isFullyCovered();
    }
}
